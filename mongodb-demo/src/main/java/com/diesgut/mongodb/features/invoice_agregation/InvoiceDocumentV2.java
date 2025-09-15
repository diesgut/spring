package com.diesgut.mongodb.features.invoice_agregation;

import com.diesgut.mongodb.features.invoice.entities.CustomerDocument;
import com.diesgut.mongodb.features.invoice.entities.ProductDocument;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "invoices_v2")
public class InvoiceDocumentV2 {
    @Id
    private String id;

    // Almacenamos solo el ID del cliente
    private String customerId;

    // Almacenamos solo la lista de IDs de los productos
    private List<String> productIds;

    // Campos transitorios para guardar los documentos completos después de una agregación
    @Transient
    private CustomerDocument customerDocument;

    @Transient
    private List<ProductDocument> productDocuments;
}
