package com.nazareno.portal.controller;

import com.nazareno.portal.dto.LoginRequest;
import com.nazareno.portal.dto.LoginResponse;
import com.nazareno.portal.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest datos) {
        return authService.login(datos);
    }
}