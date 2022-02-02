package com.challenge.disney.disney.controller;

import com.challenge.disney.disney.dto.MovieBasicDTO;
import com.challenge.disney.disney.dto.MovieDTO;
import com.challenge.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class MovieController {

    //=== Instancia de Service ===
    private MovieService movieService;
    public MovieController(@Autowired @Lazy MovieService movieService) {
        this.movieService = movieService;
    }

    //=== Post ===
    @PostMapping
    public ResponseEntity<MovieDTO>save(@RequestBody MovieDTO pelicula){
        MovieDTO peliculaGuardada = movieService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }

    //=== Delete ===
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //=== Put ===
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> editarPelicula(@PathVariable Long id, @RequestBody MovieDTO edit){
        MovieDTO peliculaEditada = movieService.editPelicula(id, edit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(peliculaEditada);
    }
    //=== Get ===
    @GetMapping("/all")
    public ResponseEntity<List<MovieBasicDTO>> obtenerPeliculasBasic(){
        List<MovieBasicDTO> peliculasBasic = movieService.traerPeliculasBasic();
        return ResponseEntity.status(HttpStatus.OK).body(peliculasBasic);

    }

    //=== Get --- Filters ===
    @GetMapping
    public ResponseEntity<List<MovieDTO>> detallesPorFiltros(
            @RequestParam(required = false)String titulo,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Set<Long> genero,
            @RequestParam(required  = false, defaultValue = "ASC") String order
    ){
        List<MovieDTO> movieDTOList = movieService.traerPorFiltros(titulo, genero, order, date);
        return ResponseEntity.status(HttpStatus.OK).body(movieDTOList);
    }

}
