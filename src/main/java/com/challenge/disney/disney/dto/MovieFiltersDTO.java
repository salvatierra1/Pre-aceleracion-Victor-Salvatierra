package com.challenge.disney.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
<<<<<<< HEAD:src/main/java/com/challenge/disney/disney/dto/PeliculaFiltersDTO.java
public class PeliculaFiltersDTO {
=======
@AllArgsConstructor
public class MovieFiltersDTO {
>>>>>>> develop:src/main/java/com/challenge/disney/disney/dto/MovieFiltersDTO.java

    private String title;

    private String date;

    private Set<Long> gender;

    private String order;

<<<<<<< HEAD:src/main/java/com/challenge/disney/disney/dto/PeliculaFiltersDTO.java
    public PeliculaFiltersDTO(String title, Set<Long> gender, String order, String date) {
        this.title = title;
=======
    public MovieFiltersDTO(String titulo, Set<Long> genero, String order, String date) {
        this.titulo = titulo;
>>>>>>> develop:src/main/java/com/challenge/disney/disney/dto/MovieFiltersDTO.java
        this.date = date;
        this.gender = gender;
        this.order = order;
    }


    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }

}
