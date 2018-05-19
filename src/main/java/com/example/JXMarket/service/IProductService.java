package com.example.JXMarket.service;

import com.example.JXMarket.entity.Product;

import java.util.List;

public interface IProductService  {
    Product createProduct(Product product);

    List<Product> getProductList();

    Product updateProduct(Long id, Product product);

    Product getProductById(Long id);

    Product getProductByName(String name);

    List<Product> getProductByNameAndDesc(String name, String description);

}
