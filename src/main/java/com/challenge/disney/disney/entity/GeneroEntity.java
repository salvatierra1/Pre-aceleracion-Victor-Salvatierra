package com.challenge.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "genero")
public class GeneroEntity {

    //=== Atributos ===
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    private String imagen;

}
