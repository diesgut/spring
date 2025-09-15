package com.diesgut.mongodb.features.invoice.repositories;

import com.diesgut.mongodb.features.invoice.entities.CustomerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDocumentRepository extends MongoRepository<CustomerDocument, String> {
}
