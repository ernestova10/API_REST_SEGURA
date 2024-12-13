package com.es.ProyectoAPI_Segura.util.mapper;

import com.es.ProyectoAPI_Segura.dto.UsuarioDTO;
import com.es.ProyectoAPI_Segura.model.Usuario;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO entityToDTO(Usuario usuario) {

        String[] roles = usuario.getRol().split(",");
        return new UsuarioDTO(
              usuario.getNombre(),
              usuario.getContrasenia(),
              usuario.getEdad(),
              usuario.getSexo(),
              usuario.getCorreo(),
              roles
        );


    }
}
