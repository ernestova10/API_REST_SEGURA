package com.es.ProyectoAPI_Segura.service;

import com.es.ProyectoAPI_Segura.dto.HamburguesaDTO;
import com.es.ProyectoAPI_Segura.error.exception.BadRequestException;
import com.es.ProyectoAPI_Segura.error.exception.DuplicateException;
import com.es.ProyectoAPI_Segura.error.exception.NotFoundException;
import com.es.ProyectoAPI_Segura.model.Hamburguesa;

import com.es.ProyectoAPI_Segura.repository.HamburguesaRepository;
import com.es.ProyectoAPI_Segura.util.mapper.HamburguesaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HamburguesaService {


    @Autowired
    private HamburguesaRepository hamburguesaRepository;


    public HamburguesaDTO crearHamburguesa(HamburguesaDTO hamburguesaDTO) {

        if (hamburguesaRepository.findByNombre(hamburguesaDTO.getNombre()).isPresent()) {
            throw new DuplicateException("El nombre de la hamburguesa ya existe");
        }
        if (hamburguesaDTO.getNombre().isEmpty()) {
            throw new BadRequestException("El nombre de la hamburguesa no puede estar vacio");
        }

        // El mapper está implementado pero no funciona, sim embargo, sin tener que utilizar el mapper y haciendolo aquí si funciona.
        Hamburguesa nuevaHamburguesa = new Hamburguesa();
        nuevaHamburguesa.setNombre(hamburguesaDTO.getNombre());
        nuevaHamburguesa.setPrecio(hamburguesaDTO.getPrecio());
        nuevaHamburguesa.setTipoDeCarne(hamburguesaDTO.getTipoDeCarne());
        nuevaHamburguesa.setIngredientes(hamburguesaDTO.getIngredientes());
        hamburguesaRepository.save(nuevaHamburguesa);

        return hamburguesaDTO;
    }


    public List<HamburguesaDTO> obtenerHamburguesas() {
        List<Hamburguesa> hamburguesas = hamburguesaRepository.findAll();
        List<HamburguesaDTO> hamburguesasDTO = new ArrayList<>();

        for (Hamburguesa hamburguesa : hamburguesas) {
            HamburguesaDTO hamburguesaDTO = HamburguesaMapper.entityToDTO(hamburguesa);
            hamburguesasDTO.add(hamburguesaDTO);

        }
        if (hamburguesas.isEmpty()) {
            throw new NotFoundException("No hay hamburguesas en la base de datos");
        }
        return hamburguesasDTO;
    }


    public HamburguesaDTO actualizarHamburguesa(String id, HamburguesaDTO hamburguesaDTO) {
        Long idL = 0L;
        try{
            idL = Long.parseLong(id);
        }catch (NumberFormatException e){
            throw new NumberFormatException("El formato de la ID debe ser numérico");
        }

        Hamburguesa hamburguesa = hamburguesaRepository.findById(idL).orElseThrow(() -> new NotFoundException("Hamburguesa no encontrada"));

        if (hamburguesaDTO.getNombre().isEmpty()){
            throw new BadRequestException("El nombre de la hamburguesa no puede estar vacío.");
        }
        hamburguesa.setNombre(hamburguesaDTO.getNombre());

        if (hamburguesaDTO.getIngredientes().isEmpty()){
            throw new BadRequestException("Ingredientes no puede ser vacío.");
        }
        hamburguesa.setIngredientes(hamburguesaDTO.getIngredientes());

        if (hamburguesaDTO.getPrecio() < 0){
            throw new NumberFormatException("Precio no puede ser negativo.");
        }
        hamburguesa.setPrecio(hamburguesaDTO.getPrecio());

        if (hamburguesaDTO.getTipoDeCarne().isEmpty()){
            throw new BadRequestException("Tipo de carne no puede ser vacio");
        }
        hamburguesa.setTipoDeCarne(hamburguesaDTO.getTipoDeCarne());

        HamburguesaDTO hamburguesaActualizada = HamburguesaMapper.entityToDTO(hamburguesa);

        hamburguesaRepository.save(hamburguesa);
        return hamburguesaActualizada;

    }

    public void eliminarHamburguesa(String id) {
        Long idL;
        try {
            idL = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El formato de la ID debe ser numérico");
        }

        Hamburguesa hamburguesa = hamburguesaRepository.findById(idL)
                .orElseThrow(() -> new NotFoundException("Hamburguesa no encontrada"));

        hamburguesaRepository.delete(hamburguesa);
    }


}
