package com.es.ProyectoAPI_Segura.service;

import com.es.ProyectoAPI_Segura.dto.UsuarioDTO;
import com.es.ProyectoAPI_Segura.dto.UsuarioLoginDTO;
import com.es.ProyectoAPI_Segura.dto.UsuarioRegisterDTO;
import com.es.ProyectoAPI_Segura.error.exception.DuplicateException;
import com.es.ProyectoAPI_Segura.error.exception.GenericInternalException;
import com.es.ProyectoAPI_Segura.error.exception.NotFoundException;
import com.es.ProyectoAPI_Segura.model.Usuario;
import com.es.ProyectoAPI_Segura.repository.UsuarioRepository;
import com.es.ProyectoAPI_Segura.util.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper mapper;

    public UsuarioRegisterDTO crearUsuario(UsuarioRegisterDTO usuarioDTO) {
        // Comprobamos que el usuario no existe en la base de datos
        if (usuarioRepository.findByNombre(usuarioDTO.getNombre()).isPresent()) {
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


    public List<UsuarioDTO> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarioRepository.findAll().forEach(usuario -> usuarios.add(usuario));

        return usuarios.stream().map(usuario -> mapper.entityToDTO(usuario)).toList();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new NotFoundException("Usuario no encontrado");
        }

        Usuario usuarioExistente = usuarioRepository.findById(id).get();
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setCorreo(usuario.getCorreo());
        usuarioExistente.setEdad(usuario.getEdad());
        usuarioExistente.setSexo(usuario.getSexo());
        usuarioExistente.setRol(usuario.getRol());

        if (usuario.getContrasenia() != null && !usuario.getContrasenia().isEmpty()) {
            usuarioExistente.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
        }

        return usuarioRepository.save(usuarioExistente);
    }

    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new NotFoundException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
