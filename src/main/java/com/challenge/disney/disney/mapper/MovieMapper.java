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
    public MovieEntity toEntity(MovieDTO dto){
        GenderEntity genderEntity = new GenderEntity();
        genderEntity.setId(dto.getGeneroId());
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setImagen(dto.getImagen());
        movieEntity.setTitulo(dto.getTitulo());
        movieEntity.setCalificacion(dto.getCalificacion());
        movieEntity.setFechaCreacion(this.String2LocalDate(dto.getFechaCreacion()));
        movieEntity.setPersonajes(characterMapper.toEntityList(dto.getPersonajesDTO()));
        movieEntity.setGenero(genderEntity);
        return movieEntity;
    }

    //=== Entity --> DTO
    public MovieDTO peliculaEntity2Dto(MovieEntity movieEntity, boolean mostrarPelisDePersonaje){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movieEntity.getId());
        movieDTO.setImagen(movieEntity.getImagen());
        movieDTO.setTitulo(movieEntity.getTitulo());
        movieDTO.setCalificacion(movieEntity.getCalificacion());
        movieDTO.setFechaCreacion(movieEntity.getFechaCreacion().toString());
        movieDTO.setGeneroId(movieEntity.getGenero().getId());
        movieDTO.setPersonajesDTO(characterMapper.personajeEntityList2DtoList(movieEntity.getPersonajes(), false));
        return movieDTO;
    }

    //=== ListEntity --> ListDto ===
    public List<MovieDTO> peliculaEntityList2DtoList(List<MovieEntity> listaEntity, boolean mostrarPelisDePersonajes){
        List<MovieDTO>dtoList = new ArrayList<>();
            for(MovieEntity ent : listaEntity) {
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
    public List<MovieBasicDTO> peliculaBasicEntityList2DtoList(List<MovieEntity> listaEntity) {
        List<MovieBasicDTO>dtoList = new ArrayList<>();
        for(MovieEntity ent : listaEntity){
            dtoList.add(this.peliculaBasicEntity2Dto(ent));
        }
        return dtoList;
    }
    //=== Basic ===
    private MovieBasicDTO peliculaBasicEntity2Dto(MovieEntity ent) {
        MovieBasicDTO movieBasicDTO = new MovieBasicDTO();
        movieBasicDTO.setImagen(ent.getImagen());
        movieBasicDTO.setTitulo(ent.getTitulo());
        movieBasicDTO.setFechaCreacion(ent.getFechaCreacion().toString());
        return movieBasicDTO;
    }


}
