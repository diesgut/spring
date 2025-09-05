package com.diesgut.saga.order.doman.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "status")
    private com.diesgut.saga.commons.types.OrderStatus status;
    @Column(name = "customer_id")
    private UUID customerId;
    @Column(name = "product_id")
    private UUID productId;
    @Column(name = "product_quantity")
    private Integer productQuantity;
}
