package com.diesgut.segurity.features.person.imp;

import com.diesgut.segurity.commons.exception.ResourceNotFoundException;
import com.diesgut.segurity.features.person.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonServiceImp implements PersonService {
    private final PersonEntityMapper entityMapper;
    private final PersonEntityRepository repository;

    @Override
    public PersonDto create(PersonDto dto) {
        PersonEntity entity = entityMapper.toEntity(dto);
        PersonEntity saved = repository.save(entity);
        return entityMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findAll() {
        List<PersonEntity> entities = repository.findAll();
        return entities.stream().map(entityMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonDto> findById(Long id) {
        return repository.findById(id)
                .map(entityMapper::toDto);
    }

    @Override
    public PersonDto update(Long id, PersonDto dto) {
        PersonEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
        entityMapper.partialUpdate(dto, existingEntity);
        PersonEntity updatedEntity = repository.save(existingEntity);
        return entityMapper.toDto(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Person not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
