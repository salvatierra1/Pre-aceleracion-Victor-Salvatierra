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
    public GeneroEntity genderDTO2Entity(GeneroDTO dto) {
        GeneroEntity genderEntity = new GeneroEntity();
        genderEntity.setImage(dto.getImage());
        genderEntity.setName(dto.getName());
        return genderEntity;
    }

    //=== Entity --> DTO ===
    public GeneroDTO genderEntity2Dto(GeneroEntity genderEntity) {
        GeneroDTO genderDTO = new GeneroDTO();
        genderDTO.setId(genderEntity.getId());
        genderDTO.setName(genderEntity.getName());
        genderDTO.setImage(genderEntity.getImage());
        return genderDTO;
    }

    //=== ListEntity --> ListDTO ===
    public List<GeneroDTO> genderEntityList2DTOList(List<GeneroEntity> savedGender) {
        List<GeneroDTO> genderDTOList = new ArrayList<>();
        for (GeneroEntity ent : savedGender){
            genderDTOList.add(this.genderEntity2Dto(ent));
        }
        return genderDTOList;
    }
}
