package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.PeliculaDTO;
import com.challenge.disney.disney.dto.PeliculaFiltersDTO;
import com.challenge.disney.disney.entity.GeneroEntity;
import com.challenge.disney.disney.entity.PeliculaEntity;
import com.challenge.disney.disney.mapper.PeliculaMapper;
import com.challenge.disney.disney.repository.GeneroRepository;
import com.challenge.disney.disney.repository.PeliculaRepository;
import com.challenge.disney.disney.repository.specifications.PeliculaSpecification;
import com.challenge.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    //=== Specification ===
    private PeliculaSpecification peliculaSpecification;

    //=== Mapper ===
    private PeliculaMapper peliculaMapper;

    //=== Repository ====
    private PeliculaRepository peliculaRepository;
    private GeneroRepository generoRepository;

    //=== Construtor ===
    public PeliculaServiceImpl(@Autowired @Lazy PeliculaSpecification peliculaSpecification,@Autowired @Lazy PeliculaMapper peliculaMapper,@Autowired @Lazy PeliculaRepository peliculaRepository,@Autowired @Lazy GeneroRepository generoRepository) {
        this.peliculaSpecification = peliculaSpecification;
        this.peliculaMapper = peliculaMapper;
        this.peliculaRepository = peliculaRepository;
        this.generoRepository = generoRepository;
    }

    //=== Post ===
    @Override
    public PeliculaDTO save(PeliculaDTO dto) {
        PeliculaEntity peliculaEnt = peliculaMapper.toEntity(dto);
        PeliculaEntity peliculaSaved = peliculaRepository.save(peliculaEnt);
        PeliculaDTO resultado = peliculaMapper.peliculaEntity2Dto(peliculaSaved, false);
        return resultado;
    }

    //=== Delete ===
    public void delete(Long id){
        peliculaRepository.deleteById(id);
    }

    //=== Put ===
    @Override
    public PeliculaDTO editPelicula(Long id, PeliculaDTO edit) {
       PeliculaEntity savedPelicula = this.getPeliculaEdit(id);
       savedPelicula.setImagen(edit.getImagen());
       savedPelicula.setTitulo(edit.getTitulo());
       savedPelicula.setCalificacion(edit.getCalificacion());
       savedPelicula.setFechaCreacion(peliculaMapper.String2LocalDate(edit.getFechaCreacion()));
       PeliculaEntity editPelicula = peliculaRepository.save(savedPelicula);
       PeliculaDTO savedDTO = peliculaMapper.peliculaEntity2Dto(editPelicula, false);
       return savedDTO;
    }

    private PeliculaEntity getPeliculaEdit(Long id) {
        Optional<PeliculaEntity> peliculaEntity = peliculaRepository.findById(id);
        if (!peliculaEntity.isPresent()){
        }
        return peliculaEntity.get();
    }

    //=== Filters ===
    @Override
    public List<PeliculaDTO> traerPorFiltros(String titulo, Set<Long> genero, String order, String date, String imagen) {
        PeliculaFiltersDTO peliculaFiltersDTO = new PeliculaFiltersDTO(titulo, genero, order, date, imagen);
        List<PeliculaEntity> peliculaEntityList = peliculaRepository.findAll(peliculaSpecification.obtenerFiltro(peliculaFiltersDTO));
        List<PeliculaDTO> resultado = peliculaMapper.peliculaEntityList2DtoList(peliculaEntityList, true);
        return resultado;
    }


}
