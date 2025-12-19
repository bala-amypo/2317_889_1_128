package com.example.demo.services;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.UserAccount;

public interface AuthService {

    AuthResponse login(AuthRequest request);

    UserAccount register(UserAccount user);
}
