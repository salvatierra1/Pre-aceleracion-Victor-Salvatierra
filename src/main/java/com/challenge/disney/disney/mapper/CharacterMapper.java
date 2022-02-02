package com.challenge.disney.disney.mapper;


import com.challenge.disney.disney.dto.CharacterBasicDTO;
import com.challenge.disney.disney.dto.CharacterDTO;
import com.challenge.disney.disney.entity.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    //=== Mapper ==
    private MovieMapper movieMapper;
    public CharacterMapper(@Autowired @Lazy MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    //=== DTO --> Entity ===
    public CharacterEntity personajeDTO2Entity(CharacterDTO dto) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setEdad(dto.getEdad());
        characterEntity.setHistoria(dto.getHistoria());
        characterEntity.setImagen(dto.getImagen());
        characterEntity.setNombre(dto.getNombre());
        characterEntity.setPeso(dto.getPeso());
        return characterEntity;
    }

    //=== Entity --> DTO ===
    public CharacterDTO personajeEntity2Dto(CharacterEntity characterEntity, boolean mostrarPersonajesDePelicula) {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setId(characterEntity.getId());
        characterDTO.setEdad(characterEntity.getEdad());
        characterDTO.setHistoria(characterEntity.getHistoria());
        characterDTO.setImagen(characterEntity.getImagen());
        characterDTO.setNombre(characterEntity.getNombre());
        characterDTO.setPeso(characterEntity.getPeso());
        if (mostrarPersonajesDePelicula){
           characterDTO.setPeliculasDTO(movieMapper.peliculaEntityList2DtoList(characterEntity.getPeliculas(), false));
        }
        return characterDTO;
    }

    //=== ListEntity --> ListDto
   public List<CharacterDTO>personajeEntityList2DtoList(List<CharacterEntity> listaEntity, boolean b){
        List<CharacterDTO>dtoList = new ArrayList<>();
        for(CharacterEntity ent : listaEntity){
            dtoList.add(this.personajeEntity2Dto(ent, b));
        }
        return dtoList;
    }


    public List<CharacterEntity> toEntityList(List<CharacterDTO> personajesDTO) {
        List<CharacterEntity> characterEntityList = new ArrayList<>();
        for(CharacterDTO personaje : personajesDTO){
            characterEntityList.add(this.personajeDTO2Entity(personaje));
        }
        return characterEntityList;
    }

    //=== Basic ===
    public List<CharacterBasicDTO> personajeBasicEntityList2DtoList(List<CharacterEntity> listaEntity) {
        List<CharacterBasicDTO>dtoList = new ArrayList<>();
        for(CharacterEntity ent : listaEntity){
            dtoList.add(this.personajeBasicEntity2Dto(ent));
        }
        return dtoList;
    }

    //=== Basic ===
    private CharacterBasicDTO personajeBasicEntity2Dto(CharacterEntity ent) {
        CharacterBasicDTO dto = new CharacterBasicDTO();
        dto.setNombre(ent.getNombre());
        dto.setImagen(ent.getImagen());
        return dto;
    }
}
