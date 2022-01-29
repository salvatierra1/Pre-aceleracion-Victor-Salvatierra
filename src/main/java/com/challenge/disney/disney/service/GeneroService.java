package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.GeneroDTO;

import java.util.List;


public interface GeneroService {

    GeneroDTO save(GeneroDTO dto);

    List<GeneroDTO> getGenders();

}
