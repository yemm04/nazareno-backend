package com.nazareno.portal.service;

import com.nazareno.portal.model.Asistencia;
import com.nazareno.portal.model.Usuario;
import com.nazareno.portal.repository.AsistenciaRepository;
import com.nazareno.portal.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final UsuarioRepository usuarioRepository;

    public AsistenciaService(AsistenciaRepository asistenciaRepository, UsuarioRepository usuarioRepository) {
        this.asistenciaRepository = asistenciaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Asistencia> listarTodas() {
        return asistenciaRepository.findAll();
    }

    public Asistencia marcarEntrada(Long usuarioId, String metodo) {
        LocalDate hoy = LocalDate.now();
        return asistenciaRepository.findByUsuarioIdAndFecha(usuarioId, hoy)
                .orElseGet(() -> {
                    Usuario usuario = usuarioRepository.findById(usuarioId)
                            .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado."));
                    Asistencia nueva = new Asistencia();
                    nueva.setUsuario(usuario);
                    nueva.setFecha(hoy);
                    nueva.setHoraEntrada(LocalTime.now());
                    nueva.setMetodo(metodo);
                    return asistenciaRepository.save(nueva);
                });
    }

    public Asistencia marcarSalida(Long usuarioId) {
        LocalDate hoy = LocalDate.now();
        Asistencia registro = asistenciaRepository.findByUsuarioIdAndFecha(usuarioId, hoy)
                .orElseThrow(() -> new NoSuchElementException("Aún no marcaste tu entrada hoy."));
        if (registro.getHoraSalida() == null) {
            registro.setHoraSalida(LocalTime.now());
            asistenciaRepository.save(registro);
        }
        return registro;
    }
}