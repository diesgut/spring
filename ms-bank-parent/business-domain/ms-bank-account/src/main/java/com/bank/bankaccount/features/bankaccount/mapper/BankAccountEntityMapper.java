package com.bank.bankaccount.features.bankaccount.mapper;

import com.bank.bankaccount.features.bankaccount.BankAccountEntity;
import com.bank.bankaccount.features.bankaccount.dto.BankAccountDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BankAccountEntityMapper {
    @Mapping(target = "customerUuid", source = "customer_uuid")
    @Mapping(target = "bank", source = "bank")
    @Mapping(target = "totalBalance", source = "total_balance")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "transactionUuid", source = "transaction_uuid")
    @Mapping(target = "createdAt", source = "created_at")
    @Mapping(target = "updatedAt", source = "updated_at")
    BankAccountDto toDto(BankAccountEntity bankAccountEntity);
}