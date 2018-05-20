package com.example.JXMarket.service;

import com.example.JXMarket.entity.Order;
import com.example.JXMarket.entity.OrderItem;

import java.util.List;

public interface IOrderService {
    String createOrder(List<OrderItem> orderItems);

    Order getOrderById(Long id);

    String payOrder(Long id);

    String withdrawOrder(Long id);

    String signOrder(Long id);
}
