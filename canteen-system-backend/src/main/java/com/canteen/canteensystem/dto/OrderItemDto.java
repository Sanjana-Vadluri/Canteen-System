package com.canteen.canteensystem.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItemDto {
    private Long id;
    private Long itemId;
    private String itemName;
    private Integer quantity;
    private Double price;
    private Double lineTotal;
}
