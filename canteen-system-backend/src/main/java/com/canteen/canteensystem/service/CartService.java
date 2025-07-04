package com.canteen.canteensystem.service;

import com.canteen.canteensystem.dto.CartItemDto;

import java.util.List;

public interface CartService {
    List<CartItemDto> getMyCart();
    CartItemDto addItem(Long itemId, Integer qty);
    CartItemDto updateItem(Long itemId, Integer qty);
    void removeItem(Long itemId);
    void clearCart();
}
