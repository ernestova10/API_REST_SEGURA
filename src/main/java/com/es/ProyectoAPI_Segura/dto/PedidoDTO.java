package com.es.ProyectoAPI_Segura.dto;

import java.time.LocalDate;
import java.util.Date;

public class PedidoDTO {
    private Long idUsuario;      // ID del usuario que realiza el pedido
    private Long idHamburguesa;  // ID de la hamburguesa pedida
    private String fecha;          // Fecha del pedido
    private int cantidad;   // Cantidad de hamburguesas pedidas
    private int precio;

    // Constructor sin argumentos
    public PedidoDTO() {
    }

    // Constructor con argumentos
    public PedidoDTO(Long idUsuario, Long idHamburguesa, String fecha, int cantidad) {
        this.idUsuario = idUsuario;
        this.idHamburguesa = idHamburguesa;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public PedidoDTO(Long idUsuario, int precio, int cantidad, String fecha, Long idHamburguesa) {
        this.idUsuario = idUsuario;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.idHamburguesa = idHamburguesa;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
