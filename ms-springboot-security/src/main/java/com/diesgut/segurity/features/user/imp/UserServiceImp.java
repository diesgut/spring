package com.diesgut.segurity.features.user.imp;

import com.diesgut.segurity.commons.exception.ResourceNotFoundException;
import com.diesgut.segurity.features.person.PersonEntity;
import com.diesgut.segurity.features.role.RoleEntity;
import com.diesgut.segurity.features.role.RoleEntityRepository;
import com.diesgut.segurity.features.user.CreateUserEntityMapper;
import com.diesgut.segurity.features.user.UserEntityMapper;
import com.diesgut.segurity.features.user.UserEntityRepository;
import com.diesgut.segurity.features.user.UserService;
import com.diesgut.segurity.features.user.dto.CreateUserDto;
import com.diesgut.segurity.features.user.dto.UserDto;
import com.diesgut.segurity.features.user.entity.UserEntity;
import com.diesgut.segurity.features.user.entity.UserRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final CreateUserEntityMapper createEntityMapper;
    private final UserEntityMapper entityMapper;
    private final UserEntityRepository repository;
    private final RoleEntityRepository roleEntityRepository;

    @Override
    public UserDto create(CreateUserDto dto) {
        List<RoleEntity> roles = roleEntityRepository.findByNameIn(dto.roles());

        UserEntity entity = createEntityMapper.toEntity(dto);
        UserEntity saved = repository.save(entity);

        roles.forEach(role -> {
            UserRoleEntity userRoleEntity = UserRoleEntity.builder()
                    .user(saved)
                    .role(role)
                    .build();
            saved.getUserRoles().add(userRoleEntity);
        });
        return entityMapper.toDto(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<UserEntity> entities = repository.findAll();
        return entities.stream().map(entityMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return repository.findById(id)
                .map(entityMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        UserEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        entityMapper.partialUpdate(dto, existingEntity);
        UserEntity updatedEntity = repository.save(existingEntity);
        return entityMapper.toDto(updatedEntity);
    }

    @Override
    public UserDetails findUserDetailByUsername(String username) {
        UserEntity userEntity = repository.findByUsernameWithRoles(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        return User.withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .disabled(!userEntity.getEnabled())
                .roles(userEntity.getUserRoles().stream()
                        .map(userRole -> userRole.getRole().getName())
                        .toArray(String[]::new))
                .build();
    }
}
