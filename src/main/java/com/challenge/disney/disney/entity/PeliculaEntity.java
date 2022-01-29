package com.challenge.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name = "movie")
@Getter
@Setter
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class PeliculaEntity {


    //=== Atributos ===
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    private boolean deleted = Boolean.FALSE;

    private String image;

    private String title;

    private int qualification;

    @Column(name = "date_creation")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateCreation;


    //=== Tiene muchos Personajes ===
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )@JoinTable(
            name = "movie_character",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )

    private List<PersonajeEntity> characters = new ArrayList<>();

    //=== Tiene un Genero ==
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private GeneroEntity genderEntity;

}
