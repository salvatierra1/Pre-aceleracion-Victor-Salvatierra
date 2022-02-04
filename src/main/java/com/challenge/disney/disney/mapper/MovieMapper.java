package com.challenge.disney.disney.mapper;

import com.challenge.disney.disney.dto.MovieBasicDTO;
import com.challenge.disney.disney.dto.MovieDTO;
import com.challenge.disney.disney.entity.GenderEntity;
import com.challenge.disney.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
public class MovieMapper {

    //=== Mapper ===
    private CharacterMapper characterMapper;
    public MovieMapper(@Autowired @Lazy CharacterMapper characterMapper) {
        this.characterMapper = characterMapper;
    }

    //=== DTO --> Entity
    public MovieEntity movieDTO2Entity(MovieDTO dto){
        GenderEntity genderEntity = new GenderEntity();
        genderEntity.setId(dto.getGenderId());
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setImage(dto.getImage());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setQualification(dto.getQualification());
        movieEntity.setDateCreation(this.String2LocalDate(dto.getDateCreation()));
        movieEntity.setCharacters(characterMapper.toEntityList(dto.getCharactersDTO()));
        movieEntity.setGender(genderEntity);
        return movieEntity;
    }

    //=== Entity --> DTO
    public MovieDTO movieEntity2Dto(MovieEntity movieEntity, boolean showMoviesCharacters){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movieEntity.getId());
        movieDTO.setImage(movieEntity.getImage());
        movieDTO.setTitle(movieEntity.getTitle());
        movieDTO.setQualification(movieEntity.getQualification());
        movieDTO.setDateCreation(movieEntity.getDateCreation().toString());
        movieDTO.setGenderId(movieEntity.getGender().getId());
        movieDTO.setCharactersDTO(characterMapper.characterEntityList2DtoList(movieEntity.getCharacters(), false));
        return movieDTO;
    }

    //=== ListEntity --> ListDto ===
    public List<MovieDTO> movieEntityList2DtoList(List<MovieEntity> listaEntity, boolean showMoviesCharacters){
        List<MovieDTO>dtoList = new ArrayList<>();
            for(MovieEntity ent : listaEntity) {
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
    public List<MovieBasicDTO> movieBasicEntityList2DtoList(List<MovieEntity> listaEntity) {
        List<MovieBasicDTO>dtoList = new ArrayList<>();
        for(MovieEntity ent : listaEntity){
            dtoList.add(this.movieBasicEntity2Dto(ent));
        }
        return dtoList;
    }
    //=== Basic ===
    private MovieBasicDTO movieBasicEntity2Dto(MovieEntity ent) {
        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();
        movieBasicDTO.setImage(ent.getImage());
        movieBasicDTO.setTitle(ent.getTitle());
        movieBasicDTO.setDateCreation(ent.getDateCreation().toString());
        return movieBasicDTO;
    }

}
