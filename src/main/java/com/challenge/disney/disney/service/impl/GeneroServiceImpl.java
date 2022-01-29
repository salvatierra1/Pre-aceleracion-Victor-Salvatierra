package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.GeneroDTO;
import com.challenge.disney.disney.entity.GeneroEntity;
import com.challenge.disney.disney.mapper.GeneroMapper;
import com.challenge.disney.disney.repository.GeneroRepository;
import com.challenge.disney.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImpl implements GeneroService {

    //=== Mapper ==
    private GeneroMapper generoMapper;

    //=== Repository ===
    private GeneroRepository generoRepository;

    //=== Construtor ===
    public GeneroServiceImpl(@Autowired @Lazy GeneroMapper generoMapper, GeneroRepository generoRepository) {
        this.generoMapper = generoMapper;
        this.generoRepository = generoRepository;
    }

    //=== Post ===
    @Override
    public GeneroDTO save(GeneroDTO dto) {
        GeneroEntity generoEnt = generoMapper.genderDTO2Entity(dto);
        GeneroEntity generoSaved = generoRepository.save(generoEnt);
        GeneroDTO result = generoMapper.genderEntity2Dto(generoSaved);
        return result;
    }

    //=== Get ===
    @Override
    public List<GeneroDTO> getGenders() {
        List<GeneroEntity> savedGeneros = generoRepository.findAll();
        List<GeneroDTO> result = generoMapper.genderEntityList2DTOList(savedGeneros);
        return  result;
    }
}
