package com.nazareno.portal.service;

import com.nazareno.portal.dto.LoginRequest;
import com.nazareno.portal.dto.LoginResponse;
import com.nazareno.portal.model.Usuario;
import com.nazareno.portal.repository.UsuarioRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest datos) {
        Usuario usuario = usuarioRepository.findByCodigo(datos.getCodigo())
                .orElseThrow(() -> new BadCredentialsException("Código o contraseña incorrectos."));

        if (!passwordEncoder.matches(datos.getPassword(), usuario.getPassword())) {
            throw new BadCredentialsException("Código o contraseña incorrectos.");
        }

        String token = jwtService.generarToken(usuario.getCodigo());

        return new LoginResponse(
                token, usuario.getId(), usuario.getCodigo(), usuario.getNombre(),
                usuario.getApellido(), usuario.getRol().name(), usuario.getArea());
    }
}