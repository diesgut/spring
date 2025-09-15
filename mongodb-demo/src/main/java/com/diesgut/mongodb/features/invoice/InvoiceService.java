package com.diesgut.mongodb.features.invoice;

import com.diesgut.mongodb.features.invoice.dao.IInvoiceDao;
import com.diesgut.mongodb.features.invoice.entities.CustomerDocument;
import com.diesgut.mongodb.features.invoice.entities.InvoiceDocument;
import com.diesgut.mongodb.features.invoice.entities.ProductDocument;
import com.diesgut.mongodb.features.invoice.entities.projections.InvoiceBasic;
import com.diesgut.mongodb.features.invoice.repositories.CustomerDocumentRepository;
import com.diesgut.mongodb.features.invoice.repositories.ProductDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InvoiceService {
    private final CustomerDocumentRepository customerRepository;
    private final IInvoiceDao iInvoiceDao;
    private final ProductDocumentRepository productRepository;

    public InvoiceDocument createInvoice(InvoiceDocument invoiceDocument) {
        if(invoiceDocument.getCustomerDocument() != null){
            customerRepository.findById(invoiceDocument.getCustomerDocument().getId())
                    .ifPresent(invoiceDocument::setCustomerDocument);
        }
        if(invoiceDocument.getProductDocuments() != null && !invoiceDocument.getProductDocuments().isEmpty()){
            List<String> productIds = invoiceDocument.getProductDocuments().stream()
                    .map(ProductDocument::getId)
                    .toList();
            List<ProductDocument> fetchedProducts = productRepository.findAllById(productIds);
            invoiceDocument.setProductDocuments(fetchedProducts);
        }
        return iInvoiceDao.save(invoiceDocument);
    }

    public List<InvoiceDocument> allInvoices() {
        return iInvoiceDao.allWithoutProducts();
    }

    public List<InvoiceBasic> allInvoicesForProjections() {
        return iInvoiceDao.allForProjections();
    }

    public CustomerDocument createCustomer(CustomerDocument customerDocument) {
        return customerRepository.save(customerDocument);
    }

    public ProductDocument createProduct(ProductDocument productDocument) {
        return productRepository.save(productDocument);
    }

    public List<CustomerDocument> allCustomers() {
        return customerRepository.findAll();
    }

    public List<ProductDocument> allProducts() {
        return productRepository.findAll();
    }
}
