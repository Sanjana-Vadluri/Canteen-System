package com.canteen.canteensystem.controller;

import com.canteen.canteensystem.dto.CategoryDto;
import com.canteen.canteensystem.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/canteens/{canteenId}/categories")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(
            @PathVariable Long canteenId,
            @Valid @RequestBody CategoryDto dto) {

        return new ResponseEntity<>(
                categoryService.create(canteenId, dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories(
            @PathVariable Long canteenId) {

        return ResponseEntity.ok(categoryService.getByCanteen(canteenId));
    }
}