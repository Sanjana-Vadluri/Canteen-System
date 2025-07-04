// service/impl/OrderServiceImpl.java
package com.canteen.canteensystem.service.impl;

import com.canteen.canteensystem.dto.OrderDto;
import com.canteen.canteensystem.mapper.OrderMapper;
import com.canteen.canteensystem.model.*;
import com.canteen.canteensystem.repository.*;
import com.canteen.canteensystem.service.OrderService;
import com.canteen.canteensystem.websocket.OrderNotifier;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CartItemRepository cartRepo;
    private final OrderRepository orderRepo;
    private final ItemRepository itemRepo;
    private final UserRepository userRepo;
    private final OrderNotifier notifier;


    /* ---------- helpers ---------- */
    private User currentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "User not found"));
    }

    /* ---------- interface methods ---------- */

    @Override
    public OrderDto placeOrder() {
        User user = currentUser();
        List<CartItem> cart = cartRepo.findByUserId(user.getId());
        if (cart.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cart is empty");

        // build Order & OrderItems
        Order order = Order.builder()
                .createdAt(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .user(user)
                .build();

        List<OrderItem> orderItems = cart.stream().map(ci ->
                OrderItem.builder()
                        .order(order)
                        .item(ci.getItem())
                        .quantity(ci.getQuantity())
                        .price(ci.getItem().getPrice())  // snapshot
                        .build()
        ).collect(Collectors.toList());

        order.setItems(orderItems);

        // save & clear cart
        Order saved = orderRepo.save(order);
        cartRepo.deleteAll(cart);
        notifier.newOrder(OrderMapper.toDto(saved));

        return OrderMapper.toDto(saved);
    }

    @Override
    public List<OrderDto> myOrders() {
        return orderRepo.findByUserIdOrderByCreatedAtDesc(currentUser().getId())
                .stream().map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> allOrders() {
        return orderRepo.findAll().stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto updateStatus(Long orderId, OrderStatus status) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order not found"));
        order.setStatus(status);
        Order updated = orderRepo.save(order);
        notifier.statusChanged(OrderMapper.toDto(updated));
        return OrderMapper.toDto(orderRepo.save(order));
    }
}
