package com.canteen.canteensystem.service.impl;

import com.canteen.canteensystem.dto.CartItemDto;
import com.canteen.canteensystem.mapper.CartItemMapper;
import com.canteen.canteensystem.model.*;
import com.canteen.canteensystem.repository.*;
import com.canteen.canteensystem.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartRepo;
    private final ItemRepository itemRepo;
    private final UserRepository userRepo;

    /* ---------- helpers ---------- */
    private User currentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "User not found"));
    }

    /* ---------- public API ---------- */

    @Override
    public List<CartItemDto> getMyCart() {
        Long uid = currentUser().getId();
        return cartRepo.findByUserId(uid)
                .stream().map(CartItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartItemDto addItem(Long itemId, Integer qty) {
        if (qty <= 0) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Quantity must be > 0");

        User user = currentUser();
        Item item = itemRepo.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Item not found"));

        CartItem ci = cartRepo.findByUserIdAndItemId(user.getId(), itemId)
                .map(existing -> {               // already in cart → update qty
                    existing.setQuantity(existing.getQuantity() + qty);
                    return existing;
                })
                .orElse(CartItem.builder()       // new cart line
                        .user(user).item(item).quantity(qty).build());

        return CartItemMapper.toDto(cartRepo.save(ci));
    }

    @Override
    public CartItemDto updateItem(Long itemId, Integer qty) {
        if (qty <= 0) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Quantity must be > 0");

        CartItem ci = cartRepo.findByUserIdAndItemId(currentUser().getId(), itemId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Item not in cart"));

        ci.setQuantity(qty);
        return CartItemMapper.toDto(cartRepo.save(ci));
    }

    @Override
    public void removeItem(Long itemId) {
        CartItem ci = cartRepo.findByUserIdAndItemId(currentUser().getId(), itemId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Item not in cart"));
        cartRepo.delete(ci);
    }

    @Override
    public void clearCart() {
        cartRepo.deleteAll(cartRepo.findByUserId(currentUser().getId()));
    }
}
