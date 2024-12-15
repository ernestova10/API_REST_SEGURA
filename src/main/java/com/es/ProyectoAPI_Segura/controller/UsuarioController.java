package com.es.ProyectoAPI_Segura.controller;

import com.es.ProyectoAPI_Segura.dto.UsuarioDTO;
import com.es.ProyectoAPI_Segura.dto.UsuarioLoginDTO;
import com.es.ProyectoAPI_Segura.dto.UsuarioRegisterDTO;
import com.es.ProyectoAPI_Segura.error.exception.GenericInternalException;
import com.es.ProyectoAPI_Segura.error.exception.NotFoundException;
import com.es.ProyectoAPI_Segura.model.Usuario;
import com.es.ProyectoAPI_Segura.service.TokenService;
import com.es.ProyectoAPI_Segura.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UsuarioRegisterDTO> crearUsuario(@RequestBody UsuarioRegisterDTO usuarioDTO) {
        UsuarioRegisterDTO nuevoUsuario = usuarioService.crearUsuario(usuarioDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String login(
            @RequestBody UsuarioLoginDTO usuarioLoginDTO
    ) {

        Authentication authentication = null;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getUsername(), usuarioLoginDTO.getPassword()));


        String token = tokenService.generateToken(authentication);


        return token;

    }

    // Obtener todos los usuarios
    @GetMapping("/")
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Obtener un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
