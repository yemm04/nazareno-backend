package com.nazareno.portal.model;

public enum Rol {
    PRACTICANTE("P"),
    COORDINADOR("C"),
    ADMIN("A");

    private final String prefijo;

    Rol(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getPrefijo() {
        return prefijo;
    }
}