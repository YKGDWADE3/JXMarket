package com.example.JXMarket.service;

import com.example.JXMarket.entity.DeliveryInfo;
import com.example.JXMarket.exception.NotFoundEx;
import com.example.JXMarket.repository.DeliveryInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeliveryInfoService implements IDeliveryInfoService {
    @Autowired
    DeliveryInfoRepository mDeliveryInfoRepository;

    @Override
    public String createDelivery(DeliveryInfo deliveryInfo) {
        mDeliveryInfoRepository.saveAndFlush(deliveryInfo);
        return "create deliveryInfo success";
    }

    @Override
    public String outboundDelivery(Long id) {
        return null;
    }

    @Override
    public String signedDelivery(Long id) {
        return null;
    }

    @Override
    public DeliveryInfo getDeliveryInfoById(Long id) {
        Optional<DeliveryInfo> optional = mDeliveryInfoRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NotFoundEx(id);
        }

    }
}
