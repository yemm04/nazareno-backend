package com.nazareno.portal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "asistencias", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "fecha"}))
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaEntrada;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime horaSalida;

    private String metodo; // MANUAL o CODIGO

    public Long getId() 
    { return id; }

    public Usuario getUsuario() 
    { return usuario; }

    public void setUsuario(Usuario usuario) 
    { this.usuario = usuario; }

    public LocalDate getFecha() 
    { return fecha; }

    public void setFecha(LocalDate fecha) 
    { this.fecha = fecha; }

    public LocalTime getHoraEntrada() 
    { return horaEntrada; }

    public void setHoraEntrada(LocalTime horaEntrada) 
    { this.horaEntrada = horaEntrada; }

    public LocalTime getHoraSalida() 
    { return horaSalida; }

    public void setHoraSalida(LocalTime horaSalida) 
    { this.horaSalida = horaSalida; }

    public String getMetodo() 
    { return metodo; }

    public void setMetodo(String metodo) 
    { this.metodo = metodo; }
}