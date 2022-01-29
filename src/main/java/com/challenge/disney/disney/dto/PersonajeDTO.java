package com.challenge.disney.disney.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PersonajeDTO {

    private Long id;

    private String image;

    private String name;

    private int age;

    private double weight;

    private String history;

    private List<PeliculaDTO> moviesDTO;

}
