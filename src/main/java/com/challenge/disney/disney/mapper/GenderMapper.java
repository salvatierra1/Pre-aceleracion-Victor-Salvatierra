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
    public GenderEntity genderDTO2Entity(GenderDTO dto) {
        GenderEntity genderEntity = new GenderEntity();
        genderEntity.setImage(dto.getImage());
        genderEntity.setName(dto.getName());
        return genderEntity;
    }

    //=== Entity --> DTO ===
    public GenderDTO genderEntity2Dto(GenderEntity genderEntity) {
        GenderDTO genderDTO = new GenderDTO();
        genderDTO.setId(genderEntity.getId());
        genderDTO.setName(genderEntity.getName());
        genderDTO.setImage(genderEntity.getImage());
        return genderDTO;
    }

    //=== ListEntity --> ListDTO ===
    public List<GenderDTO> genderEntityList2DTOList(List<GenderEntity> genderEntities) {
        List<GenderDTO> genderDTOList = new ArrayList<>();
        for (GenderEntity ent : genderEntities){
            genderDTOList.add(this.genderEntity2Dto(ent));
        }
        return genderDTOList;
    }

}
