package com.example.JXMarket.service;

import com.example.JXMarket.entity.Inventory;
import com.example.JXMarket.entity.OrderItem;
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

    @Override
    public Inventory updateProductLockNumber(Long productId, int lockNumber) {
        Inventory inventory1 = mInventoryRepository.findByProductId(productId);
        if (inventory1 != null) {
            inventory1.setProductLockNumber(lockNumber);
            return mInventoryRepository.save(inventory1);
        } else {
            throw new NotFoundEx(productId);
        }
    }

    @Override
    public Inventory saveInventory(Inventory inventory) {
        return mInventoryRepository.saveAndFlush(inventory);
    }

    @Override
    public String updateInventories(List<OrderItem> orderItems) {
        for (OrderItem orderItem: orderItems) {
            Inventory inventory = getInventoryByProductId(orderItem.getProductId());
            inventory.setProductLockNumber(inventory.getProductLockNumber() - orderItem.getPurchaseCount());
            inventory.setProductNumber(inventory.getProductNumber() - orderItem.getPurchaseCount());
            saveInventory(inventory);
        }
        return "update inventories success";
    }
}
