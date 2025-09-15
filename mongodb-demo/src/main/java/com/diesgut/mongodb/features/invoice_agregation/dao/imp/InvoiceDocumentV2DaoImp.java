package com.diesgut.mongodb.features.invoice_agregation.dao.imp;

import com.diesgut.mongodb.features.invoice_agregation.InvoiceDocumentV2;
import com.diesgut.mongodb.features.invoice_agregation.InvoiceDocumentV2Repository;
import com.diesgut.mongodb.features.invoice_agregation.dao.IInvoiceDocumentV2Dao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class InvoiceDocumentV2DaoImp implements IInvoiceDocumentV2Dao {
    private final InvoiceDocumentV2Repository invoiceDocumentV2Repository;
    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<InvoiceDocumentV2> findByIdWithRelations(String id) {
        return invoiceDocumentV2Repository.findByIdWithRelations(id);
    }

    @Override
    public Optional<InvoiceDocumentV2> findByIdWithRelationsV2(String id) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("_id").is(id)),
                Aggregation.lookup("customers", "customerId", "_id", "customerDocument"),
                Aggregation.lookup("products", "productIds", "_id", "productDocuments"),
                Aggregation.unwind("customerDocument")
        );

        List<InvoiceDocumentV2> results = mongoTemplate.aggregate(
                aggregation, "invoices_v2", InvoiceDocumentV2.class
        ).getMappedResults();

        return results.isEmpty() ? Optional.empty() : Optional.of(results.getFirst());
    }

    @Override
    public List<InvoiceDocumentV2> allWithRelations() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.lookup("customers", "customerId", "_id", "customerDocument"),
                Aggregation.lookup("products", "productIds", "_id", "productDocuments"),
                Aggregation.unwind("customerDocument")
        );

        return mongoTemplate.aggregate(aggregation, "invoices", InvoiceDocumentV2.class)
                .getMappedResults();
    }
}
