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
    public CharacterEntity characterDTO2Entity(CharacterDTO dto) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setAge(dto.getAge());
        characterEntity.setHistory(dto.getHistory());
        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        characterEntity.setWeight(dto.getWeight());
        return characterEntity;
    }

    //=== Entity --> DTO ===
    public CharacterDTO characterEntity2Dto(CharacterEntity characterEntity, boolean showCharactersMovies) {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setId(characterEntity.getId());
        characterDTO.setAge(characterEntity.getAge());
        characterDTO.setHistory(characterEntity.getHistory());
        characterDTO.setImage(characterEntity.getImage());
        characterDTO.setName(characterEntity.getName());
        characterDTO.setWeight(characterEntity.getWeight());
        if (showCharactersMovies){
           characterDTO.setMoviesDTO(movieMapper.movieEntityList2DtoList(characterEntity.getMovies(), false));
        }
        return characterDTO;
    }

    //=== ListEntity --> ListDto
   public List<CharacterDTO> characterEntityList2DtoList(List<CharacterEntity> listEntity, boolean b){
        List<CharacterDTO>dtoList = new ArrayList<>();
        for(CharacterEntity ent : listEntity){
            dtoList.add(this.characterEntity2Dto(ent, b));
        }
        return dtoList;
    }

    public List<CharacterEntity> toEntityList(List<CharacterDTO> characterDTO) {
        List<CharacterEntity> characterEntityList = new ArrayList<>();
        for(CharacterDTO character : characterDTO){
            characterEntityList.add(this.characterDTO2Entity(character));
        }
        return characterEntityList;
    }

    //=== Basic ===
    public List<CharacterBasicDTO> characterBasicEntityList2DtoList(List<CharacterEntity> listaEntity) {
        List<CharacterBasicDTO>dtoList = new ArrayList<>();
        for(CharacterEntity ent : listaEntity){
            dtoList.add(this.characterBasicEntity2Dto(ent));
        }
        return dtoList;
    }

    //=== Basic ===
    private CharacterBasicDTO characterBasicEntity2Dto(CharacterEntity ent) {
        CharacterBasicDTO dto = new CharacterBasicDTO();
        dto.setName(ent.getName());
        dto.setImage(ent.getImage());
        return dto;
    }

}
