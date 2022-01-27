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
public class PeliculaFiltersDTO {

    private String titulo;

    private String date;

    private String imagen;

    private Set<Long> genero;

    private String order;

    public PeliculaFiltersDTO(String titulo, Set<Long> genero, String order, String date, String imagen) {
        this.titulo = titulo;
        this.date = date;
        this.genero = genero;
        this.order = order;
        this.imagen = imagen;
    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }

}
