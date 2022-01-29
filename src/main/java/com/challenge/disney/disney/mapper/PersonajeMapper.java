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
    public PersonajeEntity characterDTO2Entity(PersonajeDTO dto) {
        PersonajeEntity characterEntity = new PersonajeEntity();
        characterEntity.setAge(dto.getAge());
        characterEntity.setHistory(dto.getHistory());
        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        characterEntity.setWeight(dto.getWeight());
        return characterEntity;
    }

    //=== Entity --> DTO ===
    public PersonajeDTO characterEntity2Dto(PersonajeEntity personajeEntity, boolean showCharactersMovie) {
        PersonajeDTO characterDTO = new PersonajeDTO();
        characterDTO.setId(personajeEntity.getId());
        characterDTO.setAge(personajeEntity.getAge());
        characterDTO.setHistory(personajeEntity.getHistory());
        characterDTO.setImage(personajeEntity.getImage());
        characterDTO.setName(personajeEntity.getName());
        characterDTO.setWeight(personajeEntity.getWeight());
        if (showCharactersMovie){
           characterDTO.setMoviesDTO(peliculaMapper.movieEntityList2DtoList(personajeEntity.getMovies(), false));
        }
        return characterDTO;
    }

    //=== ListEntity --> ListDto
   public List<PersonajeDTO> characterEntityList2DtoList(List<PersonajeEntity> listaEntity, boolean b){
        List<PersonajeDTO>dtoList = new ArrayList<>();
        for(PersonajeEntity ent : listaEntity){
            dtoList.add(this.characterEntity2Dto(ent, b));
        }
        return dtoList;
    }


    public List<PersonajeEntity> toEntityList(List<PersonajeDTO> personajesDTO) {
        List<PersonajeEntity>personajeEntityList = new ArrayList<>();
        for(PersonajeDTO personaje : personajesDTO){
            personajeEntityList.add(this.characterDTO2Entity(personaje));
        }
        return personajeEntityList;
    }

    //=== Basic ===
    public List<PersonajeBasicDTO> characterBasicEntityList2DtoList(List<PersonajeEntity> listaEntity) {
        List<PersonajeBasicDTO>dtoList = new ArrayList<>();
        for(PersonajeEntity ent : listaEntity){
            dtoList.add(this.personajeBasicEntity2Dto(ent));
        }
        return dtoList;
    }

    //=== Basic ===
    private PersonajeBasicDTO personajeBasicEntity2Dto(PersonajeEntity ent) {
        PersonajeBasicDTO dto = new PersonajeBasicDTO();
        dto.setName(ent.getName());
        dto.setImage(ent.getImage());
        return dto;
    }

}
