package com.diesgut.segurity.features.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonEntityRepository extends JpaRepository<PersonEntity, Long> {
}