package com.nazareno.portal.repository;

import com.nazareno.portal.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
    Optional<Asistencia> findByUsuarioIdAndFecha(Long usuarioId, LocalDate fecha);
}