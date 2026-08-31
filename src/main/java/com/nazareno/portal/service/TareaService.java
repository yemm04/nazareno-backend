package com.nazareno.portal.service;

import com.nazareno.portal.model.EstadoTarea;
import com.nazareno.portal.model.Tarea;
import com.nazareno.portal.model.Usuario;
import com.nazareno.portal.repository.TareaRepository;
import com.nazareno.portal.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;

    public TareaService(TareaRepository tareaRepository, UsuarioRepository usuarioRepository) {
        this.tareaRepository = tareaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Tarea> listarTodas() {
        return tareaRepository.findAll();
    }

    public Tarea crear(Tarea datos) {
        datos.setEstado(EstadoTarea.PENDIENTE);
        datos.setTomadaPor(null);
        return tareaRepository.save(datos);
    }

    public Tarea tomar(Long tareaId, Long usuarioId) {
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new NoSuchElementException("Tarea no encontrada."));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado."));
        tarea.setTomadaPor(usuario);
        return tareaRepository.save(tarea);
    }

    public Tarea completar(Long tareaId) {
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new NoSuchElementException("Tarea no encontrada."));
        tarea.setEstado(EstadoTarea.COMPLETADA);
        return tareaRepository.save(tarea);
    }

    public void eliminar(Long id) {
        tareaRepository.deleteById(id);
    }
}