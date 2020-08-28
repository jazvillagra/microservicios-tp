package com.example.golden.dto;

import org.springframework.web.multipart.MultipartFile;

public class PersonaDTO {
    private String id;
    private String nombres;
    private String apellidos;
    private MultipartFile imagen;

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

    public MultipartFile getImagen() {
        return imagen;
    }

    public void setImagen(MultipartFile imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "PersonaDTO{" +
                "id='" + id + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", imagen=" + imagen +
                '}';
    }
}
