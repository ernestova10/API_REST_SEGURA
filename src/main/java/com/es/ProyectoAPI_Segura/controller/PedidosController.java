package com.es.ProyectoAPI_Segura.controller;

import com.es.ProyectoAPI_Segura.dto.PedidoDTO;
import com.es.ProyectoAPI_Segura.service.PedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosService pedidosService;

    // Realizar pedidos
    @PostMapping("/")
    public ResponseEntity<PedidoDTO> realizarPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO nuevoPedido = pedidosService.realizarPedido(pedidoDTO);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    // Ver historial de pedidos del usuario autenticado
    @GetMapping("/historial/{perfil}")
    public ResponseEntity<List<PedidoDTO>> verHistorialUsuario(@PathVariable String perfil) {
        List<PedidoDTO> historial = pedidosService.verHistorialUsuario(perfil);
        return new ResponseEntity<>(historial, HttpStatus.OK);
    }

    // Ver historial de todos los pedidos
    @GetMapping("/admin/historial")
    public ResponseEntity<List<PedidoDTO>> verHistorialTodos() {
        List<PedidoDTO> historial = pedidosService.verHistorialTodos();
        return new ResponseEntity<>(historial, HttpStatus.OK);
    }

    // Actualizar pedidos
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> actualizarPedido(@PathVariable String id, @RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO pedidoActualizado = pedidosService.actualizarPedido(id, pedidoDTO);
        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }

    // Eliminar pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable String id) {
        pedidosService.eliminarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

