package com.bank.bankaccount.features.bankaccount;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class BankAccountConstants {
    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ACTIVE(1),
        INACTIVE(2);
        private final int code;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Banks {
        INTERBANK,
        BCP,
        CAJA_CARHUAMAYO;
    }
}
