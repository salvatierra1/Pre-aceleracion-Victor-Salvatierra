package com.challenge.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table (name = "personaje")
@SQLDelete(sql = "UPDATE personaje SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class CharacterEntity {

    //=== Atributos ===
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    private boolean deleted = Boolean.FALSE;

    private String imagen;

    private String nombre;

    private int edad;

    private double peso;

    private String historia;


    //=== Tiene muchas peliculas ===
    @ManyToMany(
            mappedBy = "personajes",
            fetch  = FetchType.LAZY
    )
    private List<MovieEntity> peliculas = new ArrayList<>();

}
