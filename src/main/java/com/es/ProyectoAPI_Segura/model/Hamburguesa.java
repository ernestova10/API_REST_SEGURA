package com.es.ProyectoAPI_Segura.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "hamburguesas")
public class Hamburguesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private int precio;
    @Column(nullable = false)
    private String tipoDeCarne;

    @ElementCollection
    @Column(nullable = false)
    private List<String> ingredientes;

    //Relacion con pedidos
    @OneToMany(mappedBy = "hamburguesa", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    public Hamburguesa(String nombre, int precio, String tipoDeCarne, List<String> ingredientes) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoDeCarne = tipoDeCarne;
        this.ingredientes = ingredientes;
    }

    public Hamburguesa() {
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getTipoDeCarne() {
        return tipoDeCarne;
    }

    public void setTipoDeCarne(String tipoDeCarne) {
        this.tipoDeCarne = tipoDeCarne;
    }

}
