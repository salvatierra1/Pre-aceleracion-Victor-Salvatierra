package com.challenge.disney.disney.service;

import com.challenge.disney.disney.dto.PersonajeBasicDTO;
import com.challenge.disney.disney.dto.PersonajeDTO;

import java.util.List;
import java.util.Set;

public interface PersonajeService {

    PersonajeDTO save(PersonajeDTO dto);

    List<PersonajeDTO> traerPersonajes();

    void delete(Long id);

    PersonajeDTO editPersonaje(Long id, PersonajeDTO edit);

    List<PersonajeBasicDTO> traerPersonajesBasic();

    PersonajeDTO traerDetalles(Long id);

    List<PersonajeDTO> traerPorFiltros(String nombre, Integer edad, Set<Long> peliculas);

}
