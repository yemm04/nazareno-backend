package com.nazareno.portal.repository;

import com.nazareno.portal.model.Rol;
import com.nazareno.portal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    long countByRol(Rol rol);
    Optional<Usuario> findByCodigo(String codigo);
}