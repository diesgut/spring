package com.bank.customer.features.person.converters;

import com.bank.customer.features.person.PersonConstants;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class DocumentTypeConverter implements AttributeConverter<PersonConstants.DocumentType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PersonConstants.DocumentType documentType) {
        if (documentType == null) {
            return null;
        }
        return documentType.getCode();
    }

    @Override
    public PersonConstants.DocumentType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(PersonConstants.DocumentType.values())
                .filter(c -> c.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}