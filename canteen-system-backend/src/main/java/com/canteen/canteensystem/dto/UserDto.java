package com.canteen.canteensystem.dto;

import com.canteen.canteensystem.model.Role;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDto {
    private Long id;
    private String email;
    private Role role;
}
