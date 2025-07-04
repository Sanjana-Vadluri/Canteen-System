package com.canteen.canteensystem.service;

import com.canteen.canteensystem.model.User;
import com.canteen.canteensystem.repository.UserRepository;
import com.canteen.canteensystem.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public String login(String email, String password) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        System.out.println(user.getRole().name());
        String role = user.getRole().name();
        return jwtUtil.generate(email, role);
    }
}

