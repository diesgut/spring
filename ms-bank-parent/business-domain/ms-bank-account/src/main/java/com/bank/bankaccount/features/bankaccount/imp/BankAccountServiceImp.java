package com.bank.bankaccount.features.bankaccount.imp;

import com.bank.bankaccount.common.exception.ResourceNotFoundException;
import com.bank.bankaccount.features.bankaccount.BankAccountEntity;
import com.bank.bankaccount.features.bankaccount.BankAccountEntityRepository;
import com.bank.bankaccount.features.bankaccount.BankAccountService;
import com.bank.bankaccount.features.bankaccount.dto.BankAccountDto;
import com.bank.bankaccount.features.bankaccount.dto.CreateBankAccountDto;
import com.bank.bankaccount.features.bankaccount.dto.UpdateBankAccountDto;
import com.bank.bankaccount.features.bankaccount.mapper.BankAccountEntityMapper;
import com.bank.bankaccount.features.bankaccount.mapper.CreateBankAccountEntityMapper;
import com.bank.bankaccount.features.bankaccount.mapper.UpdateBankAccountEntityMapper;
import com.bank.bankaccount.features.customer.CustomerWfClient;
import com.bank.bankaccount.features.customer.dto.CustomerDto;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BankAccountServiceImp implements BankAccountService {
    private final CustomerWfClient  customerWfClient;
    private final BankAccountEntityRepository repository;
    private final CreateBankAccountEntityMapper createEntityMapper;
    private final UpdateBankAccountEntityMapper updateEntityMapper;
    private final BankAccountEntityMapper entityMapper;

    private final String MAIN_ENTITY = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, BankAccountEntity.class.getSimpleName());

    @Override
    public BankAccountDto create(CreateBankAccountDto dto) {
        CustomerDto customerDto = customerWfClient.findCustomerById(dto.customerUuid());
        BankAccountEntity entity = createEntityMapper.toEntity(dto);
        BankAccountEntity saved = repository.save(entity);
        return entityMapper.toDto(saved);
    }

    @Override
    public List<BankAccountDto> findAll() {
        List<BankAccountEntity> entities = repository.findAll();
        return entities.stream().map(entityMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<BankAccountDto> findByTransactionUuid(UUID id) {
        return repository.findByTransactionUuid(id)
                .map(entityMapper::toDto);
    }

    @Override
    public BankAccountDto update(UUID id, UpdateBankAccountDto dto) {
        BankAccountEntity existingEntity = repository.findByTransactionUuid(id)
                .orElseThrow(() -> new ResourceNotFoundException(MAIN_ENTITY +" not found with id: " + id));
        updateEntityMapper.partialUpdate(dto, existingEntity);
        BankAccountEntity updatedEntity = repository.save(existingEntity);
        return entityMapper.toDto(updatedEntity);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsByUuid(id)) {
            throw new ResourceNotFoundException(MAIN_ENTITY +" not found with id: " + id);
        }
        repository.deleteByUuid(id);
    }
}
