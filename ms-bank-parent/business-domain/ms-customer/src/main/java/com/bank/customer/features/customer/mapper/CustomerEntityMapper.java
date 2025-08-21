package com.bank.customer.features.customer.mapper;

import com.bank.customer.features.customer.CustomerEntity;
import com.bank.customer.features.customer.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerEntityMapper {
    @Mapping(source = "personEntity.transaction_uuid", target = "person.transactionUuid")
    @Mapping(target = "company", source = "company")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phone_number")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "transactionUuid", source = "transaction_uuid")
    @Mapping(target = "createdAt", source = "created_at")
    @Mapping(target = "updatedAt", source = "updated_at")
    CustomerDto toDto(CustomerEntity entity);

    @Mapping(source = "personEntity.transaction_uuid", target = "person.transactionUuid")
    @Mapping(target = "company", source = "company")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phoneNumber", source = "phone_number")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "transactionUuid", source = "transaction_uuid")
    @Mapping(target = "createdAt", source = "created_at")
    @Mapping(target = "updatedAt", source = "updated_at")
    List<CustomerDto> toDtoList(List<CustomerEntity> entities);
}