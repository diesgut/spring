package com.diesgut.mongodb.features.invoice_agregation;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceDocumentV2Repository extends MongoRepository<InvoiceDocumentV2, String> {
    @Aggregation(pipeline = {
            "{ '$match': { '_id': ?0 } }",
            // Join con Customers
            "{ '$lookup': { 'from': 'customers', 'localField': 'customerId', 'foreignField': '_id', 'as': 'customerDocument' } }",
            // Join con Products
            "{ '$lookup': { 'from': 'products', 'localField': 'productIds', 'foreignField': '_id', 'as': 'productDocuments' } }",
            // $lookup siempre devuelve un array, incluso si solo hay un elemento. Usamos $unwind para convertir el array de customerDocument en un objeto simple.
            "{ '$unwind': '$customerDocument' }"
    })
    Optional<InvoiceDocumentV2> findByIdWithRelations(String id);
}
