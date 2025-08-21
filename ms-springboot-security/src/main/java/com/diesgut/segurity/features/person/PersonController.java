package com.diesgut.segurity.features.person;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService service;

    @PostMapping
    public ResponseEntity<PersonDto> create(@RequestBody PersonDto entityDTO) {
        PersonDto createdDto = service.create(entityDTO);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getAll() {
        List<PersonDto> dtos = service.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable Long id, @RequestBody PersonDto entityDTO) {
        PersonDto updatedDto = service.update(id, entityDTO);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
