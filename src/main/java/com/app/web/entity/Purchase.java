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
/*
    public Purchase() {
    }

    public Purchase(String product, Double price, Integer quantity, Integer productCode, Double total) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.productCode = productCode;
        this.total = total;
    }

    public Purchase(Long id, String product, Double price, Integer quantity, Integer productCode, Double total) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.productCode = productCode;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @PrePersist
    @PreUpdate
    private void calculateTotal() {
        this.total = price * quantity;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }*/

}
