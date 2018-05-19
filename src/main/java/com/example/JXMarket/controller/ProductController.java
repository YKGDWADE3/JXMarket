package com.example.JXMarket.controller;

import com.example.JXMarket.entity.Product;
import com.example.JXMarket.entity.Inventory;
import com.example.JXMarket.exception.AlreadyExistEx;
import com.example.JXMarket.exception.NotFoundEx;
import com.example.JXMarket.repository.ProductRepository;
import com.example.JXMarket.repository.InventoryRepository;
import com.example.JXMarket.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    IProductService mProductService;

    @Autowired
    InventoryRepository mInventoryRepository;

    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    Product getProductById(@PathVariable long id) {
        return mProductService.getProductById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Product> getProductList() {
        return mProductService.getProductList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Product createProduct(@RequestBody Product input) {
        return mProductService.createProduct(input);
    }

    @PutMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    Product putProduct(@PathVariable long id, @RequestBody Product product ) {
        return mProductService.updateProduct(id,product);
    }

    @GetMapping(value = "name/{name}")
    @ResponseStatus(HttpStatus.OK)
    Product getProductByName(@PathVariable String name) {
        return mProductService.getProductByName(name);
    }

    @GetMapping("name/{name}/description/{description}")
    @ResponseStatus(HttpStatus.OK)
    List<Product> getProductsByNameAndDesc(@PathVariable String name, @PathVariable String description) {
        return mProductService.getProductByNameAndDesc(name, description);
    }

}
