package com.example.JXMarket.service;

import com.example.JXMarket.entity.OrderItem;

import java.util.List;

public interface IOrderItemService {
    String createOrderItem(List<OrderItem> orderItems, Long OrderId);
}
