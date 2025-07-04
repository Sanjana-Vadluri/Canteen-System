package com.canteen.canteensystem.mapper;

import com.canteen.canteensystem.dto.CartItemDto;
import com.canteen.canteensystem.model.CartItem;

public class CartItemMapper {

    public static CartItemDto toDto(CartItem ci) {
        return CartItemDto.builder()
                .id(ci.getId())
                .itemId(ci.getItem().getId())
                .itemName(ci.getItem().getName())
                .price(ci.getItem().getPrice())
                .quantity(ci.getQuantity())
                .lineTotal(ci.getLineTotal())
                .build();
    }
}
