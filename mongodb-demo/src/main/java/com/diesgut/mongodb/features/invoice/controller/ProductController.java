package com.diesgut.mongodb.features.invoice.controller;

import com.diesgut.mongodb.features.invoice.InvoiceService;
import com.diesgut.mongodb.features.invoice.entities.ProductDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductController {
    private final InvoiceService invoiceService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductDocument create(@RequestBody ProductDocument productDocument) {
        return invoiceService.createProduct(productDocument);
    }

    @GetMapping
    public List<ProductDocument> all() {
        return invoiceService.allProducts();
    }
}
