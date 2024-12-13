package com.es.ProyectoAPI_Segura.dto;

import jakarta.persistence.Column;

public class UsuarioDTO {

    private String nombre;
    private String contrasenia;
    private String[] rol;
    private int edad;
    private String sexo;
    private String correo;

    public UsuarioDTO(String nombre, String contrasenia, int edad, String sexo, String correo,String[] rol) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.edad = edad;
        this.sexo = sexo;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String[] getRol() {
        return rol;
    }

    public void setRol(String[] rol) {
        this.rol = rol;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
