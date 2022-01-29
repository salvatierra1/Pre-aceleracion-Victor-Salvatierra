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
@Table (name = "characters")
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class PersonajeEntity {

    //=== Atributos ===
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    private boolean deleted = Boolean.FALSE;

    private String image;

    private String name;

    private int age;

    private double weight;

    private String history;


    //=== Tiene muchas peliculas ===
    @ManyToMany(
            mappedBy = "characters",
            fetch  = FetchType.LAZY
    )
    private List<PeliculaEntity> movies = new ArrayList<>();

}
