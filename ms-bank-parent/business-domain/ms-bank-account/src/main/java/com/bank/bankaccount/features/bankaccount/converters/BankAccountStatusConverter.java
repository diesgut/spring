package com.bank.bankaccount.features.bankaccount.converters;

import com.bank.bankaccount.features.bankaccount.BankAccountConstants;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class BankAccountStatusConverter implements AttributeConverter<BankAccountConstants.Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BankAccountConstants.Status status) {
        if (status == null) {
            return null;
        }
        return status.getCode();
    }

    @Override
    public BankAccountConstants.Status convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(BankAccountConstants.Status.values())
                .filter(c -> c.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}