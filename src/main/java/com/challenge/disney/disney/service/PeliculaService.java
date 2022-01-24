package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.PeliculaBasicDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;
import com.challenge.disney.disney.dto.PersonajeDTO;

import java.util.List;
import java.util.Set;

public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO dto);

    List<PeliculaDTO> traerPeliculas();

    void delete(Long id);

    PeliculaDTO editPelicula(Long id, PeliculaDTO edit);

    List<PeliculaBasicDTO> traerPeliculasBasic();


    List<PeliculaDTO> traerPorFiltros(String titulo, Set<Long> genero, String order, String date);

}
