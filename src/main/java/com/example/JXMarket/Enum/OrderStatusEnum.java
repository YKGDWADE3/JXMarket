package com.example.JXMarket.Enum;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatusEnum{

    UNPAID("unpaid", 1),
    PAID("paid", 2),
    WITHDRAW("withdraw", 3),
    FINISH("finish", 4),
    UNKNOWN("未知", 5),
    ;
    private String orderStatus;

    private Integer statusId;

    OrderStatusEnum(String orderStatus, Integer statusId) {
        this.orderStatus = orderStatus;
        this.statusId = statusId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public static OrderStatusEnum resolve(String orderStatus) {
        if (INSTANCE.containsKey(orderStatus)) {
            return INSTANCE.get(orderStatus);
        }
        return OrderStatusEnum.UNKNOWN;
    }

    private static final Map<String, OrderStatusEnum> INSTANCE = new HashMap<>();

    static {
        OrderStatusEnum[] values = OrderStatusEnum.values();
        for (OrderStatusEnum value : values) {
            if (value == UNKNOWN) {
                continue;
            }
            INSTANCE.put(value.getOrderStatus(), value);
        }
    }

    public static int getCourseEnumNumber() {
        return INSTANCE.size();
    }


}
