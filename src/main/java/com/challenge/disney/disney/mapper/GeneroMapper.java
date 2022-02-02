package com.challenge.disney.disney.mapper;

import com.challenge.disney.disney.dto.GeneroDTO;
import com.challenge.disney.disney.entity.GeneroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneroMapper {


    //=== Mapper ===
    private PeliculaMapper peliculaMapper;
    public GeneroMapper(@Autowired @Lazy PeliculaMapper peliculaMapper) {
        this.peliculaMapper = peliculaMapper;
    }

    //=== DTO --> Entity
    public GeneroEntity generoDTO2Entity(GeneroDTO dto) {
        GeneroEntity generoEntity = new GeneroEntity();
        generoEntity.setImagen(dto.getImagen());
        generoEntity.setNombre(dto.getNombre());
        return generoEntity;
    }

    //=== Entity --> DTO ===
    public GeneroDTO generoEntity2Dto(GeneroEntity generoEntity) {
        GeneroDTO generoDTO = new GeneroDTO();
        generoDTO.setId(generoEntity.getId());
        generoDTO.setNombre(generoEntity.getNombre());
        generoDTO.setImagen(generoEntity.getImagen());
        return generoDTO;
    }

    //=== ListEntity --> ListDTO ===
    public List<GeneroDTO> generoEntityList2DTOList(List<GeneroEntity> savedGeneros) {
        List<GeneroDTO> generoDTOList = new ArrayList<>();
        for (GeneroEntity ent : savedGeneros){
            generoDTOList.add(this.generoEntity2Dto(ent));
        }
        return generoDTOList;
    }
}
