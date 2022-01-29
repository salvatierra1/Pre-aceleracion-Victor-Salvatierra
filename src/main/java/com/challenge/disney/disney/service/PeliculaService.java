package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.PeliculaBasicDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;

import java.util.List;
import java.util.Set;

public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO dto);

    void delete(Long id);

    PeliculaDTO editPelicula(Long id, PeliculaDTO edit);

    List<PeliculaDTO> getByFilters(String title, Set<Long> gender, String order, String date);

    List<PeliculaBasicDTO> getMoviesBasic();
}
