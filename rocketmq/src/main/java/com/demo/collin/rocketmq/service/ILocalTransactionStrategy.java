package com.demo.collin.rocketmq.service;

import com.demo.collin.rocketmq.dto.BaseTransactionMsgDTO;
import com.demo.collin.rocketmq.enums.TransactionCode;

public interface ILocalTransactionStrategy<T extends BaseTransactionMsgDTO> {

    TransactionCode getTransactionCode();

    boolean execute(T context);

}