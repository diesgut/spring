package com.diesgut.mongodb.features.invoice_agregation.dao;

import com.diesgut.mongodb.features.invoice_agregation.InvoiceDocumentV2;

import java.util.List;
import java.util.Optional;

public interface IInvoiceDocumentV2Dao {
    Optional<InvoiceDocumentV2> findByIdWithRelations(String id);
    Optional<InvoiceDocumentV2> findByIdWithRelationsV2(String id);
    List<InvoiceDocumentV2> allWithRelations();
}
