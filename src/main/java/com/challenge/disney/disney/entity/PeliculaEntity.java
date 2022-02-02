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
@Table (name = "pelicula")
@Getter
@Setter
@SQLDelete(sql = "UPDATE pelicula SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class PeliculaEntity {


    //=== Atributos ===
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    private boolean deleted = Boolean.FALSE;

    private String imagen;

    private String titulo;

    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;

    private int calificacion;


    //=== Tiene muchos Personajes ===
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY
    )@JoinTable(
            name = "pelicula_personaje",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id")
    )

    private List<PersonajeEntity> personajes = new ArrayList<>();

    //=== Tiene un Genero ==
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private GeneroEntity genero;

}
