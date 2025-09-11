package com.diesgut.mongodb.features.invoice.controller;

import com.diesgut.mongodb.features.invoice.InvoiceService;
import com.diesgut.mongodb.features.invoice.entities.InvoiceDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/invoices")
@RestController
public class InvoiceController {
    private final InvoiceService invoiceService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public InvoiceDocument createProduct(@RequestBody InvoiceDocument invoiceDocument) {
        return invoiceService.createInvoice(invoiceDocument);
    }

    @GetMapping
    public List<InvoiceDocument> all(){
        return invoiceService.allInvoices();
    }
}
