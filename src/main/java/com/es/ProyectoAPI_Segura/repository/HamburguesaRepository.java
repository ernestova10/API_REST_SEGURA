package com.es.ProyectoAPI_Segura.repository;

import com.es.ProyectoAPI_Segura.model.Hamburguesa;
import com.es.ProyectoAPI_Segura.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HamburguesaRepository extends JpaRepository<Hamburguesa, Long> {
    Optional<Hamburguesa> findByName(String nombre);
}
