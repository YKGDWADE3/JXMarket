package com.example.JXMarket.controller;

import com.example.JXMarket.entity.Order;
import com.example.JXMarket.entity.OrderItem;
import com.example.JXMarket.repository.OrderRepository;
import com.example.JXMarket.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    IOrderService mIOrderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    String createOrder(@RequestBody List<OrderItem> orderItems) {
        return mIOrderService.createOrder(orderItems);
    }

    @PutMapping(value = "{id}/paid")
    @ResponseStatus(HttpStatus.OK)
    String payOrder(@PathVariable Long id) {
        return mIOrderService.payOrder(id);
    }

    @PutMapping(value = "{id}/withdraw")
    @ResponseStatus(HttpStatus.OK)
    String withdrawOrder(Long id) {
        return mIOrderService.withdrawOrder(id);
    }

    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    Order getOrderById(@PathVariable Long id) {
        return mIOrderService.getOrderById(id);
    }
}
