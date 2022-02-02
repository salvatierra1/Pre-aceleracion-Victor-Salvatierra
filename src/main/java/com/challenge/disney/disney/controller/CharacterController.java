package com.challenge.disney.disney.controller;

import com.challenge.disney.disney.dto.CharacterBasicDTO;
import com.challenge.disney.disney.dto.CharacterDTO;
import com.challenge.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class CharacterController {


    //=== Instancia de Service ===
    private CharacterService characterService;
    public CharacterController(@Autowired @Lazy CharacterService characterService) {
        this.characterService = characterService;
    }


    //=== Post ===
    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO personaje){
        CharacterDTO personajeGuardado = characterService.save(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }

    //=== Delete ===
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //=== Put ===
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> editarPersonaje(@PathVariable Long id, @RequestBody CharacterDTO edit){
        CharacterDTO editarPer = characterService.editPersonaje(id, edit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editarPer);
    }

    //=== Get ===
    @GetMapping("/all")
    public ResponseEntity<List<CharacterBasicDTO>> obtenerPersonajesBasic(){
        List<CharacterBasicDTO> personajesBasic = characterService.traerPersonajesBasic();
        return ResponseEntity.status(HttpStatus.OK).body(personajesBasic);

    }

    //=== Get --- Filters ===
    @GetMapping
    public ResponseEntity<List<CharacterDTO>> detallesPorFiltros(
            @RequestParam(required = false)String nombre,
            @RequestParam(required = false)Integer edad,
            @RequestParam(required = false)Set<Long>peliculas
            ){
        List<CharacterDTO> characterDTOList = characterService.traerPorFiltros(nombre, edad, peliculas);
        return ResponseEntity.status(HttpStatus.OK).body(characterDTOList);
    }



}
