package com.diesgut.mongodb.features.invoice.repositories;

import com.diesgut.mongodb.features.invoice.entities.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductDocument, String> {
}
