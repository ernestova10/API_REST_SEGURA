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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // BUSCO EL USUARIO POR SU NOMBRE EN LA BDD
        Usuario usuario = usuarioRepository
                .findByNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario No encontrado"));

        /* RETORNAMOS UN USERDETAILS
        El método loadUserByUsername nos fuerza a devolver un objeto de tipo UserDetails.
        Tenemos que convertir nuestro objeto de tipo Usuario a un objeto de tipo UserDetails
        ¡No os preocupéis, esto es siempre igual!
         */
        List<GrantedAuthority> authorities = Arrays.stream(usuario.getRol().split(","))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                .collect(Collectors.toList());

        UserDetails userDetails = User // User pertenece a SpringSecurity
                .builder()
                .username(usuario.getNombre())
                .password(usuario.getContrasenia())
                .roles(usuario.getRol())
                .build();

        return userDetails;
    }


    public UsuarioRegisterDTO crearUsuario(UsuarioRegisterDTO usuarioDTO) {
        // Comprobamos que el usuario no existe en la base de datos
        if (usuarioRepository.findByNombre(usuarioDTO.getNombre()).isPresent()) {
            throw new DuplicateException("El nombre de usuario ya existe");
        }


        Usuario usuario = new Usuario();
        usuario.setContrasenia(passwordEncoder.encode(usuarioDTO.getContrasenia()));
        if (usuarioDTO.getNombre() == null || usuarioDTO.getNombre().isEmpty()) {
            throw new NotFoundException("Nombre de usuario no encontrado");
        }
        usuario.setNombre(usuarioDTO.getNombre());
        if (usuarioDTO.getEdad() <= 0)
            throw new GenericInternalException("La edad de usuario no puede ser menor o igual a 0");
        usuario.setEdad(usuarioDTO.getEdad());

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);

        // Comprobar si el correo del DTO es válido
        if (usuarioDTO.getCorreo() != null && pattern.matcher(usuarioDTO.getCorreo()).matches()) {
            usuario.setCorreo(usuarioDTO.getCorreo());
            System.out.println("Correo válido asignado: " + usuario.getCorreo());
        } else {
            System.out.println("El correo proporcionado no es válido.");
        }
        usuario.setRol(usuarioDTO.getRol());

        if (usuarioDTO.getSexo() == null || usuarioDTO.getSexo().isEmpty() ||
                (!usuarioDTO.getSexo().equalsIgnoreCase("masculino") &&
                        !usuarioDTO.getSexo().equalsIgnoreCase("femenino") &&
                        !usuarioDTO.getSexo().equalsIgnoreCase("otro"))) {
            throw new GenericInternalException("El sexo de usuario no es válido");
        }


        usuario.setSexo(usuarioDTO.getSexo());

        usuarioRepository.save(usuario);

        return usuarioDTO;
    }


    public List<UsuarioDTO> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarioRepository.findAll().forEach(usuario -> usuarios.add(usuario));

        return usuarios.stream().map(usuario -> mapper.entityToDTO(usuario)).toList();
    }

    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        Usuario u = usuarioRepository.findById(id).orElse(null);
        if (u == null) {
            throw new NotFoundException("Usuario no encontrado");
        }
        return mapper.entityToDTO(u);
    }

    public UsuarioDTO actualizarUsuario(Long id, Usuario usuario) {
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

        usuarioRepository.save(usuarioExistente);

        return mapper.entityToDTO(usuarioExistente);
    }

    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new NotFoundException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }



}
