package com.nazareno.portal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "tareas")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    private LocalDate fecha;

    private String tiempoEstimado;

    @Enumerated(EnumType.STRING)
    private EstadoTarea estado;

    @ManyToOne
    @JoinColumn(name = "tomada_por_id")
    private Usuario tomadaPor;

    public Long getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getTiempoEstimado() { return tiempoEstimado; }
    public void setTiempoEstimado(String tiempoEstimado) { this.tiempoEstimado = tiempoEstimado; }

    public EstadoTarea getEstado() { return estado; }
    public void setEstado(EstadoTarea estado) { this.estado = estado; }

    public Usuario getTomadaPor() { return tomadaPor; }
    public void setTomadaPor(Usuario tomadaPor) { this.tomadaPor = tomadaPor; }
}