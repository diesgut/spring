package com.bank.customer.features.customer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class CustomerConstants {
    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ACTIVE(1),
        INACTIVE(2);
        private final int code;
    }
}
