package com.challenge.disney.disney.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CharacterDTO {

    private Long id;

    private String imagen;

    private String nombre;

    private int edad;

    private double peso;

    private String historia;

    private List<MovieDTO> peliculasDTO;

}
