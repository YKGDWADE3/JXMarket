package com.example.JXMarket.Enum;

import java.util.HashMap;
import java.util.Map;

public enum DeliveryStatusEnum {

    CREATE("create", 1),
    OUTBOUND("outbound", 2),
    SIGNED("signed", 3),
    UNKNOWN("未知", 4),
    ;
    private String deliveryStatus;

    private Integer statusId;

    DeliveryStatusEnum(String deliveryStatus, Integer statusId) {
        this.deliveryStatus = deliveryStatus;
        this.statusId = statusId;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public static DeliveryStatusEnum resolve(String deliveryStatus) {
        if (INSTANCE.containsKey(deliveryStatus)) {
            return INSTANCE.get(deliveryStatus);
        }
        return DeliveryStatusEnum.UNKNOWN;
    }

    private static final Map<String, DeliveryStatusEnum> INSTANCE = new HashMap<>();

    static {
        DeliveryStatusEnum[] values = DeliveryStatusEnum.values();
        for (DeliveryStatusEnum value : values) {
            if (value == UNKNOWN) {
                continue;
            }
            INSTANCE.put(value.getDeliveryStatus(), value);
        }
    }

}
