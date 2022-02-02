package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.CharacterBasicDTO;
import com.challenge.disney.disney.dto.CharacterDTO;
import com.challenge.disney.disney.dto.CharacterFiltersDTO;
import com.challenge.disney.disney.entity.CharacterEntity;
import com.challenge.disney.disney.exception.ParamNotFound;
import com.challenge.disney.disney.mapper.CharacterMapper;
import com.challenge.disney.disney.repository.CharacterRepository;
import com.challenge.disney.disney.repository.specifications.CharacterSpecification;
import com.challenge.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    //=== Mapper ===
    private CharacterMapper characterMapper;

    //=== Repository ===
    private CharacterRepository characterRepository;

    //=== Specification ===
    private CharacterSpecification characterSpecification;
    public CharacterServiceImpl(@Autowired @Lazy CharacterMapper characterMapper, @Autowired @Lazy CharacterRepository characterRepository, @Autowired CharacterSpecification characterSpecification) {
        this.characterMapper = characterMapper;
        this.characterRepository = characterRepository;
        this.characterSpecification = characterSpecification;
    }

    //=== Post ===
    public CharacterDTO save(CharacterDTO dto){
        CharacterEntity characterEntity = characterMapper.personajeDTO2Entity(dto);
        CharacterEntity personajeSaved = characterRepository.save(characterEntity);
        CharacterDTO resultado = characterMapper.personajeEntity2Dto(personajeSaved, false);
        return resultado;
    }

    //=== Get ===
    public List<CharacterDTO> traerPersonajes(){
        List<CharacterEntity>listaEntity = characterRepository.findAll();
        List<CharacterDTO>resultado = characterMapper.personajeEntityList2DtoList(listaEntity, false);
        return resultado;
    }

    //=== Delete ===
    public void delete(Long id){
        characterRepository.deleteById(id);
    }

    //=== Put ===
    @Override
    public CharacterDTO editPersonaje(Long id, CharacterDTO edit) {
        CharacterEntity savedPersonaje = this.personajeEdit(id);
        savedPersonaje.setNombre(edit.getNombre());
        savedPersonaje.setPeso(edit.getPeso());
        savedPersonaje.setImagen(edit.getImagen());
        savedPersonaje.setEdad(edit.getEdad());
        savedPersonaje.setHistoria(edit.getHistoria());
        CharacterEntity editPersonaje = characterRepository.save(savedPersonaje);
        CharacterDTO savedDTO = characterMapper.personajeEntity2Dto(editPersonaje, false);
        return savedDTO;
    }

    CharacterEntity personajeEdit(Long id) {
        Optional<CharacterEntity>personajeEntity = characterRepository.findById(id);
        if (!personajeEntity.isPresent()){
            throw  new ParamNotFound("id no valido");
        }
        return personajeEntity.get();
    }


    //=== Filters ===
    @Override
    public List<CharacterDTO> traerPorFiltros(String nombre, Integer edad, Set<Long> peliculas) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(nombre, edad, peliculas);
        List<CharacterEntity> entityList = this.characterRepository.findAll(this.characterSpecification.getFilters(filtersDTO));
        List<CharacterDTO> resultadoDTO = this.characterMapper.personajeEntityList2DtoList(entityList, true);
        return resultadoDTO;
    }

    @Override
    public List<CharacterBasicDTO> traerPersonajesBasic() {
        List<CharacterEntity>listaEntity = characterRepository.findAll();
        List<CharacterBasicDTO>resultado = characterMapper.personajeBasicEntityList2DtoList(listaEntity);
        return resultado;
    }
}
