package com.example.JXMarket.instance;

public class GlobalMessage {
    //order
    public static final String ORDER_ERROR_PAY_STATUS = "order cannot pay because status is ";
    public static final String ORDER_ERROR_WITHDRAW_STATUS = "order cannot withdraw because status is ";
    public static final String ORDER_ERROR_SIGN_STATUS = "order cannot sign because order status is ";
    public static final String ORDER_SUCCESS_CREATE = "create order success";
    public static final String ORDER_SUCCESS_PAY = "pay order success";
    public static final String ORDER_SUCCESS_WITHDRAW = "withdraw order success";
    public static final String ORDER_SUCCESS_SIGN = "sign order success";

    //orderItem

    public static final String ORDER_ITEM_SUCCESS_CREATE = "create orderItem success";

    //delivery
    public static final String DELIVERY_SUCCESS_CREATE = "create deliveryInfo success";
    public static final String DELIVERY_SUCCESS_SIGN = "sign deliveryInfo success";
    public static final String DELIVERY_SUCCESS_OUTBOUND = "outbound delivery success";

    //inventory
    public static final String INVENTORY_SUCCESS_UPDATE_WHEN_ORDER_WITHDRAW = "update inventory success when order withdraw";
    public static final String INVENTORY_SUCCESS_UPDATE_WHEN_ORDER_SIGNED = "update inventory success when order signed";

}
