package com.diesgut.segurity.features.user;

import com.diesgut.segurity.features.person.PersonDto;
import com.diesgut.segurity.features.user.dto.UserDto;
import com.diesgut.segurity.features.user.entity.UserEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntityMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "enabled", target = "enabled")
    UserEntity toEntity(UserDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "enabled", target = "enabled")
    UserDto toDto(UserEntity entity);

    @Mapping(source = "enabled", target = "enabled")
    void partialUpdate(UserDto dto, @MappingTarget UserEntity entity);
}
