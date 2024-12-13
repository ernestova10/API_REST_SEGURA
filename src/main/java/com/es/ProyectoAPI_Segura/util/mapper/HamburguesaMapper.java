package com.es.ProyectoAPI_Segura.util.mapper;

import com.es.ProyectoAPI_Segura.dto.HamburguesaDTO;
import com.es.ProyectoAPI_Segura.model.Hamburguesa;
import org.springframework.stereotype.Component;

@Component
public class HamburguesaMapper {

    public HamburguesaDTO entityToDTO(Hamburguesa hamburguesa) {
        HamburguesaDTO hamburguesaDTO = new HamburguesaDTO();
        hamburguesaDTO.setNombre(hamburguesa.getNombre());
        hamburguesaDTO.setPrecio(hamburguesa.getPrecio());
        hamburguesaDTO.setTipoDeCarne(hamburguesa.getTipoDeCarne());
        hamburguesaDTO.setIngredientes(hamburguesa.getIngredientes());
        return hamburguesaDTO;
    }

    public Hamburguesa DTOToEntity(HamburguesaDTO hamburguesaDTO) {
        Hamburguesa hamburguesa = new Hamburguesa();
        hamburguesa.setNombre(hamburguesaDTO.getNombre());
        hamburguesa.setPrecio(hamburguesa.getPrecio());
        hamburguesa.setIngredientes(hamburguesaDTO.getIngredientes());
        hamburguesa.setTipoDeCarne(hamburguesa.getTipoDeCarne());
        return hamburguesa;
    }
}
