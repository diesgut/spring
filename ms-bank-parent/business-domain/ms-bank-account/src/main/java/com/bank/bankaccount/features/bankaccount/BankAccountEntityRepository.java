package com.bank.bankaccount.features.bankaccount;

import com.bank.bankaccount.common.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountEntityRepository extends BaseRepository<BankAccountEntity, Long> {
}