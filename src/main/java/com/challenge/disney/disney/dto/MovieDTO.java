package com.challenge.disney.disney.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MovieDTO {

    private Long id;

    private String image;

    private String title;

    private String dateCreation;

    private int qualification;

    private Long genderId;

    private List<CharacterDTO> charactersDTO;

}
