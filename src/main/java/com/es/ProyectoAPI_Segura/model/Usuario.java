package com.es.ProyectoAPI_Segura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false, name = "contraseña")
    private String contrasenia;
    @Column(nullable = false, name = "roles")
    private String rol;
    @Column(nullable = false)
    private int edad;
    @Column(nullable = false)
    private String sexo;
    @Column(nullable = false)
    private String correo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    public Usuario(String nombre, String contrasenia, String rol, int edad, String sexo, String correo) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.edad = edad;
        this.sexo = sexo;
        this.correo = correo;
    }

    public Usuario() {
    }

    public Usuario(List<Pedido> pedidos, String correo, String sexo, int edad, String rol, String contrasenia, String nombre) {
        this.pedidos = pedidos;
        this.correo = correo;
        this.sexo = sexo;
        this.edad = edad;
        this.rol = rol;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
