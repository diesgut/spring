package com.diesgut.segurity.features.role;

import com.diesgut.segurity.features.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findByNameIn(List<String> names);
}
