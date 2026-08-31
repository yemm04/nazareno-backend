package com.nazareno.portal.dto;

public class LoginResponse {
    private final String token;
    private final Long id;
    private final String codigo;
    private final String nombre;
    private final String apellido;
    private final String rol;
    private final String area;

    public LoginResponse(String token, Long id, String codigo, String nombre, String apellido, String rol, String area) {
        this.token = token;
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.area = area;
    }

    public String getToken() 
    { return token; }

    public Long getId() 
    { return id; }

    public String getCodigo() 
    { return codigo; }

    public String getNombre() 
    { return nombre; }

    public String getApellido() 
    { return apellido; }

    public String getRol() 
    { return rol; }

    public String getArea() 
    { return area; }
}