package com.challenge.disney.disney.controller;

import com.challenge.disney.disney.dto.CharacterBasicDTO;
import com.challenge.disney.disney.dto.CharacterDTO;
import com.challenge.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CharacterController(@Autowired CharacterService characterService) {
        this.characterService = characterService;
    }


    //=== Post ===
    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character){
        CharacterDTO characterSaved = characterService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }

    //=== Delete ===
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //=== Put ===
    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> editCharacter(@PathVariable Long id, @RequestBody CharacterDTO edit){
        CharacterDTO characterEdited = characterService.editCharacter(id, edit);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(characterEdited);
    }

    //=== Get ===
    @GetMapping("/all")
    public ResponseEntity<List<CharacterBasicDTO>> getCharacterBasic(){
        List<CharacterBasicDTO> characterBasic = characterService.getCharacterBasic();
        return ResponseEntity.status(HttpStatus.OK).body(characterBasic);

    }

    //=== Get --- Filters ===
    @GetMapping
    public ResponseEntity<List<CharacterDTO>> detailsByFilters(
            @RequestParam(required = false)String name,
            @RequestParam(required = false)Integer age,
            @RequestParam(required = false)Set<Long>movies
            ){
        List<CharacterDTO> characterDTOList = characterService.getByFilters(name, age, movies);
        return ResponseEntity.status(HttpStatus.OK).body(characterDTOList);
    }



}
