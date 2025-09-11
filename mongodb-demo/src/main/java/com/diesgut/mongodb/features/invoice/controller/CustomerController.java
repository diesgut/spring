package com.diesgut.mongodb.features.invoice.controller;

import com.diesgut.mongodb.features.invoice.InvoiceService;
import com.diesgut.mongodb.features.invoice.entities.CustomerDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/customers")
@RestController
public class CustomerController {
    private final InvoiceService invoiceService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CustomerDocument create(@RequestBody CustomerDocument customerDocument) {
        return invoiceService.createCustomer(customerDocument);
    }

    @GetMapping
    public List<CustomerDocument> all() {
        return invoiceService.allCustomers();
    }
}
