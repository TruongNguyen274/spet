package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.request.AuthRequest;
import com.example.spetsrestapi.model.request.LoginRequest;
import com.example.spetsrestapi.model.request.RegisterRequest;
import com.example.spetsrestapi.model.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse updateToken(AuthRequest request);

    AuthResponse refreshToken(AuthRequest request);

}
