package com.es.ProyectoAPI_Segura.dto;

import java.time.LocalDate;
import java.util.Date;

public class PedidoDTO {
    private Long idUsuario;      // ID del usuario que realiza el pedido
    private Long idHamburguesa;  // ID de la hamburguesa pedida
    private LocalDate fecha;          // Fecha del pedido
    private int precio;          // Precio total del pedido
    private int cantidad;        // Cantidad de hamburguesas pedidas

    // Constructor sin argumentos
    public PedidoDTO() {
    }

    // Constructor con argumentos
    public PedidoDTO(Long idUsuario, Long idHamburguesa, LocalDate fecha, int precio, int cantidad) {
        this.idUsuario = idUsuario;
        this.idHamburguesa = idHamburguesa;
        this.fecha = fecha;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdHamburguesa() {
        return idHamburguesa;
    }

    public void setIdHamburguesa(Long idHamburguesa) {
        this.idHamburguesa = idHamburguesa;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
