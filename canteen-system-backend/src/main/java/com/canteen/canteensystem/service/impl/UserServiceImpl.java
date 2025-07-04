package com.canteen.canteensystem.service.impl;

import com.canteen.canteensystem.dto.UserDto;
import com.canteen.canteensystem.model.User;
import com.canteen.canteensystem.model.Role;
import com.canteen.canteensystem.repository.UserRepository;
import com.canteen.canteensystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    @Override
    public UserDto registerStudent(String email, String rawPassword) {
        if (repo.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already in use");
        }

        User saved = repo.save(User.builder()
                .email(email)
                .password(encoder.encode(rawPassword))
                .role(Role.STUDENT)
                .build());

        return new UserDto(saved.getId(), saved.getEmail(), saved.getRole());
    }

    @Override
    public boolean exists(String email) {
        return repo.existsByEmail(email);
    }
}
