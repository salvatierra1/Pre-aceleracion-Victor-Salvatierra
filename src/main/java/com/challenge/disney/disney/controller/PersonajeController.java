package com.challenge.disney.disney.controller;

import com.challenge.disney.disney.dto.PersonajeBasicDTO;
import com.challenge.disney.disney.dto.PersonajeDTO;
import com.challenge.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class PersonajeController {


    //=== Instancia de Service ===
    private PersonajeService personajeService;
    public PersonajeController(@Autowired @Lazy PersonajeService personajeService) {
        this.personajeService = personajeService;
    }


    //=== Post ===
    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personaje){
        PersonajeDTO characterSaved = personajeService.save(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }

    //=== Delete ===
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //=== Put ===
    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> editCharacter(@PathVariable Long id, @RequestBody PersonajeDTO edit){
        PersonajeDTO characterDTO = personajeService.editCharacter(id, edit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(characterDTO);
    }

    //=== Get ===
    @GetMapping("/all")
    public ResponseEntity<List<PersonajeBasicDTO>> getCharactersBasic(){
        List<PersonajeBasicDTO> characterBasicList = personajeService.getCharacterBasic();
        return ResponseEntity.status(HttpStatus.OK).body(characterBasicList);

    }

    //=== Get --- Filters ===
    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> detailsByFilters(
            @RequestParam(required = false)String name,
            @RequestParam(required = false)Integer age,
            @RequestParam(required = false)Set<Long>movies
            ){
        List<PersonajeDTO> characterDTOList = personajeService.getByFilters(name, age, movies);
        return ResponseEntity.status(HttpStatus.OK).body(characterDTOList);
    }



}
