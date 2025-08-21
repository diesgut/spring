package com.bank.customer.features.person;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class PersonConstants {
    @Getter
    @RequiredArgsConstructor
    public enum DocumentType {
        DNI(1),
        PASSPORT(2);
        private final int code;
    }
}
