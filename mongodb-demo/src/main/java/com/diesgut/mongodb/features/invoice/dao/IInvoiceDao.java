package com.diesgut.mongodb.features.invoice.dao;

import com.diesgut.mongodb.features.invoice.entities.InvoiceDocument;
import com.diesgut.mongodb.features.invoice.entities.projections.InvoiceBasic;

import java.util.List;

public interface IInvoiceDao {
    List<InvoiceDocument> allInvoices();
    List<InvoiceDocument> allWithoutProducts();
    List<InvoiceBasic> allForProjections();
    InvoiceDocument save(InvoiceDocument invoiceDocument);
}
