package com.bank.customer.features.person;

import com.bank.customer.common.constants.ApiConstants;
import com.bank.customer.features.person.dto.CreatePersonDto;
import com.bank.customer.features.person.dto.PersonDto;
import com.bank.customer.features.person.dto.UpdatePersonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(ApiConstants.API_V1_VERSION + "persons")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody CreatePersonDto crreatePersonDto) {
        log.info("Creating new person with data: {}", crreatePersonDto);
        PersonDto createdDto = personService.create(crreatePersonDto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAll() {
        log.info("Fetching all persons");
        List<PersonDto> dtos = personService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getById(@PathVariable("id") UUID id) {
        log.info("Fetching person with transaction UUID: {}", id);
        return personService.findByTransactionUuid(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable("id") UUID id, @RequestBody UpdatePersonDto updatePersonDto) {
        log.info("Updating person with transaction UUID: {} with data: {}", id, updatePersonDto);
        PersonDto updatedDto = personService.update(id, updatePersonDto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        log.info("Deleting person with transaction UUID: {}", id);
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}