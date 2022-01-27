package com.challenge.disney.disney.auth.filter.service;

import com.challenge.disney.disney.dto.GeneroDTO;

import java.util.List;


public interface GeneroService {

    GeneroDTO save(GeneroDTO dto);

    List<GeneroDTO> traerGeneros();

}
