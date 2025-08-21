package com.bank.bankaccount.features.bankaccount.mapper;

import com.bank.bankaccount.features.bankaccount.BankAccountEntity;
import com.bank.bankaccount.features.bankaccount.dto.UpdateBankAccountDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UpdateBankAccountEntityMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "customerUuid", target = "customer_uuid")
    @Mapping(source = "bank", target = "bank")
    @Mapping(source = "totalBalance", target = "total_balance")
    @Mapping(source = "status", target = "status")
    BankAccountEntity partialUpdate(UpdateBankAccountDto dto, @MappingTarget BankAccountEntity entity); //update an existing personEntity object
}
