package com.challenge.disney.disney.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CharacterDTO {

    private Long id;

    private String image;

    private String name;

    private int age;

    private double weight;

    private String history;

<<<<<<< HEAD:src/main/java/com/challenge/disney/disney/dto/PersonajeDTO.java
    private List<PeliculaDTO> moviesDTO;
=======
    private List<MovieDTO> peliculasDTO;
>>>>>>> develop:src/main/java/com/challenge/disney/disney/dto/CharacterDTO.java

}
