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
public class CharacterFiltersDTO {

    private String name;

    private Integer age;

    private Set<Long> movies;

}
