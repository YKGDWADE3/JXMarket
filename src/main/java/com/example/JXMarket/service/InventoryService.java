package com.example.JXMarket.service;

import com.example.JXMarket.entity.Inventory;
import com.example.JXMarket.exception.NotFoundEx;
import com.example.JXMarket.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryService implements IInventoryService {

    @Autowired
    InventoryRepository mInventoryRepository;

    @Override
    public Inventory updateProductNumber(Long productId, int count) {
        Inventory inventory1 = mInventoryRepository.findByProductId(productId);
        if (inventory1 != null) {
            inventory1.setProductNumber(count);
            return mInventoryRepository.save(inventory1);
        } else {
            throw new NotFoundEx(productId);
        }
    }

    @Override
    public Inventory getInventoryByProductId(Long productId) {
        Inventory inventory= mInventoryRepository.findByProductId(productId);
        if (inventory != null) {
            return inventory;
        } else {
            throw new NotFoundEx(productId);
        }
    }

    @Override
    public List<Inventory> getInventoryList() {
        return mInventoryRepository.findAll();
    }
}
