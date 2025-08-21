package com.bank.bankaccount.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@MappedSuperclass
public class BaseEntity {
    @Column(name = "transaction_uuid", unique = true, nullable = false, updatable = false, length = 36)
    private UUID transaction_uuid;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    // Callbacks de JPA para autogenerar los valores
    @PrePersist
    public void onCreate() {
        if (this.transaction_uuid == null) {
            this.transaction_uuid = UUID.randomUUID();
        }
        if (this.created_at == null) {
            this.created_at = LocalDateTime.now();
        }

        if (this.updated_at == null) {
            this.updated_at = this.created_at;
        }
    }

    @PreUpdate
    public void onUpdate() {
        this.updated_at = LocalDateTime.now();
    }
}
