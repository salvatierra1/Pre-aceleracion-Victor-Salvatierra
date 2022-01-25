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

    //=== Get ===
    @GetMapping("/basic/characters")
    public ResponseEntity<List<PersonajeBasicDTO>> obtenerPersonajesBasic(){
        List<PersonajeBasicDTO> personajesBasic = personajeService.traerPersonajesBasic();
        return ResponseEntity.status(HttpStatus.OK).body(personajesBasic);

    }

    //=== Post ===
    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personaje){
        PersonajeDTO personajeGuardado = personajeService.save(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }

    //=== Get ===
    @GetMapping("/all")
    public ResponseEntity<List<PersonajeDTO>> obtenerPersonajes(){
        List<PersonajeDTO> personajeDTOList = personajeService.traerPersonajes();
        return ResponseEntity.status(HttpStatus.OK).body(personajeDTOList);
    }

    //=== Get ===
    @GetMapping("/details/{id}")
    public ResponseEntity<PersonajeDTO> detallesDePersonaje(@PathVariable Long id){
        PersonajeDTO personajeDTO = personajeService.traerDetalles(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personajeDTO);
    }

    //=== Delete ===
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //=== Put ===
    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> editarPersonaje(@PathVariable Long id, @RequestBody PersonajeDTO edit){
        PersonajeDTO editarPer = personajeService.editPersonaje(id, edit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editarPer);
    }

    //=== Get --- Filters ===
    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> detallesPorFiltros(
            @RequestParam(required = false)String nombre,
            @RequestParam(required = false)Integer edad,
            @RequestParam(required = false)Set<Long>peliculas
            ){
        List<PersonajeDTO> personajeDTOList = personajeService.traerPorFiltros(nombre, edad, peliculas);
        return ResponseEntity.status(HttpStatus.OK).body(personajeDTOList);
    }

}
