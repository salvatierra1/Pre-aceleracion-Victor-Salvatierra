package com.challenge.disney.disney.controller;


import com.challenge.disney.disney.dto.GeneroDTO;
import com.challenge.disney.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GeneroController {

    //=== Instancia de Service ===
    private GeneroService generoService;
    public GeneroController(@Autowired @Lazy GeneroService generoService) {
        this.generoService = generoService;
    }

    //=== Post ===
    @PostMapping()
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO genero){
        GeneroDTO generoGuardado = generoService.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }

    //=== Get ===
    @GetMapping("/all")
    public ResponseEntity<List<GeneroDTO>>obtenerGeneros(){
        List<GeneroDTO> generoDTOList = generoService.traerGeneros();
        return ResponseEntity.status(HttpStatus.OK).body(generoDTOList);
    }

}
