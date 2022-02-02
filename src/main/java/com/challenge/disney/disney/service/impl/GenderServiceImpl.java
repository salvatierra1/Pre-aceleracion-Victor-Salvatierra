package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.GenderDTO;
import com.challenge.disney.disney.entity.GenderEntity;
import com.challenge.disney.disney.mapper.GenderMapper;
import com.challenge.disney.disney.repository.GenderRepository;
import com.challenge.disney.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {

    //=== Mapper ==
    private GenderMapper genderMapper;

    //=== Repository ===
    private GenderRepository genderRepository;

    //=== Construtor ===
    public GenderServiceImpl(@Autowired @Lazy GenderMapper genderMapper, GenderRepository genderRepository) {
        this.genderMapper = genderMapper;
        this.genderRepository = genderRepository;
    }

    //=== Post ===
    @Override
    public GenderDTO save(GenderDTO dto) {
        GenderEntity generoEnt = genderMapper.generoDTO2Entity(dto);
        GenderEntity generoSaved = genderRepository.save(generoEnt);
        GenderDTO resultado = genderMapper.generoEntity2Dto(generoSaved);
        return resultado;
    }

    //=== Get ===
    @Override
    public List<GenderDTO> traerGeneros() {
        List<GenderEntity> savedGeneros = genderRepository.findAll();
        List<GenderDTO> resultado = genderMapper.generoEntityList2DTOList(savedGeneros);
        return  resultado;
    }
}
