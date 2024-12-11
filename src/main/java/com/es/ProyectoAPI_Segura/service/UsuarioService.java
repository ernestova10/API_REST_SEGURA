package com.es.ProyectoAPI_Segura.service;

import com.es.ProyectoAPI_Segura.dto.UsuarioDTO;
import com.es.ProyectoAPI_Segura.error.exception.DuplicateException;
import com.es.ProyectoAPI_Segura.model.Usuario;
import com.es.ProyectoAPI_Segura.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        // Comprobamos que el usuario no existe en la base de datos
        if (usuarioRepository.findByUsername(usuarioDTO.getUsername()).isPresent()) {
            throw new DuplicateException("El nombre de usuario ya existe");
        }

        Usuario usuario = new Usuario();
        usuario.setContrasenia(passwordEncoder.encode(usuarioDTO.getContrasenia()));
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEdad(usuarioDTO.getEdad());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setRol(usuarioDTO.getRol());
        usuario.setSexo(usuarioDTO.getSexo());

        usuarioRepository.save(usuario);

        return usuarioDTO;
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
