package com.example.JXMarket.service;

import com.example.JXMarket.Enum.DeliveryStatusEnum;
import com.example.JXMarket.entity.DeliveryInfo;
import com.example.JXMarket.exception.NotFoundEx;
import com.example.JXMarket.instance.GlobalMessage;
import com.example.JXMarket.repository.DeliveryInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class DeliveryInfoService implements IDeliveryInfoService {
    @Autowired
    DeliveryInfoRepository mDeliveryInfoRepository;

    @Override
    public String createDelivery(DeliveryInfo deliveryInfo) {
        mDeliveryInfoRepository.saveAndFlush(deliveryInfo);
        return GlobalMessage.DELIVERY_SUCCESS_CREATE;
    }

    @Override
    public String outboundDelivery(Long id) {
        DeliveryInfo deliveryInfo = getDeliveryInfoById(id);
        deliveryInfo.setLogisticsStatus(DeliveryStatusEnum.OUTBOUND.getDeliveryStatus());
        deliveryInfo.setOutboundTime(new Date());
        mDeliveryInfoRepository.save(deliveryInfo);
        return GlobalMessage.DELIVERY_SUCCESS_OUTBOUND;
    }

    @Override
    public String signedDelivery(DeliveryInfo deliveryInfo) {
        deliveryInfo.setLogisticsStatus(DeliveryStatusEnum.SIGNED.getDeliveryStatus());
        deliveryInfo.setSignedTime(new Date());
        mDeliveryInfoRepository.save(deliveryInfo);
        return GlobalMessage.DELIVERY_SUCCESS_SIGN;
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
