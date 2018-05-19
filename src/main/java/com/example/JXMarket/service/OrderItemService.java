package com.example.JXMarket.service;

import com.example.JXMarket.entity.OrderItem;
import com.example.JXMarket.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemService implements IOrderItemService{
    @Autowired
    OrderItemRepository mOrderItemRepository;


    @Override
    public String createOrderItem(List<OrderItem> orderItems, Long orderId) {
        for (OrderItem orderItem: orderItems) {
            orderItem.setOrderId(orderId);
            mOrderItemRepository.save(orderItem);
        }
        return "create orderItem success";
    }
}
