package com.example.JXMarket.controller;

import com.example.JXMarket.entity.Inventory;
import com.example.JXMarket.exception.NotFoundEx;
import com.example.JXMarket.repository.InventoryRepository;
import com.example.JXMarket.service.IInventoryService;
import com.fasterxml.jackson.databind.jsontype.impl.MinimalClassNameIdResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/inventories")
public class InventoryController {
    @Autowired
    IInventoryService mIInventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Inventory> getInventoryList() {
        return mIInventoryService.getInventoryList();
    }

    @GetMapping(value = "{productId}")
    @ResponseStatus(HttpStatus.OK)
    Inventory getInventoryByProductId(@PathVariable Long productId) {
        return mIInventoryService.getInventoryByProductId(productId);
    }

    @PutMapping(value = "{productId}/{count}")
    @ResponseStatus(HttpStatus.OK)
    Inventory updateProductNumber(@PathVariable Long productId, @PathVariable int count) {
        return mIInventoryService.updateProductNumber(productId, count);
    }
}
