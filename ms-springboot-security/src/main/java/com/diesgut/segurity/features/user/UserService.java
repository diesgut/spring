package com.diesgut.segurity.features.user;


import com.diesgut.segurity.features.person.PersonDto;
import com.diesgut.segurity.features.user.dto.CreateUserDto;
import com.diesgut.segurity.features.user.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto create(CreateUserDto userDto);

    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    void delete(Long id);

    UserDto update(Long id, UserDto userDto);

    UserDetails findUserDetailByUsername(String username);
}
