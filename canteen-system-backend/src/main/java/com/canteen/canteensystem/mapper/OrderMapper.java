package com.canteen.canteensystem.mapper;

import com.canteen.canteensystem.dto.*;
import com.canteen.canteensystem.model.Order;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderItemDto toDto(com.canteen.canteensystem.model.OrderItem oi) {
        return OrderItemDto.builder()
                .id(oi.getId())
                .itemId(oi.getItem().getId())
                .itemName(oi.getItem().getName())
                .quantity(oi.getQuantity())
                .price(oi.getPrice())
                .lineTotal(oi.getLineTotal())
                .build();
    }

    public static OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .status(order.getStatus())
                .total(order.getTotal())
                .items(order.getItems().stream()
                        .map(OrderMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
