package com.bank.customer.features.person;

import com.bank.customer.common.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonEntityRepository extends BaseRepository<PersonEntity, Long> {
  /*  @Query("SELECT p FROM PersonEntity p WHERE p.transaction_uuid = :uuid")
    Optional<PersonEntity> findByTransactionUuid(@Param("uuid") UUID id);

    @Query("SELECT COUNT(p) > 0 FROM PersonEntity p WHERE p.transaction_uuid = :uuid")
    boolean existsByUuid(@Param("uuid") UUID uuid);

    @Modifying
    @Query("DELETE FROM PersonEntity p WHERE p.transaction_uuid = :uuid")
    void deleteByUuid(@Param("uuid") UUID uuid);*/
}