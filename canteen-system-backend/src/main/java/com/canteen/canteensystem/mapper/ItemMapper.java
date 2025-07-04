package com.canteen.canteensystem.mapper;

import com.canteen.canteensystem.dto.ItemDto;
import com.canteen.canteensystem.model.Category;
import com.canteen.canteensystem.model.Item;

public class ItemMapper {

    public static Item toEntity(ItemDto dto, Category category) {
        return Item.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .available(dto.getAvailable() != null ? dto.getAvailable() : true)
                .category(category)
                .build();
    }

    public static ItemDto toDto(Item entity) {
        return ItemDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .imageUrl(entity.getImageUrl())
                .available(entity.getAvailable())
                .categoryId(entity.getCategory().getId())
                .build();
    }
}
