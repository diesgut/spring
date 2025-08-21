package com.bank.customer.features.customer.converters;

import com.bank.customer.features.customer.CustomerConstants;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CustomerStatusConverter implements AttributeConverter<CustomerConstants.Status, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CustomerConstants.Status status) {
        if (status == null) {
            return null;
        }
        return status.getCode();
    }

    @Override
    public CustomerConstants.Status convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(CustomerConstants.Status.values())
                .filter(c -> c.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}