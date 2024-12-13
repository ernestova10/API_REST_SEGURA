package com.es.ProyectoAPI_Segura.controller;

import com.es.ProyectoAPI_Segura.dto.HamburguesaDTO;
import com.es.ProyectoAPI_Segura.dto.UsuarioDTO;
import com.es.ProyectoAPI_Segura.model.Usuario;
import com.es.ProyectoAPI_Segura.service.HamburguesaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hamburguesas")
public class HamburguesaController {

    @Autowired
    private HamburguesaService hamburguesaService;

    @PostMapping
    public ResponseEntity<HamburguesaDTO> crearHamburguesa(@RequestBody HamburguesaDTO hamburguesaDTO) {
        HamburguesaDTO nuevaHamburguesa = hamburguesaService.crearHamburguesa(hamburguesaDTO);
        return new ResponseEntity<>(nuevaHamburguesa, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HamburguesaDTO>> obtenerUsuarios() {
        List<HamburguesaDTO> hamburguesas = hamburguesaService.obtenerHamburguesas();
        return new ResponseEntity<>(hamburguesas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HamburguesaDTO> actualizarHamburguesa(@PathVariable String id, @RequestBody HamburguesaDTO hamburguesaDTO) {
        HamburguesaDTO hamburguesaActualizada = hamburguesaService.actualizarHamburguesa(id, hamburguesaDTO);
        return new ResponseEntity<>(hamburguesaActualizada, HttpStatus.OK);
    }
}
