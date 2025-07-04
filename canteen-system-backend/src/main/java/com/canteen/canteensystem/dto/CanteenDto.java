package com.canteen.canteensystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CanteenDto {
    private Long id;
    private String name;
    private String location;
}
