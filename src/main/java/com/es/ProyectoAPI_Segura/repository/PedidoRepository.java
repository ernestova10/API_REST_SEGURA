package com.es.ProyectoAPI_Segura.repository;

import com.es.ProyectoAPI_Segura.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuarioNombre(String usuario);
}
