package com.es.ProyectoAPI_Segura.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "hamburguesa_id", nullable = false)
    private Hamburguesa hamburguesa;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private int cantidad;

    public Pedido() {
    }

    public Pedido(Usuario usuario, Hamburguesa hamburguesa, LocalDate fecha, int cantidad) {
        this.usuario = usuario;
        this.hamburguesa = hamburguesa;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Hamburguesa getHamburguesa() {
        return hamburguesa;
    }

    public void setHamburguesa(Hamburguesa hamburguesa) {
        this.hamburguesa = hamburguesa;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(int i) {

    }
}