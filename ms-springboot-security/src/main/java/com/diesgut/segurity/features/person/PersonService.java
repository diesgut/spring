package com.diesgut.segurity.features.person;


import java.util.List;
import java.util.Optional;

public interface PersonService {
    PersonDto create(PersonDto entityDTO);

    List<PersonDto> findAll();

    Optional<PersonDto> findById(Long id);

    void delete(Long id);

    PersonDto update(Long id, PersonDto entityDTO);
}
