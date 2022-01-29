package com.challenge.disney.disney.service.impl;

import com.challenge.disney.disney.dto.PersonajeBasicDTO;
import com.challenge.disney.disney.dto.PersonajeDTO;
import com.challenge.disney.disney.dto.PersonajeFiltersDTO;
import com.challenge.disney.disney.entity.PersonajeEntity;
import com.challenge.disney.disney.exception.ParamNotFound;
import com.challenge.disney.disney.mapper.PersonajeMapper;
import com.challenge.disney.disney.repository.PersonajeRepository;
import com.challenge.disney.disney.repository.specifications.PersonajeSpecification;
import com.challenge.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    //=== Mapper ===
    private  PersonajeMapper personajeMapper;

    //=== Repository ===
    private  PersonajeRepository personajeRepository;

    //=== Specification ===
    private PersonajeSpecification personajeSpecification;
    public PersonajeServiceImpl(@Autowired @Lazy PersonajeMapper personajeMapper,@Autowired @Lazy PersonajeRepository personajeRepository,@Autowired PersonajeSpecification personajeSpecification) {
        this.personajeMapper = personajeMapper;
        this.personajeRepository = personajeRepository;
        this.personajeSpecification = personajeSpecification;
    }

    //=== Post ===
    public PersonajeDTO save(PersonajeDTO dto){
        PersonajeEntity personajeEntity = personajeMapper.characterDTO2Entity(dto);
        PersonajeEntity personajeSaved = personajeRepository.save(personajeEntity);
        PersonajeDTO resultado = personajeMapper.characterEntity2Dto(personajeSaved, false);
        return resultado;
    }

    //=== Get ===
    public List<PersonajeDTO> traerPersonajes(){
        List<PersonajeEntity>listaEntity = personajeRepository.findAll();
        List<PersonajeDTO>result = personajeMapper.characterEntityList2DtoList(listaEntity, false);
        return result;
    }

    //=== Delete ===
    public void delete(Long id){
        personajeRepository.deleteById(id);
    }

    //=== Put ===
    @Override
    public PersonajeDTO editCharacter(Long id, PersonajeDTO edit) {
        PersonajeEntity savedPersonaje = this.personajeEdit(id);
        savedPersonaje.setName(edit.getName());
        savedPersonaje.setWeight(edit.getWeight());
        savedPersonaje.setImage(edit.getImage());
        savedPersonaje.setAge(edit.getAge());
        savedPersonaje.setHistory(edit.getHistory());
        PersonajeEntity editPersonaje = personajeRepository.save(savedPersonaje);
        PersonajeDTO savedDTO = personajeMapper.characterEntity2Dto(editPersonaje, false);
        return savedDTO;
    }

    PersonajeEntity personajeEdit(Long id) {
        Optional<PersonajeEntity>personajeEntity = personajeRepository.findById(id);
        if (!personajeEntity.isPresent()){
            throw  new ParamNotFound("id no valido");
        }
        return personajeEntity.get();
    }


    //=== Filters ===
    @Override
    public List<PersonajeDTO> getByFilters(String name, Integer age, Set<Long> movies) {
        PersonajeFiltersDTO filtersDTO = new PersonajeFiltersDTO(name, age, movies);
        List<PersonajeEntity> entityList = this.personajeRepository.findAll(this.personajeSpecification.getFilters(filtersDTO));
        List<PersonajeDTO> resultDTO = this.personajeMapper.characterEntityList2DtoList(entityList, true);
        return resultDTO;
    }

    @Override
    public List<PersonajeBasicDTO> getCharacterBasic() {
        List<PersonajeEntity>listaEntity = personajeRepository.findAll();
        List<PersonajeBasicDTO>result = personajeMapper.characterBasicEntityList2DtoList(listaEntity);
        return result;
    }
}
