package com.challenge.disney.disney.mapper;


import com.challenge.disney.disney.dto.PersonajeBasicDTO;
import com.challenge.disney.disney.dto.PersonajeDTO;
import com.challenge.disney.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {

    //=== Mapper ==
    private  PeliculaMapper peliculaMapper;
    public PersonajeMapper(@Autowired @Lazy PeliculaMapper peliculaMapper) {
        this.peliculaMapper = peliculaMapper;
    }

    //=== DTO --> Entity ===
    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto) {
        PersonajeEntity personajeEntity = new PersonajeEntity();
        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setHistoria(dto.getHistoria());
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setPeso(dto.getPeso());
        return personajeEntity;
    }

    //=== Entity --> DTO ===
    public PersonajeDTO personajeEntity2Dto(PersonajeEntity personajeEntity, boolean mostrarPersonajesDePelicula) {
        PersonajeDTO personajeDTO = new PersonajeDTO();
        personajeDTO.setId(personajeEntity.getId());
        personajeDTO.setEdad(personajeEntity.getEdad());
        personajeDTO.setHistoria(personajeEntity.getHistoria());
        personajeDTO.setImagen(personajeEntity.getImagen());
        personajeDTO.setNombre(personajeEntity.getNombre());
        personajeDTO.setPeso(personajeEntity.getPeso());
        if (mostrarPersonajesDePelicula){
           personajeDTO.setPeliculasDTO(peliculaMapper.peliculaEntityList2DtoList(personajeEntity.getPeliculas(), false));
        }
        return personajeDTO;
    }

    //=== ListEntity --> ListDto
   public List<PersonajeDTO>personajeEntityList2DtoList(List<PersonajeEntity> listaEntity, boolean b){
        List<PersonajeDTO>dtoList = new ArrayList<>();
        for(PersonajeEntity ent : listaEntity){
            dtoList.add(this.personajeEntity2Dto(ent, b));
        }
        return dtoList;
    }


    public List<PersonajeEntity> toEntityList(List<PersonajeDTO> personajesDTO) {
        List<PersonajeEntity>personajeEntityList = new ArrayList<>();
        for(PersonajeDTO personaje : personajesDTO){
            personajeEntityList.add(this.personajeDTO2Entity(personaje));
        }
        return personajeEntityList;
    }

    //=== Basic ===
    public List<PersonajeBasicDTO> personajeBasicEntityList2DtoList(List<PersonajeEntity> listaEntity) {
        List<PersonajeBasicDTO>dtoList = new ArrayList<>();
        for(PersonajeEntity ent : listaEntity){
            dtoList.add(this.personajeBasicEntity2Dto(ent));
        }
        return dtoList;
    }

    //=== Basic ===
    private PersonajeBasicDTO personajeBasicEntity2Dto(PersonajeEntity ent) {
        PersonajeBasicDTO dto = new PersonajeBasicDTO();
        dto.setNombre(ent.getNombre());
        dto.setImagen(ent.getImagen());
        return dto;
    }
}
