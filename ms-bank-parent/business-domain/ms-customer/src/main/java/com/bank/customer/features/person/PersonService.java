package com.bank.customer.features.person;

import com.bank.customer.features.person.dto.CreatePersonDto;
import com.bank.customer.features.person.dto.PersonDto;
import com.bank.customer.features.person.dto.UpdatePersonDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService {
    PersonDto create(CreatePersonDto createPersonDto);

    List<PersonDto> findAll();

    Optional<PersonDto> findByTransactionUuid(UUID id);

    PersonDto update(UUID id, UpdatePersonDto updatePersonDto);

    void delete(UUID id);
}
