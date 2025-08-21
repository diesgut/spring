package com.bank.customer.features.customer;

import com.bank.customer.features.customer.dto.CreateCustomerDto;
import com.bank.customer.features.customer.dto.CustomerDto;
import com.bank.customer.features.customer.dto.UpdateCustomerDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    CustomerDto create(CreateCustomerDto dto);

    List<CustomerDto> findAll();

    Optional<CustomerDto> findByTransactionUuid(UUID id);

    CustomerDto update(UUID id, UpdateCustomerDto dto);

    void delete(UUID id);
}
