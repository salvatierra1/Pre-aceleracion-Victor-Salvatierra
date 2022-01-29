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
    public PeliculaEntity movieDTO2Entity(PeliculaDTO dto){
        GeneroEntity genderEntity = new GeneroEntity();
        genderEntity.setId(dto.getGenderId());
        PeliculaEntity movieEntity = new PeliculaEntity();
        movieEntity.setImage(dto.getImage());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setQualification(dto.getQualification());
        movieEntity.setDateCreation(this.String2LocalDate(dto.getDateCreation()));
        movieEntity.setCharacters(personajeMapper.toEntityList(dto.getCharactersDTO()));
        movieEntity.setGenderEntity(genderEntity);
        return movieEntity;
    }

    //=== Entity --> DTO
    public PeliculaDTO movieEntity2Dto(PeliculaEntity peliculaEntity, boolean showMoviesCharacter){
        PeliculaDTO movieDTO = new PeliculaDTO();
        movieDTO.setId(peliculaEntity.getId());
        movieDTO.setImage(peliculaEntity.getImage());
        movieDTO.setTitle(peliculaEntity.getTitle());
        movieDTO.setQualification(peliculaEntity.getQualification());
        movieDTO.setDateCreation(peliculaEntity.getDateCreation().toString());
        movieDTO.setGenderId(peliculaEntity.getGenderEntity().getId());
        movieDTO.setCharactersDTO(personajeMapper.characterEntityList2DtoList(peliculaEntity.getCharacters(), false));
        return movieDTO;
    }

    //=== ListEntity --> ListDto ===
    public List<PeliculaDTO> movieEntityList2DtoList(List<PeliculaEntity> listaEntity, boolean mostrarPelisDePersonajes){
        List<PeliculaDTO>dtoList = new ArrayList<>();
            for(PeliculaEntity ent : listaEntity) {
                dtoList.add(this.movieEntity2Dto(ent, false));
            }
        return dtoList;
    }

    //=== Date ===
    public LocalDate String2LocalDate(String stringDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, format);
        return date;
    }

    //=== Basic ===
    public List<PeliculaBasicDTO> peliculaBasicEntityList2DtoList(List<PeliculaEntity> listaEntity) {
        List<PeliculaBasicDTO>dtoList = new ArrayList<>();
        for(PeliculaEntity ent : listaEntity){
            dtoList.add(this.movieBasicEntity2Dto(ent));
        }
        return dtoList;
    }
    //=== Basic ===
    private PeliculaBasicDTO movieBasicEntity2Dto(PeliculaEntity ent) {
        PeliculaBasicDTO movieBasicDTO = new PeliculaBasicDTO();
        movieBasicDTO.setImage(ent.getImage());
        movieBasicDTO.setTitle(ent.getTitle());
        movieBasicDTO.setDateCreation(ent.getDateCreation().toString());
        return movieBasicDTO;
    }

}
