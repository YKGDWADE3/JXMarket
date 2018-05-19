package com.example.JXMarket.controller;

import com.example.JXMarket.entity.Order;
import com.example.JXMarket.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    OrderRepository mOrderRepository;

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    Order createOrder(@RequestBody Order input) {
//
//    }

}
