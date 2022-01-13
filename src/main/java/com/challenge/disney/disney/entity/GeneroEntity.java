package com.challenge.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genero")
@Getter
@Setter
public class GeneroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    private String imagen;

    @ManyToMany(
            mappedBy = "genero",
            cascade = CascadeType.ALL,
            fetch  = FetchType.LAZY
    )
    private List<PeliculaEntity> pelicula = new ArrayList<>();

}
