package com.example.JXMarket.service;

import com.example.JXMarket.entity.Inventory;
import com.example.JXMarket.entity.Product;
import com.example.JXMarket.exception.AlreadyExistEx;
import com.example.JXMarket.exception.NotFoundEx;
import com.example.JXMarket.repository.InventoryRepository;
import com.example.JXMarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService implements IProductService {

    @Autowired
    ProductRepository mProductRepository;

    @Autowired
    InventoryRepository mInventoryRepository;
    @Override
    public Product createProduct(Product product) {
        if (product.getId() != null) {
            Optional<Product> optional = mProductRepository.findById(product.getId());
            if (optional.isPresent()) {
                throw new AlreadyExistEx(product.getId());
            }
        }
        Product product1 = mProductRepository.save(product);
        Inventory inventory = new Inventory(product1.getId(), 0, 0);
        mInventoryRepository.save(inventory);
        return product1;
    }

    @Override
    public List<Product> getProductList() {
        return mProductRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> byId = mProductRepository.findById(id);
        if (!byId.isPresent()) {
            throw new NotFoundEx(id);
        }
        product.setId(id);
        return mProductRepository.saveAndFlush(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optional = mProductRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NotFoundEx(id);
        }
    }

    @Override
    public Product getProductByName(String name) {
        Product product = mProductRepository.findByName(name);
        if (product != null) {
            return product;
        }
        throw  new NotFoundEx(name);
    }

    @Override
    public List<Product> getProductByNameAndDesc(String name, String description) {
        return mProductRepository.findByNameAndDescriptionLike(name, description);
    }
}
