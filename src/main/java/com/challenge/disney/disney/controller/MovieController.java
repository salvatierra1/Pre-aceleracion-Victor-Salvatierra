package com.challenge.disney.disney.controller;

import com.challenge.disney.disney.dto.MovieBasicDTO;
import com.challenge.disney.disney.dto.MovieDTO;
import com.challenge.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public MovieController(@Autowired MovieService movieService) {
        this.movieService = movieService;
    }

    //=== Post ===
    @PostMapping
    public ResponseEntity<MovieDTO>save(@RequestBody MovieDTO movie){
        MovieDTO movieSaved = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }

    //=== Delete ===
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //=== Put ===
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> editMovie(@PathVariable Long id, @RequestBody MovieDTO edit){
        MovieDTO movieEdited = movieService.editMovie(id, edit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(movieEdited);
    }
    //=== Get ===
    @GetMapping("/all")
    public ResponseEntity<List<MovieBasicDTO>> getMoviesBasic(){
        List<MovieBasicDTO> movieBasic = movieService.getMoviesBasic();
        return ResponseEntity.status(HttpStatus.OK).body(movieBasic);

    }

    //=== Get --- Filters ===
    @GetMapping
    public ResponseEntity<List<MovieDTO>> detailsByFilters(
            @RequestParam(required = false)String title,
            @RequestParam(required = false) String date,
            @RequestParam(required = false) Set<Long> gender,
            @RequestParam(required  = false, defaultValue = "ASC") String order
    ){
        List<MovieDTO> movieDTOList = movieService.getByFilters(title, gender, order, date);
        return ResponseEntity.status(HttpStatus.OK).body(movieDTOList);
    }

}
