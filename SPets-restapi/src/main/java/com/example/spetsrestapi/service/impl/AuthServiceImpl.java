package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.model.request.AuthRequest;
import com.example.spetsrestapi.model.request.LoginRequest;
import com.example.spetsrestapi.model.request.RegisterRequest;
import com.example.spetsrestapi.model.response.AuthResponse;
import com.example.spetsrestapi.security.JwtToken;
import com.example.spetsrestapi.security.UserSecurity;
import com.example.spetsrestapi.service.AuthService;
import com.example.spetsrestapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        // new User
        Account user = new Account();
        user.setFullName(request.getFullname());
        user.setUsername(request.getUsername());
        user.setEmail(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole().toUpperCase());
        user.setStatus(true);

        // Save
        accountService.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUsername(request.getUsername());

        return authResponse;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            Account user = accountService.findByUsername(request.getUsername());
            if (user != null) {
                UserSecurity userSecurity = new UserSecurity(user);

                Map<String, Object> extraClaims = new HashMap<>();
                extraClaims.put("username", user.getUsername());
                extraClaims.put("authorities", userSecurity.getAuthorities());

                String accessToken = jwtToken.generateToken(extraClaims, userSecurity);
                String refreshToken = jwtToken.generateRefreshToken(userSecurity);

                AuthResponse authResponse = new AuthResponse();
                authResponse.setId(user.getId());
                authResponse.setUsername(request.getUsername());
                authResponse.setFullname(user.getFullName());
                authResponse.setEmail(user.getEmail());
                authResponse.setRole(user.getRole().toUpperCase());
                authResponse.setAccessToken(accessToken);
                authResponse.setRefreshToken(refreshToken);

                return authResponse;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public AuthResponse updateToken(AuthRequest request) {
        try {
            Account user = accountService.findByUsername(request.getUsername());
            if (user != null) {
                user.setToken(request.getToken());
                accountService.save(user);

                AuthResponse authResponse = new AuthResponse();
                authResponse.setId(user.getId());
                authResponse.setUsername(request.getUsername());
                authResponse.setFullname(user.getFullName());
                authResponse.setEmail(user.getEmail());
                authResponse.setRole(user.getRole().toUpperCase());

                return authResponse;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public AuthResponse refreshToken(AuthRequest request) {
        return null;
    }

}