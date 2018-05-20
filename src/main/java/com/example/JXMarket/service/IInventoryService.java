package com.example.JXMarket.service;

import com.example.JXMarket.entity.Inventory;
import com.example.JXMarket.entity.OrderItem;

import java.util.List;

public interface IInventoryService {
    Inventory updateProductNumber(Long productId, int count);

    Inventory getInventoryByProductId(Long productId);

    List<Inventory> getInventoryList();

    Inventory saveInventory(Inventory inventory);

    String updateInventories(List<OrderItem> orderItems, boolean isSigned);
}
