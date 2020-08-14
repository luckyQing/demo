package com.liyulin.spring.statemachine.factory.vo;

import com.liyulin.spring.statemachine.factory.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderVO {

    private Integer id;
    private String orderNo;
    private OrderStatus status;

}