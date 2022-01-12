package com.challenge.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "pelicula")
@Getter
@Setter
public class Pelicula {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String titulo;

    private LocalDate fechaCreacion;

    private int calificacion;

}
