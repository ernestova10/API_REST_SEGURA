package com.es.ProyectoAPI_Segura.dto;

public class UsuarioRegisterDTO {
    private String nombre;
    private String contrasenia;
    private String rol;
    private int edad;
    private String sexo;
    private String correo;

    public UsuarioRegisterDTO(String nombre, String contrasenia, String rol, int edad, String sexo, String correo) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.edad = edad;
        this.sexo = sexo;
        this.correo = correo;
    }

    public UsuarioRegisterDTO() {
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
