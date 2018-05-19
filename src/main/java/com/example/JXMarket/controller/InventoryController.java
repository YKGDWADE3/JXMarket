package com.example.JXMarket.controller;

import com.example.JXMarket.entity.Inventory;
import com.example.JXMarket.exception.NotFoundEx;
import com.example.JXMarket.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/inventories")
public class InventoryController {
    @Autowired
    InventoryRepository mInventoryRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Inventory> getInventoryList() {
        return mInventoryRepository.findAll();
    }

    @GetMapping(value = "{productId}")
    @ResponseStatus(HttpStatus.OK)
    Inventory getInventoryByProductId(@PathVariable Long productId) {
        return mInventoryRepository.findByProductId(productId);
    }

    @PutMapping(value = "{productId}/{count}")
    @ResponseStatus(HttpStatus.OK)
    Inventory updateProductNumber(@PathVariable Long productId, @PathVariable int count) {
        Inventory inventory1 = mInventoryRepository.findByProductId(productId);
        if (inventory1 != null) {
            inventory1.setProductNumber(count);
            return mInventoryRepository.save(inventory1);
        } else {
            throw new NotFoundEx(productId);
        }
    }
}
