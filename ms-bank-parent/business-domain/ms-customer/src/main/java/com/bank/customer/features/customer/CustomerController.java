package com.bank.customer.features.customer;

import com.bank.customer.common.constants.ApiConstants;
import com.bank.customer.features.customer.dto.CreateCustomerDto;
import com.bank.customer.features.customer.dto.CustomerDto;
import com.bank.customer.features.customer.dto.UpdateCustomerDto;
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
@RequestMapping(ApiConstants.API_V1_VERSION + "customers")
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CreateCustomerDto dto) {
        log.info("Creating new customer with details: {}", dto);
        CustomerDto createdDto = service.create(dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        log.info("Fetching all customers");
        List<CustomerDto> dtos = service.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable("id") UUID id) {
        log.info("Fetching customer with ID: {}", id);
        return service.findByTransactionUuid(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable("id") UUID id, @RequestBody UpdateCustomerDto dto) {
        log.info("Updating customer with ID: {} with details: {}", id, dto);
        CustomerDto updatedDto = service.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        log.info("Deleting customer with ID: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}