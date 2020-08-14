package com.liyulin.spring.statemachine.factory.enums;

/**
 * 订单状态改变事件
 */
public enum OrderStatusChangeEvents {

    /**
     * 支付
     */
    PAYED,
    /**
     * 发货
     */
    DELIVERY,
    /**
     * 确认收货
     */
    RECEIVED,
    /**
     * 完成
     */
    FINISH,
    /**
     * 重试支付
     */
    RETRY_PAYED,
    /**
     * 重试发货
     */
    RETRY_DELIVERY,
    /**
     * 重试确认收货
     */
    RETRY_RECEIVED;

}