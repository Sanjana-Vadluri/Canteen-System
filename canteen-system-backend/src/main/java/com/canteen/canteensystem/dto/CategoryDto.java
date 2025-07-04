package com.canteen.canteensystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CategoryDto {
    private Long id;

    @NotBlank(message = "Category name is required")
    private String name;

    private String description;

    // we return canteenId in the DTO so frontend knows linkage
    private Long canteenId;
}
