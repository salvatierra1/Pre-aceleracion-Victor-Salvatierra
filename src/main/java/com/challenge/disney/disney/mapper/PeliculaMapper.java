package com.challenge.disney.disney.mapper;

import com.challenge.disney.disney.dto.PeliculaBasicDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;
import com.challenge.disney.disney.entity.GeneroEntity;
import com.challenge.disney.disney.entity.PeliculaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
public class PeliculaMapper {

    //=== Mapper ===
    private  PersonajeMapper personajeMapper;
    public PeliculaMapper(@Autowired @Lazy PersonajeMapper personajeMapper) {
        this.personajeMapper = personajeMapper;
    }

    //=== DTO --> Entity
    public PeliculaEntity toEntity(PeliculaDTO dto, GeneroEntity generoEntity){
        PeliculaEntity peliculaEntity = new PeliculaEntity();
        peliculaEntity.setImagen(dto.getImagen());
        peliculaEntity.setTitulo(dto.getTitulo());
        peliculaEntity.setCalificacion(dto.getCalificacion());
        peliculaEntity.setFechaCreacion(this.String2LocalDate(dto.getFechaCreacion()));
        peliculaEntity.setPersonajes(personajeMapper.toEntityList(dto.getPersonajesDTO()));
        peliculaEntity.setGenero(generoEntity);
        return peliculaEntity;
    }

    //=== Entity --> DTO
    public PeliculaDTO peliculaEntity2Dto(PeliculaEntity peliculaEntity, boolean mostrarPelisDePersonaje){
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        peliculaDTO.setId(peliculaEntity.getId());
        peliculaDTO.setImagen(peliculaEntity.getImagen());
        peliculaDTO.setTitulo(peliculaEntity.getTitulo());
        peliculaDTO.setCalificacion(peliculaEntity.getCalificacion());
        peliculaDTO.setFechaCreacion(peliculaEntity.getFechaCreacion().toString());
        peliculaDTO.setGeneroId(peliculaEntity.getGenero().getId());
        peliculaDTO.setPersonajesDTO(personajeMapper.personajeEntityList2DtoList(peliculaEntity.getPersonajes(), false));
        return peliculaDTO;
    }

    //=== ListEntity --> ListDto ===
    public List<PeliculaDTO> peliculaEntityList2DtoList(List<PeliculaEntity> listaEntity, boolean mostrarPelisDePersonajes){
        List<PeliculaDTO>dtoList = new ArrayList<>();
            for(PeliculaEntity ent : listaEntity) {
                dtoList.add(this.peliculaEntity2Dto(ent, false));
            }
        return dtoList;
    }

    //=== Date ===
    public LocalDate String2LocalDate(String stringDate) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formato);
        return date;
    }

    //=== Basic ===
    public List<PeliculaBasicDTO> peliculaBasicEntityList2DtoList(List<PeliculaEntity> listaEntity) {
        List<PeliculaBasicDTO>dtoList = new ArrayList<>();
        for(PeliculaEntity ent : listaEntity){
            dtoList.add(this.peliculaBasicEntity2Dto(ent));
        }
        return dtoList;
    }

    private PeliculaBasicDTO peliculaBasicEntity2Dto(PeliculaEntity ent) {
        PeliculaBasicDTO peliculaBasicDTO = new PeliculaBasicDTO();
        peliculaBasicDTO.setImagen(ent.getImagen());
        peliculaBasicDTO.setTitulo(ent.getTitulo());
        peliculaBasicDTO.setFechaCreacion(ent.getFechaCreacion().toString());
        return peliculaBasicDTO;
    }

}
