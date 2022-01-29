package com.challenge.disney.disney.controller;

import com.challenge.disney.disney.dto.PeliculaBasicDTO;
import com.challenge.disney.disney.dto.PeliculaDTO;
import com.challenge.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class PeliculaController {

    //=== Instancia de Service ===
    private PeliculaService peliculaService;
    public PeliculaController(@Autowired @Lazy PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    //=== Post ===
    @PostMapping
    public ResponseEntity<PeliculaDTO>save(@RequestBody PeliculaDTO pelicula){
        PeliculaDTO moviesSaved = peliculaService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(moviesSaved);
    }

    //=== Delete ===
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //=== Put ===
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> editMovies(@PathVariable Long id, @RequestBody PeliculaDTO edit){
        PeliculaDTO moviesEdited = peliculaService.editPelicula(id, edit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(moviesEdited);
    }
    //=== Get ===
    @GetMapping("/all")
    public ResponseEntity<List<PeliculaBasicDTO>> getMoviesBasic(){
        List<PeliculaBasicDTO>moviesBasic = peliculaService.getMoviesBasic();
        return ResponseEntity.status(HttpStatus.OK).body(moviesBasic);

    }

    //=== Get --- Filters ===
    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> detailsByFilters(
            @RequestParam(required = false)String title,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Set<Long> gender,
            @RequestParam(required  = false, defaultValue = "ASC") String order
    ){
        List<PeliculaDTO> moviesDTOList = peliculaService.getByFilters(title, gender, order, date);
        return ResponseEntity.status(HttpStatus.OK).body(moviesDTOList);
    }

}
