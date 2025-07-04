package com.canteen.canteensystem.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CartItemDto {
    private Long id;
    private Long itemId;
    private String itemName;
    private Double price;
    private Integer quantity;
    private Double lineTotal;
}
