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
        PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity personajeSaved = personajeRepository.save(personajeEntity);
        PersonajeDTO resultado = personajeMapper.personajeEntity2Dto(personajeSaved, false);
        return resultado;
    }

    //=== Get ===
    public List<PersonajeDTO> traerPersonajes(){
        List<PersonajeEntity>listaEntity = personajeRepository.findAll();
        List<PersonajeDTO>resultado = personajeMapper.personajeEntityList2DtoList(listaEntity, false);
        return resultado;
    }

    //=== Delete ===
    public void delete(Long id){
        personajeRepository.deleteById(id);
    }

    //=== Put ===
    @Override
    public PersonajeDTO editPersonaje(Long id, PersonajeDTO edit) {
        PersonajeEntity savedPersonaje = this.personajeEdit(id);
        savedPersonaje.setNombre(edit.getNombre());
        savedPersonaje.setPeso(edit.getPeso());
        savedPersonaje.setImagen(edit.getImagen());
        savedPersonaje.setEdad(edit.getEdad());
        savedPersonaje.setHistoria(edit.getHistoria());
        PersonajeEntity editPersonaje = personajeRepository.save(savedPersonaje);
        PersonajeDTO savedDTO = personajeMapper.personajeEntity2Dto(editPersonaje, false);
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
    public List<PersonajeDTO> traerPorFiltros(String nombre, Integer edad, Set<Long> peliculas) {
        PersonajeFiltersDTO filtersDTO = new PersonajeFiltersDTO(nombre, edad, peliculas);
        List<PersonajeEntity> entityList = this.personajeRepository.findAll(this.personajeSpecification.getFilters(filtersDTO));
        List<PersonajeDTO> resultadoDTO = this.personajeMapper.personajeEntityList2DtoList(entityList, true);
        return resultadoDTO;
    }

    @Override
    public List<PersonajeBasicDTO> traerPersonajesBasic() {
        List<PersonajeEntity>listaEntity = personajeRepository.findAll();
        List<PersonajeBasicDTO>resultado = personajeMapper.personajeBasicEntityList2DtoList(listaEntity);
        return resultado;
    }
}
