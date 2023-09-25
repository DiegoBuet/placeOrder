package com.app.web.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "list_purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product", nullable = false)
    private String product;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false, unique = true)
    private Integer quantity;

    @Column(name = "product_code", nullable = false, unique = true)
    private Integer productCode;

    @Column(name = "total", nullable = false)
    private Double total;


}
