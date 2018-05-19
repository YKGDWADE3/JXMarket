package com.example.JXMarket.service;

import com.example.JXMarket.entity.Inventory;

import java.util.List;

public interface IInventoryService {
    Inventory updateProductNumber(Long productId, int count);

    Inventory getInventoryByProductId(Long productId);

    List<Inventory> getInventoryList();

    Inventory updateProductLockNumber(Long productId, int lockNumber);

    Inventory saveInventory(Inventory inventory);
}
