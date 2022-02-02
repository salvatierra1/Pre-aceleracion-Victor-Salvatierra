package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.GenderDTO;

import java.util.List;


public interface GenderService {

    GenderDTO save(GenderDTO dto);

    List<GenderDTO> traerGeneros();

}
