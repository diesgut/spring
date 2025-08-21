package com.bank.customer.features.person.mapper;

import com.bank.customer.features.person.PersonEntity;
import com.bank.customer.features.person.dto.CreatePersonDto;
import com.bank.customer.features.person.dto.UpdatePersonDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UpdatePersonEntityMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "firstName", target = "first_name")
    @Mapping(source = "lastName", target = "last_name")
    @Mapping(source = "documentType", target = "document_type")
    @Mapping(source = "documentNumber", target = "document_number")
    PersonEntity partialUpdate(UpdatePersonDto updatePersonDto, @MappingTarget PersonEntity personEntity); //update an existing personEntity object
}