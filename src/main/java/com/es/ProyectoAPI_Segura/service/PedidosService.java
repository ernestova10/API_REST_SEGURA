package com.es.ProyectoAPI_Segura.service;

import com.es.ProyectoAPI_Segura.dto.PedidoDTO;
import com.es.ProyectoAPI_Segura.error.exception.BadRequestException;
import com.es.ProyectoAPI_Segura.error.exception.NotFoundException;
import com.es.ProyectoAPI_Segura.model.Hamburguesa;
import com.es.ProyectoAPI_Segura.model.Pedido;
import com.es.ProyectoAPI_Segura.model.Usuario;
import com.es.ProyectoAPI_Segura.repository.HamburguesaRepository;
import com.es.ProyectoAPI_Segura.repository.PedidoRepository;
import com.es.ProyectoAPI_Segura.repository.UsuarioRepository;
import com.es.ProyectoAPI_Segura.util.mapper.PedidosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidosService {

    @Autowired
    private PedidoRepository pedidosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HamburguesaRepository hamburguesaRepository;

    @Autowired
    private PedidosMapper pedidosMapper;

    public PedidoDTO realizarPedido(PedidoDTO pedidoDTO) {
        // Validar existencia de usuario
        Usuario usuario = usuarioRepository.findById(pedidoDTO.getIdUsuario())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        // Validar existencia de hamburguesa
        Hamburguesa hamburguesa = hamburguesaRepository.findById(pedidoDTO.getIdHamburguesa())
                .orElseThrow(() -> new NotFoundException("Hamburguesa no encontrada"));

        // Crear y guardar el pedido
        Pedido nuevoPedido = pedidosMapper.DTOToEntity(pedidoDTO);
        nuevoPedido.setUsuario(usuario);
        nuevoPedido.setHamburguesa(hamburguesa);
        nuevoPedido.setPrecio(hamburguesa.getPrecio() * pedidoDTO.getCantidad()); // Calcular precio total

        pedidosRepository.save(nuevoPedido);

        return pedidosMapper.entityToDTO(nuevoPedido);
    }

    public List<PedidoDTO> verHistorialUsuario(String perfil) {
        Usuario usuario = usuarioRepository.findByNombre(perfil)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        List<Pedido> pedidos = pedidosRepository.findByUsuarioNombre(perfil);
        if (pedidos.isEmpty()) {
            throw new NotFoundException("No se encontraron pedidos para el usuario: " + perfil);
        }

        return pedidos.stream().map(pedidosMapper::entityToDTO).collect(Collectors.toList());
    }

    public List<PedidoDTO> verHistorialTodos() {
        List<Pedido> pedidos = pedidosRepository.findAll();
        if (pedidos.isEmpty()) {
            throw new NotFoundException("No se encontraron pedidos en el sistema");
        }

        return pedidos.stream().map(pedidosMapper::entityToDTO).collect(Collectors.toList());
    }

    public PedidoDTO actualizarPedido(String id, PedidoDTO pedidoDTO) {
        Long pedidoId;
        try {
            pedidoId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("El formato de la ID debe ser numérico");
        }

        Pedido pedido = pedidosRepository.findById(pedidoId)
                .orElseThrow(() -> new NotFoundException("Pedido no encontrado"));

        if (pedidoDTO.getCantidad() <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor a 0");
        }

        pedido.setCantidad(pedidoDTO.getCantidad());
        pedido.setPrecio(pedido.getHamburguesa().getPrecio() * pedidoDTO.getCantidad()); // Recalcular precio

        pedidosRepository.save(pedido);

        return pedidosMapper.entityToDTO(pedido);
    }

    public void eliminarPedido(String id) {
        Long pedidoId;
        try {
            pedidoId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("El formato de la ID debe ser numérico");
        }

        Pedido pedido = pedidosRepository.findById(pedidoId)
                .orElseThrow(() -> new NotFoundException("Pedido no encontrado"));

        pedidosRepository.delete(pedido);
    }
}

