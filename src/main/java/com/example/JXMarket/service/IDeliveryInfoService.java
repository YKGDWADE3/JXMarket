package com.example.JXMarket.service;

import com.example.JXMarket.entity.DeliveryInfo;

public interface IDeliveryInfoService {

    String createDelivery(DeliveryInfo deliveryInfo);

    String outboundDelivery(Long id);

    String signedDelivery(Long id);

    DeliveryInfo getDeliveryInfoById(Long id);
}
