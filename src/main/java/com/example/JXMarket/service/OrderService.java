package com.example.JXMarket.service;

import com.example.JXMarket.Enum.DeliveryStatusEnum;
import com.example.JXMarket.Enum.OrderStatusEnum;
import com.example.JXMarket.entity.DeliveryInfo;
import com.example.JXMarket.entity.Inventory;
import com.example.JXMarket.entity.Order;
import com.example.JXMarket.entity.OrderItem;
import com.example.JXMarket.exception.NotEnoughEx;
import com.example.JXMarket.exception.NotFoundEx;
import com.example.JXMarket.instance.GlobalMessage;
import com.example.JXMarket.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class OrderService implements IOrderService{
    @Autowired
    OrderRepository mOrderRepository;

    @Autowired
    IInventoryService mIInventoryService;

    @Autowired
    IOrderItemService mOrderItemService;

    @Autowired
    IDeliveryInfoService mIDeliveryInfoService;

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
        return GlobalMessage.ORDER_SUCCESS_CREATE;
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
        Optional<Order> optional = mOrderRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundEx(id);
        }
        return optional.get();
    }

    @Override
    public String payOrder(Long id) {
        Order order = getOrderById(id);
        if (OrderStatusEnum.UNPAID.getOrderStatus().equals(order.getOrderStatus())) {
            order.setOrderStatus(OrderStatusEnum.PAID.getOrderStatus());
            order.setPaidTime(new Date());
            mOrderRepository.save(order);
            //新增运送记录
            DeliveryInfo deliveryInfo = new DeliveryInfo();
            deliveryInfo.setCreateTime(new Date());
            deliveryInfo.setOrderId(order.getId());
            deliveryInfo.setDeliveryStatus(DeliveryStatusEnum.CREATE.getDeliveryStatus());
            return GlobalMessage.ORDER_SUCCESS_PAY + ", " + mIDeliveryInfoService.createDelivery(deliveryInfo);
        }
        return GlobalMessage.ORDER_ERROR_PAY_STATUS + order.getOrderStatus();

    }

    @Override
    public String withdrawOrder(Long id) {
        Order order = getOrderById(id);
        if (OrderStatusEnum.UNPAID.getOrderStatus().equals(order.getOrderStatus())) {
            order.setOrderStatus(OrderStatusEnum.WITHDRAW.getOrderStatus());
            order.setWithdrawTime(new Date());
            mOrderRepository.save(order);
            return GlobalMessage.ORDER_SUCCESS_WITHDRAW + " , " + mIInventoryService.updateInventories(order.getPurchaseItemList(), false);
        }
        return GlobalMessage.ORDER_ERROR_WITHDRAW_STATUS + order.getOrderStatus();
    }

    @Override
    public String signOrder(Long id) {
        Order order = getOrderById(id);
        if (OrderStatusEnum.PAID.getOrderStatus().equals(order.getOrderStatus())) {
            order.setOrderStatus(OrderStatusEnum.SIGNED.getOrderStatus());
            order.setSignTime(new Date());
            mOrderRepository.save(order);
            mIDeliveryInfoService.signedDelivery(order.getDeliveryInfo());
            return GlobalMessage.ORDER_SUCCESS_SIGN + " , "
                    + mIDeliveryInfoService.signedDelivery(order.getDeliveryInfo()) + " , "
                    + mIInventoryService.updateInventories(order.getPurchaseItemList(), true);


        }
        return GlobalMessage.ORDER_ERROR_SIGN_STATUS + order.getOrderStatus();
    }
}
