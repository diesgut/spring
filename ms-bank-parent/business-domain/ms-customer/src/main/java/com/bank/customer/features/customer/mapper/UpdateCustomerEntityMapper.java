package com.bank.customer.features.customer.mapper;

import com.bank.customer.features.customer.CustomerEntity;
import com.bank.customer.features.customer.dto.UpdateCustomerDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UpdateCustomerEntityMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "person.transactionUuid", target = "personEntity.transaction_uuid")
    @Mapping(source = "company", target = "company")
    @Mapping(source = "phoneNumber", target = "phone_number")
    @Mapping(source = "status", target = "status")
    CustomerEntity partialUpdate(UpdateCustomerDto dto, @MappingTarget CustomerEntity personEntity); //update an existing personEntity object
}