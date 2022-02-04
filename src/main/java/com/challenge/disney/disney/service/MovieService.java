package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.MovieBasicDTO;
import com.challenge.disney.disney.dto.MovieDTO;

import java.util.List;
import java.util.Set;

public interface MovieService {

    MovieDTO save(MovieDTO dto);

    void delete(Long id);

    MovieDTO editMovie(Long id, MovieDTO edit);

    List<MovieDTO> getByFilters(String title, Set<Long> gender, String order, String date);

    List<MovieBasicDTO> getMoviesBasic();

    MovieDTO getMovieDetails(Long id);

}
