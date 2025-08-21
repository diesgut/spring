package com.bank.customer.features.customer.imp;

import com.bank.customer.common.exception.ResourceNotFoundException;
import com.bank.customer.features.customer.CustomerEntity;
import com.bank.customer.features.customer.CustomerEntityRepository;
import com.bank.customer.features.customer.CustomerService;
import com.bank.customer.features.customer.dto.CreateCustomerDto;
import com.bank.customer.features.customer.dto.CustomerDto;
import com.bank.customer.features.customer.dto.UpdateCustomerDto;
import com.bank.customer.features.customer.mapper.CreateCustomerEntityMapper;
import com.bank.customer.features.customer.mapper.CustomerEntityMapper;
import com.bank.customer.features.customer.mapper.UpdateCustomerEntityMapper;
import com.bank.customer.features.person.PersonEntity;
import com.bank.customer.features.person.PersonEntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {

    private final CustomerEntityRepository repository;
    private final CreateCustomerEntityMapper createEntityMapper;
    private final UpdateCustomerEntityMapper updateEntityMapper;
    private final CustomerEntityMapper entityMapper;

    private final PersonEntityRepository personEntityRepository;


    @Override
    public CustomerDto create(CreateCustomerDto dto) {
        CustomerEntity entity = createEntityMapper.toEntity(dto);

        PersonEntity personEntity = personEntityRepository.findByTransactionUuid(dto.person().transactionUuid())
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con ID: " + dto.person().transactionUuid()));

        entity.setPersonEntity(personEntity);
        CustomerEntity saved = repository.save(entity);
        return entityMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> findAll() {
        List<CustomerEntity> entities = repository.findAll();
        return entityMapper.toDtoList(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDto> findByTransactionUuid(UUID id) {
        return repository.findByTransactionUuid(id)
                .map(entityMapper::toDto);
    }

    @Override
    public CustomerDto update(UUID id, UpdateCustomerDto dto) {
        CustomerEntity existingEntity = repository.findByTransactionUuid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        updateEntityMapper.partialUpdate(dto, existingEntity);
        CustomerEntity updatedEntity = repository.save(existingEntity);
        return entityMapper.toDto(updatedEntity);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsByUuid(id)) {
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }
        repository.deleteByUuid(id);
    }
}
