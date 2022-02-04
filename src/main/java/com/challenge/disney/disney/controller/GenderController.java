package com.challenge.disney.disney.controller;


import com.challenge.disney.disney.dto.GenderDTO;
import com.challenge.disney.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenderController {

    //=== Instancia de Service ===
    private GenderService genderService;
    public GenderController(@Autowired GenderService genderService) {
        this.genderService = genderService;
    }

    //=== Post ===
    @PostMapping()
    public ResponseEntity<GenderDTO> save(@RequestBody GenderDTO gender){
        GenderDTO genderSaved = genderService.save(gender);
        return ResponseEntity.status(HttpStatus.CREATED).body(genderSaved);
    }

    //=== Get ===
    @GetMapping()
    public ResponseEntity<List<GenderDTO>> getGenders(){
        List<GenderDTO> genderDTOList = genderService.getGender();
        return ResponseEntity.status(HttpStatus.OK).body(genderDTOList);
    }

}
