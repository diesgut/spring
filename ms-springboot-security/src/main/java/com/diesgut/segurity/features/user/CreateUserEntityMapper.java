package com.diesgut.segurity.features.user;

import com.diesgut.segurity.features.user.dto.CreateUserDto;
import com.diesgut.segurity.features.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CreateUserEntityMapper {
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    UserEntity toEntity(CreateUserDto dto);
}
