package com.bank.bankaccount.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository <T extends BaseEntity, ID> extends JpaRepository<T, ID> {
    @Query("SELECT e FROM #{#entityName} e WHERE e.transaction_uuid = :uuid")
    Optional<T> findByTransactionUuid(@Param("uuid") UUID id);

    @Query("SELECT COUNT(e) > 0 FROM #{#entityName} e WHERE e.transaction_uuid = :uuid")
    boolean existsByUuid(@Param("uuid") UUID uuid);

    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.transaction_uuid = :uuid")
    void deleteByUuid(@Param("uuid") UUID uuid);
}
