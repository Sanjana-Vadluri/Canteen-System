package com.canteen.canteensystem.controller;

import com.canteen.canteensystem.dto.CartItemDto;
import com.canteen.canteensystem.service.CartService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /* ------------------ read ------------------ */
    @GetMapping
    public ResponseEntity<List<CartItemDto>> myCart() {
        return ResponseEntity.ok(cartService.getMyCart());
    }

    /* ------------------ create / update ------------------ */
    @PostMapping("/add")
    public ResponseEntity<CartItemDto> add(
            @RequestParam Long itemId,
            @RequestParam @Min(1) Integer qty) {

        return new ResponseEntity<>(cartService.addItem(itemId, qty),
                HttpStatus.CREATED);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<CartItemDto> updateQty(
            @PathVariable Long itemId,
            @RequestParam @Min(1) Integer qty) {

        return ResponseEntity.ok(cartService.updateItem(itemId, qty));
    }

    /* ------------------ delete ------------------ */
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> remove(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clear() {
        cartService.clearCart();
        return ResponseEntity.noContent().build();
    }
}
