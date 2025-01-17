package com.demo.collin.rocketmq.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TransactionCode {

    BUY("buy");

    private String code;

}