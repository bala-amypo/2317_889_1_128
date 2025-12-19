package com.example.demo.services.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.UserAccount;
import com.example.demo.entity.enums.RoleType;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserAccountRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authManager,
            JwtUtil jwtUtil,
            UserAccountRepository userRepo,
            PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        UserAccount user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user);

        return new AuthResponse(
                token,
                user.getId(),
                user.getEmail(),
                user.getRole().name());
    }

    @Override
    public UserAccount register(UserAccount user) {

        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null) {
            user.setRole(RoleType.INVESTOR);
        }

        user.setActive(true);
        return userRepo.save(user);
    }

}
