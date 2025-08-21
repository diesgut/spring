package com.bank.customer.features.person.imp;

import com.bank.customer.common.exception.ResourceNotFoundException;
import com.bank.customer.features.person.PersonEntity;
import com.bank.customer.features.person.PersonEntityRepository;
import com.bank.customer.features.person.PersonService;
import com.bank.customer.features.person.dto.CreatePersonDto;
import com.bank.customer.features.person.dto.PersonDto;
import com.bank.customer.features.person.dto.UpdatePersonDto;
import com.bank.customer.features.person.mapper.CreatePersonEntityMapper;
import com.bank.customer.features.person.mapper.PersonEntityMapper;
import com.bank.customer.features.person.mapper.UpdatePersonEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonServiceImp implements PersonService {

    private final PersonEntityRepository repository;;
    private final CreatePersonEntityMapper createEntityMapper;
    private final UpdatePersonEntityMapper updateEntityMapper;
    private final PersonEntityMapper entityMapper;

    @Override
    public PersonDto create(CreatePersonDto dto) {
        PersonEntity entity = createEntityMapper.toEntity(dto);
        PersonEntity savedEntity = repository.save(entity);
        return entityMapper.toDto(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findAll() {
        List<PersonEntity> entities = repository.findAll();
        return entityMapper.toDtoList(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonDto> findByTransactionUuid(UUID id) {
        return repository.findByTransactionUuid(id)
                .map(entityMapper::toDto);
    }

    @Override
    @Transactional
    public PersonDto update(UUID id, UpdatePersonDto dto) {
        PersonEntity existingEntity = repository.findByTransactionUuid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
        updateEntityMapper.partialUpdate(dto, existingEntity);
        PersonEntity updatedEntity = repository.save(existingEntity);
        return entityMapper.toDto(updatedEntity);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsByUuid(id)) {
            throw new ResourceNotFoundException("Person not found with id: " + id);
        }
        repository.deleteByUuid(id);
    }
}
