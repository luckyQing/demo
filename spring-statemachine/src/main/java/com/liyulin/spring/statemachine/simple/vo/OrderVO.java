package com.liyulin.spring.statemachine.simple.vo;

import com.liyulin.spring.statemachine.simple.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderVO {
	
	private int id;
	private String orderNo;
	private OrderStatus status;
	
}