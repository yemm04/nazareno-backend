package com.nazareno.portal.dto;

public class LoginRequest {
    private String codigo;
    private String password;

    public String getCodigo()   
    { return codigo; }

    public void setCodigo(String codigo) 
    { this.codigo = codigo; }

    public String getPassword() 
    { return password; }

    public void setPassword(String password) 
    { this.password = password; }
}