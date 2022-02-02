package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.MovieBasicDTO;
import com.challenge.disney.disney.dto.MovieDTO;
import com.challenge.disney.disney.dto.MovieFiltersDTO;
import com.challenge.disney.disney.entity.MovieEntity;
import com.challenge.disney.disney.exception.ParamNotFound;
import com.challenge.disney.disney.mapper.MovieMapper;
import com.challenge.disney.disney.repository.GenderRepository;
import com.challenge.disney.disney.repository.MovieRepository;
import com.challenge.disney.disney.repository.specifications.MovieSpecification;
import com.challenge.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    public MovieServiceImpl(@Autowired @Lazy MovieSpecification movieSpecification, @Autowired @Lazy MovieMapper movieMapper, @Autowired @Lazy MovieRepository movieRepository, @Autowired @Lazy GenderRepository genderRepository) {
        this.movieSpecification = movieSpecification;
        this.movieMapper = movieMapper;
        this.movieRepository = movieRepository;
        this.genderRepository = genderRepository;
    }

    //=== Post ===
    @Override
    public MovieDTO save(MovieDTO dto) {
        MovieEntity peliculaEnt = movieMapper.toEntity(dto);
        MovieEntity peliculaSaved = movieRepository.save(peliculaEnt);
        MovieDTO resultado = movieMapper.peliculaEntity2Dto(peliculaSaved, false);
        return resultado;
    }

    //=== Delete ===
    public void delete(Long id){
        movieRepository.deleteById(id);
    }

    //=== Put ===
    @Override
    public MovieDTO editPelicula(Long id, MovieDTO edit) {
       MovieEntity savedPelicula = this.getPeliculaEdit(id);
       savedPelicula.setImagen(edit.getImagen());
       savedPelicula.setTitulo(edit.getTitulo());
       savedPelicula.setCalificacion(edit.getCalificacion());
       savedPelicula.setFechaCreacion(movieMapper.String2LocalDate(edit.getFechaCreacion()));
       MovieEntity editPelicula = movieRepository.save(savedPelicula);
       MovieDTO savedDTO = movieMapper.peliculaEntity2Dto(editPelicula, false);
       return savedDTO;
    }

    private MovieEntity getPeliculaEdit(Long id) {
        Optional<MovieEntity> peliculaEntity = movieRepository.findById(id);
        if (!peliculaEntity.isPresent()){
            throw  new ParamNotFound("id no valido");
        }
        return peliculaEntity.get();
    }

    //=== Filters ===
    @Override
    public List<MovieDTO> traerPorFiltros(String titulo, Set<Long> genero, String order, String date) {
        MovieFiltersDTO movieFiltersDTO = new MovieFiltersDTO(titulo, genero, order, date);
        List<MovieEntity> movieEntityList = movieRepository.findAll(movieSpecification.obtenerFiltro(movieFiltersDTO));
        List<MovieDTO> resultado = movieMapper.peliculaEntityList2DtoList(movieEntityList, true);
        return resultado;
    }

    //=== Basic ===
    @Override
    public List<MovieBasicDTO> traerPeliculasBasic() {
        List<MovieEntity>listaEntity = movieRepository.findAll();
        List<MovieBasicDTO>resultado = movieMapper.peliculaBasicEntityList2DtoList(listaEntity);
        return resultado;
    }


}
