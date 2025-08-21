package com.bank.customer.features.person.mapper;

import com.bank.customer.features.person.PersonEntity;
import com.bank.customer.features.person.dto.CreatePersonDto;
import com.bank.customer.features.person.dto.PersonDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonEntityMapper {
    @Mapping(target = "firstName", source = "first_name")
    @Mapping(target = "lastName", source = "last_name")
    @Mapping(target = "documentType", source = "document_type")
    @Mapping(target = "documentNumber", source = "document_number")
    @Mapping(target = "transactionUuid", source = "transaction_uuid")
    @Mapping(target = "createdAt", source = "created_at")
    @Mapping(target = "updatedAt", source = "updated_at")
    PersonDto toDto(PersonEntity entity);

    @Mapping(target = "firstName", source = "first_name")
    @Mapping(target = "lastName", source = "last_name")
    @Mapping(target = "documentType", source = "document_type")
    @Mapping(target = "documentNumber", source = "document_number")
    @Mapping(target = "transactionUuid", source = "transaction_uuid")
    @Mapping(target = "createdAt", source = "created_at")
    @Mapping(target = "updatedAt", source = "updated_at")
    List<PersonDto> toDtoList(List<PersonEntity> entities);
}