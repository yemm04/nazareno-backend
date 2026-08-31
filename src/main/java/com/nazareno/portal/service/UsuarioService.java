package com.nazareno.portal.service;

import com.nazareno.portal.model.Usuario;
import com.nazareno.portal.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario crear(Usuario datos) {
        long countConMismoRol = usuarioRepository.countByRol(datos.getRol());
        String codigo = datos.getRol().getPrefijo() + (2600 + countConMismoRol + 1);

        datos.setCodigo(codigo);
        datos.setEstado("ACTIVO");
        datos.setPassword(passwordEncoder.encode(datos.getDni()));
        if (datos.getFechaIngreso() == null) {
            datos.setFechaIngreso(LocalDate.now());
        }
        return usuarioRepository.save(datos);
    }

    public Usuario actualizar(Long id, Usuario datos) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado."));

        existente.setNombre(datos.getNombre());
        existente.setApellido(datos.getApellido());
        existente.setDni(datos.getDni());
        existente.setGenero(datos.getGenero());
        existente.setFechaNacimiento(datos.getFechaNacimiento());
        existente.setTelefono(datos.getTelefono());
        existente.setDireccion(datos.getDireccion());
        existente.setArea(datos.getArea());

        return usuarioRepository.save(existente);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}