package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.GeneroDTO;
import com.challenge.disney.disney.entity.GeneroEntity;
import com.challenge.disney.disney.mapper.GeneroMapper;
import com.challenge.disney.disney.auth.repository.GeneroRepository;
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
        GeneroEntity generoEnt = generoMapper.generoDTO2Entity(dto);
        GeneroEntity generoSaved = generoRepository.save(generoEnt);
        GeneroDTO resultado = generoMapper.generoEntity2Dto(generoSaved);
        return resultado;
    }

    //=== Get ===
    @Override
    public List<GeneroDTO> traerGeneros() {
        List<GeneroEntity> savedGeneros = generoRepository.findAll();
        List<GeneroDTO> resultado = generoMapper.generoEntityList2DTOList(savedGeneros);
        return  resultado;
    }
}
