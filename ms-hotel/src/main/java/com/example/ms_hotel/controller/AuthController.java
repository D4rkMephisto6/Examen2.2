package com.example.ms_hotel.controller;

import com.example.ms_hotel.dto.LoginRequest;
import com.example.ms_hotel.dto.LoginResponse;
import com.example.ms_hotel.security.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")

    public LoginResponse login(@RequestBody LoginRequest request) {
        System.out.println("ENTRO AL LOGIN");
        System.out.println(request);
        if ("admin".equals(request.getUsername())
                && "admin123".equals(request.getPassword())) {

            String token =
                    jwtService.generateToken("admin", "ADMIN");

            return new LoginResponse(token);
        }

        if ("user".equals(request.getUsername())
                && "user123".equals(request.getPassword())) {

            String token =
                    jwtService.generateToken("user", "USER");

            return new LoginResponse(token);
        }

        throw new RuntimeException("Credenciales inválidas");
    }
}