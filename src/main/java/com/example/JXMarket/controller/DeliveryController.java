package com.example.JXMarket.controller;

import com.example.JXMarket.entity.DeliveryInfo;
import com.example.JXMarket.service.IDeliveryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/logisticsRecords")
public class DeliveryController {
    @Autowired
    IDeliveryInfoService mIDeliveryInfoService;

    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    DeliveryInfo getDeliveryInfoById(@PathVariable Long id) {
        return mIDeliveryInfoService.getDeliveryInfoById(id);
    }

    @PutMapping(value = "{id}/outbound")
    @ResponseStatus(HttpStatus.OK)
    String outBoundDelivery(@PathVariable Long id) {
        return mIDeliveryInfoService.outboundDelivery(id);
    }
}
