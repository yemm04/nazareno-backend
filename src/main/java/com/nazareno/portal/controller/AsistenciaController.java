package com.nazareno.portal.controller;

import com.nazareno.portal.model.Asistencia;
import com.nazareno.portal.service.AsistenciaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/asistencias")
@CrossOrigin(origins = "http://localhost:5173")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    public AsistenciaController(AsistenciaService asistenciaService) {
        this.asistenciaService = asistenciaService;
    }

    @GetMapping
    public List<Asistencia> listarTodas() {
        return asistenciaService.listarTodas();
    }

    @PostMapping("/entrada")
    public Asistencia marcarEntrada(@RequestBody Map<String, Object> body) {
        Long usuarioId = Long.valueOf(body.get("usuarioId").toString());
        String metodo = (String) body.getOrDefault("metodo", "MANUAL");
        return asistenciaService.marcarEntrada(usuarioId, metodo);
    }

    @PostMapping("/salida")
    public Asistencia marcarSalida(@RequestBody Map<String, Object> body) {
        Long usuarioId = Long.valueOf(body.get("usuarioId").toString());
        return asistenciaService.marcarSalida(usuarioId);
    }
}