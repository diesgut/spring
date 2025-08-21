package com.bank.customer.features.person.mapper;

import com.bank.customer.features.person.PersonEntity;
import com.bank.customer.features.person.dto.CreatePersonDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreatePersonEntityMapper {
    @Mapping(source = "firstName", target = "first_name")
    @Mapping(source = "lastName", target = "last_name")
    @Mapping(source = "documentType", target = "document_type")
    @Mapping(source = "documentNumber", target = "document_number")
    PersonEntity toEntity(CreatePersonDto personDto);

    @Mapping(target = "firstName", source = "first_name")
    @Mapping(target = "lastName", source = "last_name")
    @Mapping(target = "documentType", source = "document_type")
    @Mapping(target = "documentNumber", source = "document_number")
    CreatePersonDto toDto(PersonEntity personEntity);
}