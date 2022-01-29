package com.challenge.disney.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class PeliculaFiltersDTO {

    private String title;

    private String date;

    private Set<Long> gender;

    private String order;

    public PeliculaFiltersDTO(String title, Set<Long> gender, String order, String date) {
        this.title = title;
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
