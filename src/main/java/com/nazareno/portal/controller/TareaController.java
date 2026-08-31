package com.nazareno.portal.controller;

import com.nazareno.portal.model.Tarea;
import com.nazareno.portal.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "http://localhost:5173")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public List<Tarea> listarTodas() {
        return tareaService.listarTodas();
    }

    @PostMapping
    public Tarea crear(@Valid @RequestBody Tarea tarea) {
        return tareaService.crear(tarea);
    }

    @PatchMapping("/{id}/tomar")
    public Tarea tomar(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        Long usuarioId = Long.valueOf(body.get("usuarioId").toString());
        return tareaService.tomar(id, usuarioId);
    }

    @PatchMapping("/{id}/completar")
    public Tarea completar(@PathVariable Long id) {
        return tareaService.completar(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
    }
}