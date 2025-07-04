package com.canteen.canteensystem.service;

import com.canteen.canteensystem.dto.UserDto;

public interface UserService {
    UserDto registerStudent(String email, String rawPassword);
    boolean exists(String email);
}
