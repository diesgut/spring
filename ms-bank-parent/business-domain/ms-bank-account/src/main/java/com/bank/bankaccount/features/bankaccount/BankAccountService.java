package com.bank.bankaccount.features.bankaccount;

import com.bank.bankaccount.features.bankaccount.dto.BankAccountDto;
import com.bank.bankaccount.features.bankaccount.dto.CreateBankAccountDto;
import com.bank.bankaccount.features.bankaccount.dto.UpdateBankAccountDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankAccountService {
    BankAccountDto create(CreateBankAccountDto dto);

    List<BankAccountDto> findAll();

    Optional<BankAccountDto> findByTransactionUuid(UUID id);

    BankAccountDto update(UUID id, UpdateBankAccountDto dto);

    void delete(UUID id);
}
