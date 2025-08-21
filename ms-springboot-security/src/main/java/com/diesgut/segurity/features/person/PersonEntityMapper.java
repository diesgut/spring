package com.diesgut.segurity.features.person;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonEntityMapper {
    @Mapping(source = "firstName", target = "first_name")
    @Mapping(source = "lastName", target = "last_name")
    PersonEntity toEntity(PersonDto dto);

    @Mapping(source = "first_name", target = "firstName")
    @Mapping(source = "last_name", target = "lastName")
    PersonDto toDto(PersonEntity entity);

    @Mapping(source = "firstName", target = "first_name")
    @Mapping(source = "lastName", target = "last_name")
    void partialUpdate(PersonDto dto, @MappingTarget PersonEntity entity);
}
