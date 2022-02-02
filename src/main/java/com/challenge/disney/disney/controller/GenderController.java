package com.challenge.disney.disney.controller;


import com.challenge.disney.disney.dto.GenderDTO;
import com.challenge.disney.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenderController {

    //=== Instancia de Service ===
    private GenderService genderService;
    public GenderController(@Autowired @Lazy GenderService genderService) {
        this.genderService = genderService;
    }

    //=== Post ===
    @PostMapping()
    public ResponseEntity<GenderDTO> save(@RequestBody GenderDTO genero){
        GenderDTO generoGuardado = genderService.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }

    //=== Get ===
    @GetMapping("/all")
    public ResponseEntity<List<GenderDTO>>obtenerGeneros(){
        List<GenderDTO> genderDTOList = genderService.traerGeneros();
        return ResponseEntity.status(HttpStatus.OK).body(genderDTOList);
    }

}
