package com.canteen.canteensystem.dto;

import com.canteen.canteensystem.model.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderDto {
    private Long id;
    private LocalDateTime createdAt;
    private OrderStatus status;
    private Double total;
    private List<OrderItemDto> items;
}
