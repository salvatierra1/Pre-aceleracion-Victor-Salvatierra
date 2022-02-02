package com.challenge.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "gender")
public class GenderEntity {


    //=== Atributos ===
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String image;

}
