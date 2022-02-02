package com.challenge.disney.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieFiltersDTO {

    private String titulo;

    private String date;

    private Set<Long> genero;

    private String order;

    public MovieFiltersDTO(String titulo, Set<Long> genero, String order, String date) {
        this.titulo = titulo;
        this.date = date;
        this.genero = genero;
        this.order = order;

    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }

}
