package com.es.ProyectoAPI_Segura.util.mapper;

import com.es.ProyectoAPI_Segura.dto.PedidoDTO;
import com.es.ProyectoAPI_Segura.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidosMapper {

    public Pedido DTOToEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setFecha(pedidoDTO.getFecha());
        pedido.setCantidad(pedidoDTO.getCantidad());
        return pedido;
    }

    public PedidoDTO entityToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setIdUsuario(pedido.getUsuario().getId());
        pedidoDTO.setIdHamburguesa(pedido.getHamburguesa().getId());
        pedidoDTO.setCantidad(pedido.getCantidad());
        return pedidoDTO;
    }
}