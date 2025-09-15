package com.diesgut.mongodb.features.invoice.dao.imp;

import com.diesgut.mongodb.features.invoice.dao.IInvoiceDao;
import com.diesgut.mongodb.features.invoice.entities.InvoiceDocument;
import com.diesgut.mongodb.features.invoice.entities.projections.InvoiceBasic;
import com.diesgut.mongodb.features.invoice.repositories.InvoiceDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class InvoiceDaoImp implements IInvoiceDao {
    private final InvoiceDocumentRepository invoiceRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public List<InvoiceDocument> allInvoices() {
        return invoiceRepository.findAll();
    }

    public List<InvoiceDocument> allWithoutProducts() {
        Query query = new Query();
        query.fields().exclude("productDocuments");
        return mongoTemplate.find(query, InvoiceDocument.class);
    }

    @Override
    public List<InvoiceBasic> allForProjections() {
        return invoiceRepository.findAllBy();
    }

    @Override
    public InvoiceDocument save(InvoiceDocument invoiceDocument) {
        return invoiceRepository.save(invoiceDocument);
    }
}
