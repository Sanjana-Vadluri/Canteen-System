package com.canteen.canteensystem.controller;

import com.canteen.canteensystem.dto.ItemDto;
import com.canteen.canteensystem.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /* -------- items under a category -------- */

    @PostMapping("/api/categories/{categoryId}/items")
    public ResponseEntity<ItemDto> addItem(
            @PathVariable Long categoryId,
            @Valid @RequestBody ItemDto dto) {

        return new ResponseEntity<>(itemService.create(categoryId, dto), HttpStatus.CREATED);
    }

    @GetMapping("/api/categories/{categoryId}/items")
    public ResponseEntity<List<ItemDto>> listItems(@PathVariable Long categoryId) {
        return ResponseEntity.ok(itemService.getByCategory(categoryId));
    }

    /* -------- CRUD on a single item -------- */

    @PutMapping("/api/items/{itemId}")
    public ResponseEntity<ItemDto> editItem(
            @PathVariable Long itemId,
            @Valid @RequestBody ItemDto dto) {

        return ResponseEntity.ok(itemService.update(itemId, dto));
    }

    @DeleteMapping("/api/items/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        itemService.delete(itemId);
        return ResponseEntity.noContent().build();
    }
}
