package com.challenge.disney.disney.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MovieDTO {

    private Long id;

    private String imagen;

    private String titulo;

    private String fechaCreacion;

    private int calificacion;

    private Long generoId;

    private List<CharacterDTO> personajesDTO;

}
