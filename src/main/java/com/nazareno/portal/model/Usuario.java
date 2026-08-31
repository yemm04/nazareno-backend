package com.nazareno.portal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigo; 

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotBlank
    @Column(unique = true)
    private String dni;

    private String genero;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String direccion;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Rol rol;

    private String area;
    private LocalDate fechaIngreso;
    private String estado; // ACTIVO / INACTIVO
    @JsonIgnore
    private String password;
    @Email
    private String correo;

    
    public Long getId()     
        { return id; }
    public void setId(Long id) 
        { this.id = id; }

    public String getCodigo() 
        { return codigo; }
    public void setCodigo(String codigo) 
        { this.codigo = codigo; }

    public String getNombre() 
        { return nombre; }
    public void setNombre(String nombre) 
        { this.nombre = nombre; }

    public String getApellido() 
        { return apellido; }
    public void setApellido(String apellido) 
        { this.apellido = apellido; }

    public String getDni() 
        { return dni; }
    public void setDni(String dni) 
        { this.dni = dni; }

    public String getGenero() 
        { return genero; }
    public void setGenero(String genero) 
        { this.genero = genero; }

    public LocalDate getFechaNacimiento() 
        { return fechaNacimiento; }

    public void setFechaNacimiento(LocalDate fechaNacimiento) 
        { this.fechaNacimiento = fechaNacimiento; }

    public String getTelefono() 
        { return telefono; }

    public void setTelefono(String telefono) 
        { this.telefono = telefono; }

    public String getDireccion() 
        { return direccion; }

    public void setDireccion(String direccion) 
        { this.direccion = direccion; }

    public Rol getRol() 
        { return rol; }

    public void setRol(Rol rol) 
        { this.rol = rol; }

    public String getArea() 
        { return area; }

    public void setArea(String area) 
        { this.area = area; }

    public LocalDate getFechaIngreso() 
        { return fechaIngreso; }

    public void setFechaIngreso(LocalDate fechaIngreso) 
        { this.fechaIngreso = fechaIngreso; }

    public String getEstado() 
        { return estado; }

    public void setEstado(String estado) 
        { this.estado = estado; }

    public String getPassword() 
        { return password; }

    public void setPassword(String password)
         { this.password = password; }
    
    public String getCorreo() 
        { return correo; }
        
    public void setCorreo(String correo) 
        { this.correo = correo; }
}