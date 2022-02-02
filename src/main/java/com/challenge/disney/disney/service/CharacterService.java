package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.CharacterBasicDTO;
import com.challenge.disney.disney.dto.CharacterDTO;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto);

    void delete(Long id);

    CharacterDTO editCharacter(Long id, CharacterDTO edit);

    List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies);

    List<CharacterBasicDTO> getCharacterBasic();
}
