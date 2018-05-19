package com.example.JXMarket.controller;

import com.example.JXMarket.entity.Product;
import com.example.JXMarket.entity.Inventory;
import com.example.JXMarket.exception.AlreadyExistEx;
import com.example.JXMarket.exception.NotFoundEx;
import com.example.JXMarket.repository.ProductRepository;
import com.example.JXMarket.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    ProductRepository mProductRepository;

    @Autowired
    InventoryRepository mInventoryRepository;

    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    Product getProductById(@PathVariable long id) {
        Optional<Product> optional = mProductRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NotFoundEx(id);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Product> getProductList() {
        return mProductRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Product createProduct(@RequestBody Product input) {
        if (input.getId() != null) {
            Optional<Product> optional = mProductRepository.findById(input.getId());
            if (optional.isPresent()) {
                throw new AlreadyExistEx(input.getId());
            }
        }
        Product product = mProductRepository.save(input);
        Inventory inventory = new Inventory(product.getId(), 0);
        mInventoryRepository.save(inventory);
        return product;
    }

    @PutMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    Product putProduct(@PathVariable long id, @RequestBody Product product ) {
        Optional<Product> byId = mProductRepository.findById(id);
        if (!byId.isPresent()) {

            throw new NotFoundEx(id);
        }
        product.setId(id);
        return mProductRepository.saveAndFlush(product);
    }

    @GetMapping(value = "name/{name}")
    @ResponseStatus(HttpStatus.OK)
    Product getProductByName(@PathVariable String name) {
        Product product = mProductRepository.findByName(name);
        if (product != null) {
            return product;
        }
        throw  new NotFoundEx(name);
    }

    @GetMapping("name/{name}/description/{description}")
    @ResponseStatus(HttpStatus.OK)
    List<Product> getProductsByNameAndDesc(@PathVariable String name, @PathVariable String description) {
        return mProductRepository.findByNameAndDescriptionLike(name, description);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    String deleteProduct(@PathVariable Long id) {
        Optional<Product> optional = mProductRepository.findById(id);
        if (optional.isPresent()) {
            mProductRepository.deleteById(id);
            return "delete success";
        } else {
            throw new NotFoundEx(id);
        }
    }

}
