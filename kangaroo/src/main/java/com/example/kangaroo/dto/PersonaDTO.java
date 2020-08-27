package com.example.kangaroo.dto;

import java.util.Arrays;

public class PersonaDTO {
    private String id;
    private String nombres;
    private String apellidos;
    private byte[] imagen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "PersonaDTO{" +
                "nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", id='" + id + '\'' +
                ", imagen=" + Arrays.toString(imagen) +
                '}';
    }
}
