package com.canteen.canteensystem.mapper;

import com.canteen.canteensystem.dto.CategoryDto;
import com.canteen.canteensystem.model.Category;
import com.canteen.canteensystem.model.Canteen;

public class CategoryMapper {

    public static Category toEntity(CategoryDto dto, Canteen canteen) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .canteen(canteen)
                .build();
    }

    public static CategoryDto toDto(Category entity) {
        return CategoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .canteenId(entity.getCanteen().getId())
                .build();
    }
}
