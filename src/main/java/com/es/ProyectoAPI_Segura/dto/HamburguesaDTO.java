package com.es.ProyectoAPI_Segura.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;

import java.util.List;

public class HamburguesaDTO {
    private String nombre;
    private int precio;
    private String tipoDeCarne;
    private List<String> ingredientes;

    public HamburguesaDTO(String nombre, int precio, String tipoDeCarne, List<String> ingredientes) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoDeCarne = tipoDeCarne;
        this.ingredientes = ingredientes;
    }

    public HamburguesaDTO() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDeCarne() {
        return tipoDeCarne;
    }

    public void setTipoDeCarne(String tipoDeCarne) {
        this.tipoDeCarne = tipoDeCarne;
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


}
