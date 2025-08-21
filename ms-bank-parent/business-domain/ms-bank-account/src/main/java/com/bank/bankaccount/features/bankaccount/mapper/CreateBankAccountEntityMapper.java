package com.bank.bankaccount.features.bankaccount.mapper;

import com.bank.bankaccount.features.bankaccount.BankAccountEntity;
import com.bank.bankaccount.features.bankaccount.dto.BankAccountDto;
import com.bank.bankaccount.features.bankaccount.dto.CreateBankAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreateBankAccountEntityMapper {
    @Mapping(target = "customer_uuid", source = "customerUuid")
    @Mapping(target = "bank", source = "bank")
    @Mapping(target = "total_balance", source = "totalBalance")
    @Mapping(target = "status", source = "status")
    BankAccountEntity toEntity(CreateBankAccountDto dto);

    @Mapping(target = "customerUuid", source = "customer_uuid")
    @Mapping(target = "bank", source = "bank")
    @Mapping(target = "totalBalance", source = "total_balance")
    @Mapping(target = "status", source = "status")
    CreateBankAccountDto toDto(BankAccountEntity entity);
}