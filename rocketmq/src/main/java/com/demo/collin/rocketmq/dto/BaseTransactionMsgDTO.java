package com.demo.collin.rocketmq.dto;

import com.demo.collin.rocketmq.enums.TransactionCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseTransactionMsgDTO {

    private TransactionCode transactionCode;
    /**
     * 事务号
     */
    private String transactionNo;

}