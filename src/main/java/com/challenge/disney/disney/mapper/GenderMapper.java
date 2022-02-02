package com.challenge.disney.disney.mapper;

import com.challenge.disney.disney.dto.GenderDTO;
import com.challenge.disney.disney.entity.GenderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenderMapper {


    //=== Mapper ===
    private MovieMapper movieMapper;
    public GenderMapper(@Autowired @Lazy MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    //=== DTO --> Entity
    public GenderEntity generoDTO2Entity(GenderDTO dto) {
        GenderEntity genderEntity = new GenderEntity();
        genderEntity.setImagen(dto.getImagen());
        genderEntity.setNombre(dto.getNombre());
        return genderEntity;
    }

    //=== Entity --> DTO ===
    public GenderDTO generoEntity2Dto(GenderEntity genderEntity) {
        GenderDTO genderDTO = new GenderDTO();
        genderDTO.setId(genderEntity.getId());
        genderDTO.setNombre(genderEntity.getNombre());
        genderDTO.setImagen(genderEntity.getImagen());
        return genderDTO;
    }

    //=== ListEntity --> ListDTO ===
    public List<GenderDTO> generoEntityList2DTOList(List<GenderEntity> savedGeneros) {
        List<GenderDTO> genderDTOList = new ArrayList<>();
        for (GenderEntity ent : savedGeneros){
            genderDTOList.add(this.generoEntity2Dto(ent));
        }
        return genderDTOList;
    }
}
