package com.es.ProyectoAPI_Segura.repository;

import com.es.ProyectoAPI_Segura.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String username);
}