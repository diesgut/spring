package com.bank.customer.features.customer;

import com.bank.customer.common.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEntityRepository extends BaseRepository<CustomerEntity, Long> {
}