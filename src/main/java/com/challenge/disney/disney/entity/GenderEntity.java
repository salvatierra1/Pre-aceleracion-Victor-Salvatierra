package com.challenge.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Getter
@Setter
<<<<<<< HEAD:src/main/java/com/challenge/disney/disney/entity/GeneroEntity.java
@Table(name = "gender")
public class GeneroEntity {
=======
@Table(name = "genero")
public class GenderEntity {
>>>>>>> develop:src/main/java/com/challenge/disney/disney/entity/GenderEntity.java

    //=== Atributos ===
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String image;

}
