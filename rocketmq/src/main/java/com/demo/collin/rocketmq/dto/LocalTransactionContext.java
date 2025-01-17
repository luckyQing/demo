package com.demo.collin.rocketmq.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LocalTransactionContext<T> {

    private T data;

}