package com.example.JXMarket.entity;

import javax.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private int productNumber;

    private int productLockNumber;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id")
    private Product product;

    public Inventory(){}

    public Inventory(Long productId, int productNumber) {
        this.productId = productId;
        this.productNumber = productNumber;
    }

    public Inventory(Long productId, int productNumber, int productLockNumber) {
        this.productId = productId;
        this.productNumber = productNumber;
        this.productLockNumber = productLockNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public int getProductLockNumber() {
        return productLockNumber;
    }

    public void setProductLockNumber(int productLockNumber) {
        this.productLockNumber = productLockNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
