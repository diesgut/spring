package com.diesgut.mongodb.features.invoice.repositories;

import com.diesgut.mongodb.features.invoice.entities.InvoiceDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends MongoRepository<InvoiceDocument, String> {
}
