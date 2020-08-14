package com.liyulin.spring.statemachine.simple.enums;

/**
 * 订单状态改变事件
 */
public enum OrderStatusChangeEvents {

    /**
     * 支付
     */
    SIMPLE_PAYED,
    /**
     * 发货
     */
    SIMPLE_DELIVERY,
    /**
     * 确认收货
     */
    SIMPLE_RECEIVED,
    /**
     * 完成
     */
    SIMPLE_FINISH,
    /**
     * 重试
     */
    RETRY_PAYED,
    /**
     * 重试
     */
    RETRY_DELIVERY,
    /**
     * 重试
     */
    RETRY_RECEIVED;

}