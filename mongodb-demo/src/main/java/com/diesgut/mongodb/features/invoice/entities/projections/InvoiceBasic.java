package com.diesgut.mongodb.features.invoice.entities.projections;

import com.diesgut.mongodb.features.invoice.entities.CustomerDocument;

public interface InvoiceBasic {
    String getId();
    CustomerDocument getCustomerDocument();
}
