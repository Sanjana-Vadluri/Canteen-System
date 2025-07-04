package com.canteen.canteensystem.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ItemDto {

    private Long id;

    @NotBlank(message = "Item name is required")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    @Positive
    private Double price;

    private String imageUrl;

    private Boolean available;

    private Long categoryId;  // needed so front‑end knows linkage
}
