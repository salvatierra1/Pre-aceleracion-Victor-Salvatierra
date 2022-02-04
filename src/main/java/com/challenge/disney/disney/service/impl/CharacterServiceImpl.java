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
    public CharacterServiceImpl(@Autowired CharacterMapper characterMapper, @Autowired  CharacterRepository characterRepository, @Autowired CharacterSpecification characterSpecification) {
        this.characterMapper = characterMapper;
        this.characterRepository = characterRepository;
        this.characterSpecification = characterSpecification;
    }

    //=== Post ===
    public CharacterDTO save(CharacterDTO dto){
        CharacterEntity characterEntity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity personajeSaved = characterRepository.save(characterEntity);
        CharacterDTO resultado = characterMapper.characterEntity2Dto(personajeSaved, false);
        return resultado;
    }

    //=== Get ===
    public List<CharacterDTO> getCharacter(){
        List<CharacterEntity>listEntity = characterRepository.findAll();
        List<CharacterDTO>result = characterMapper.characterEntityList2DtoList(listEntity, false);
        return result;
    }

    @Override
    public CharacterDTO getCharacterDetails(Long id) {
        Optional<CharacterEntity> entity = this.characterRepository.findById(id);
        if (!entity.isPresent()){
            throw new ParamNotFound("id no valido");
        }
        CharacterDTO result = this.characterMapper.characterEntity2Dto(entity.get(), true);
        return result;
    }

    //=== Delete ===
    public void delete(Long id){
        characterRepository.deleteById(id);
    }

    //=== Put ===
    @Override
    public CharacterDTO editCharacter(Long id, CharacterDTO edit) {
        CharacterEntity savedCharacter = this.getCharacterEdit(id);
        savedCharacter.setName(edit.getName());
        savedCharacter.setWeight(edit.getWeight());
        savedCharacter.setImage(edit.getImage());
        savedCharacter.setAge(edit.getAge());
        savedCharacter.setHistory(edit.getHistory());
        CharacterEntity editPersonaje = characterRepository.save(savedCharacter);
        CharacterDTO savedDTO = characterMapper.characterEntity2Dto(editPersonaje, false);
        return savedDTO;
    }

    CharacterEntity getCharacterEdit(Long id) {
        Optional<CharacterEntity>characterEntity = characterRepository.findById(id);
        if (!characterEntity.isPresent()){
            throw  new ParamNotFound("id no valido");
        }
        return characterEntity.get();
    }

    //=== Filters ===
    @Override
    public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, movies);
        List<CharacterEntity> entityList = this.characterRepository.findAll(this.characterSpecification.getFilters(filtersDTO));
        List<CharacterDTO> resultDTO = this.characterMapper.characterEntityList2DtoList(entityList, true);
        return resultDTO;
    }

    //=== Basic ===
    @Override
    public List<CharacterBasicDTO> getCharacterBasic() {
        List<CharacterEntity>listEntity = characterRepository.findAll();
        List<CharacterBasicDTO>result = characterMapper.characterBasicEntityList2DtoList(listEntity);
        return result;
    }

}
