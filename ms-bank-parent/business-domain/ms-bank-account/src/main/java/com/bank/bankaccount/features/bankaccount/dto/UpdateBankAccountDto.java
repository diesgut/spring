package com.bank.bankaccount.features.bankaccount.dto;

import com.bank.bankaccount.features.bankaccount.BankAccountConstants;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record UpdateBankAccountDto(UUID customerUuid, BankAccountConstants.Banks bank,
                                   BigDecimal totalBalance, BankAccountConstants.Status status) implements Serializable {
}