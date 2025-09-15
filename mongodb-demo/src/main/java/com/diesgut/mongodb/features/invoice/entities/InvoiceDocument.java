package com.diesgut.mongodb.features.invoice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "invoices")
public class InvoiceDocument {
    @Id
    private String id;

  //  @DBRef save like "customer": { "$ref": "customers", "$id": ObjectId("customer456") } in mongodb
    @DocumentReference // save like "customer": "customer456" in mongodb
    private CustomerDocument customerDocument;

  //  @DBRef(lazy = true) //don't work
    @DocumentReference(lazy = true)
    private List<ProductDocument> productDocuments;
}
