package com.challenge.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "personaje")
@Getter
@Setter
public class PersonajeEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String nombre;

    private int edad;

    private double peso;

    private String historia;

    @ManyToMany(
            mappedBy = "personaje",
            cascade = CascadeType.ALL,
            fetch  = FetchType.LAZY
    )
    private List<PeliculaEntity> peliculas = new ArrayList<>();

    private int num;
}
