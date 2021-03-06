package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.CharacterDTO;
import com.challenge.disney.disney.dto.MovieBasicDTO;
import com.challenge.disney.disney.dto.MovieDTO;
import com.challenge.disney.disney.dto.MovieFiltersDTO;
import com.challenge.disney.disney.entity.CharacterEntity;
import com.challenge.disney.disney.entity.MovieEntity;
import com.challenge.disney.disney.exception.ParamNotFound;
import com.challenge.disney.disney.mapper.MovieMapper;
import com.challenge.disney.disney.repository.GenderRepository;
import com.challenge.disney.disney.repository.MovieRepository;
import com.challenge.disney.disney.repository.specifications.MovieSpecification;
import com.challenge.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    //=== Specification ===
    private MovieSpecification movieSpecification;

    //=== Mapper ===
    private MovieMapper movieMapper;

    //=== Repository ====
    private MovieRepository movieRepository;
    private GenderRepository genderRepository;

    //=== Construtor ===
    public MovieServiceImpl(@Autowired  MovieSpecification movieSpecification, @Autowired MovieMapper movieMapper, @Autowired  MovieRepository movieRepository, @Autowired GenderRepository genderRepository) {
        this.movieSpecification = movieSpecification;
        this.movieMapper = movieMapper;
        this.movieRepository = movieRepository;
        this.genderRepository = genderRepository;
    }

    //=== Post ===
    @Override
    public MovieDTO save(MovieDTO dto) {
        MovieEntity peliculaEnt = movieMapper.movieDTO2Entity(dto);
        MovieEntity peliculaSaved = movieRepository.save(peliculaEnt);
        MovieDTO result = movieMapper.movieEntity2Dto(peliculaSaved, false);
        return result;
    }

    //=== Delete ===
    public void delete(Long id){
        movieRepository.deleteById(id);
    }

    //=== Put ===
    @Override
    public MovieDTO editMovie(Long id, MovieDTO edit) {
       MovieEntity savedPelicula = this.getMovieEdit(id);
       savedPelicula.setImage(edit.getImage());
       savedPelicula.setTitle(edit.getTitle());
       savedPelicula.setQualification(edit.getQualification());
       savedPelicula.setDateCreation(movieMapper.String2LocalDate(edit.getDateCreation()));
       MovieEntity editPelicula = movieRepository.save(savedPelicula);
       MovieDTO savedDTO = movieMapper.movieEntity2Dto(editPelicula, false);
       return savedDTO;
    }

    private MovieEntity getMovieEdit(Long id) {
        Optional<MovieEntity> peliculaEntity = movieRepository.findById(id);
        if (!peliculaEntity.isPresent()){
            throw  new ParamNotFound("id no valido");
        }
        return peliculaEntity.get();
    }

    //=== Filters ===
    @Override
    public List<MovieDTO> getByFilters(String title, Set<Long> gender, String order, String date) {
        MovieFiltersDTO movieFiltersDTO = new MovieFiltersDTO(title, gender, order, date);
        List<MovieEntity> movieEntityList = movieRepository.findAll(movieSpecification.obtenerFiltro(movieFiltersDTO));
        List<MovieDTO> result = movieMapper.movieEntityList2DtoList(movieEntityList, true);
        return result;
    }

    //=== Get ===
    @Override
    public MovieDTO getMovieDetails(Long id) {
        Optional<MovieEntity> entity = this.movieRepository.findById(id);
        if (!entity.isPresent()){
            throw new ParamNotFound("id no valido");
        }
        MovieDTO result = this.movieMapper.movieEntity2Dto(entity.get(), true);
        return result;
    }

    //=== Basic ===
    @Override
    public List<MovieBasicDTO> getMoviesBasic() {
        List<MovieEntity>listEntity = movieRepository.findAll();
        List<MovieBasicDTO>result = movieMapper.movieBasicEntityList2DtoList(listEntity);
        return result;
    }


}
