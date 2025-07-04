package com.canteen.canteensystem.mapper;

import com.canteen.canteensystem.dto.CanteenDto;
import com.canteen.canteensystem.model.Canteen;

public class CanteenMapper {

    public static CanteenDto toDto(Canteen canteen) {
        return CanteenDto.builder()
                .id(canteen.getId())
                .name(canteen.getName())
                .location(canteen.getLocation())
                .build();
    }

    public static Canteen toEntity(CanteenDto dto) {
        return Canteen.builder()
                .id(dto.getId())
                .name(dto.getName())
                .location(dto.getLocation())
                .build();
    }
}
