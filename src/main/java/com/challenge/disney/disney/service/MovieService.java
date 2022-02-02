package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.MovieBasicDTO;
import com.challenge.disney.disney.dto.MovieDTO;

import java.util.List;
import java.util.Set;

public interface MovieService {

    MovieDTO save(MovieDTO dto);

    void delete(Long id);

    MovieDTO editPelicula(Long id, MovieDTO edit);

    List<MovieDTO> traerPorFiltros(String titulo, Set<Long> genero, String order, String date);

    List<MovieBasicDTO> traerPeliculasBasic();
}
