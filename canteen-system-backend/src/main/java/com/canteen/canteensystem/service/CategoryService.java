package com.canteen.canteensystem.service;

import com.canteen.canteensystem.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(Long canteenId, CategoryDto dto);
    List<CategoryDto> getByCanteen(Long canteenId);
}
