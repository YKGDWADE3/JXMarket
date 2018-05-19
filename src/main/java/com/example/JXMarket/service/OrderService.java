package com.example.JXMarket.service;

import com.example.JXMarket.Enum.OrderStatusEnum;
import com.example.JXMarket.entity.Inventory;
import com.example.JXMarket.entity.Order;
import com.example.JXMarket.entity.OrderItem;
import com.example.JXMarket.entity.Product;
import com.example.JXMarket.exception.NotEnoughEx;
import com.example.JXMarket.exception.NotFoundEx;
import com.example.JXMarket.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class OrderService implements IOrderService{
    @Autowired
    OrderRepository mOrderRepository;

    @Autowired
    IInventoryService mIInventoryService;

    @Autowired
    OrderItemService mOrderItemService;

    @Override
    public String createOrder(List<OrderItem> orderItems) {
        double totalPrice = checkInventoryAndUpdate(orderItems);
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setTotalPrice(totalPrice);
        order.setUserId((long) 1);
        order.setOrderStatus(OrderStatusEnum.UNPAID.getOrderStatus());
        Order order1 = mOrderRepository.saveAndFlush(order);
        mOrderItemService.createOrderItem(orderItems, order1.getId());
        return "create order success";
    }

    public double checkInventoryAndUpdate(List<OrderItem> orderItems) {
        double totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            Inventory inventory = mIInventoryService.getInventoryByProductId(orderItem.getProductId());
            if (inventory != null && inventory.getProduct() != null) {
                int newLock = inventory.getProductLockNumber() + orderItem.getPurchaseCount();
                if (inventory.getProductNumber() - newLock >= 0) {
                    //库存够
                    inventory.setProductLockNumber(newLock);
                    mIInventoryService.saveInventory(inventory);
                    totalPrice += inventory.getProduct().getPrice() * orderItem.getPurchaseCount();
                } else {
                    throw new NotEnoughEx(orderItem.getProductId());
                }
            } else {
                throw new NotFoundEx(orderItem.getProductId());
            }
        }
        return totalPrice;


    }
    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public String payOrder(Long id) {
        return null;
    }

    @Override
    public String withdrawOrder(Long id) {
        return null;
    }
}
