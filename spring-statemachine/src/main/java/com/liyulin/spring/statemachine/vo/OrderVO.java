package com.liyulin.spring.statemachine.vo;

import com.liyulin.spring.statemachine.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderVO {
	
	private int id;
	private String orderNo;
	private OrderStatus status;
	
}