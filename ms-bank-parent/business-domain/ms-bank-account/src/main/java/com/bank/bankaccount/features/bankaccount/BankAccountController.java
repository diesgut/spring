package com.bank.bankaccount.features.bankaccount;

import com.bank.bankaccount.common.constants.ApiConstants;
import com.bank.bankaccount.features.bankaccount.dto.BankAccountDto;
import com.bank.bankaccount.features.bankaccount.dto.CreateBankAccountDto;
import com.bank.bankaccount.features.bankaccount.dto.UpdateBankAccountDto;
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
@RequestMapping(ApiConstants.API_V1_VERSION + "bank-accounts")
public class BankAccountController {

    private final BankAccountService service;

    @PostMapping
    public ResponseEntity<BankAccountDto> create(@RequestBody CreateBankAccountDto dto) {
        log.info("Creating new bank account with data: {}", dto);
        BankAccountDto createdDto = service.create(dto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BankAccountDto>> getAll() {
        log.info("Fetching all bank accounts");
        List<BankAccountDto> dtos = service.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDto> getById(@PathVariable("id") UUID id) {
        log.info("Fetching bank account with transaction UUID: {}", id);
        return service.findByTransactionUuid(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountDto> update(@PathVariable("id") UUID id, @RequestBody UpdateBankAccountDto dto) {
        log.info("Updating bank account with transaction UUID: {} with data: {}", id, dto);
        BankAccountDto updatedDto = service.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        log.info("Deleting bank account with transaction UUID: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}