package com.diesgut.mongodb.features.invoice.repositories;

import com.diesgut.mongodb.features.invoice.entities.InvoiceDocument;
import com.diesgut.mongodb.features.invoice.entities.projections.InvoiceBasic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDocumentRepository extends MongoRepository<InvoiceDocument, String> {
    List<InvoiceBasic> findAllBy();
}
