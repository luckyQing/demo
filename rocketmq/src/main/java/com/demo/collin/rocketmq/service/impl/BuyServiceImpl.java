package com.demo.collin.rocketmq.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.demo.collin.rocketmq.biz.MqTransactionLogBiz;
import com.demo.collin.rocketmq.biz.OrderBillBiz;
import com.demo.collin.rocketmq.dto.BuyDTO;
import com.demo.collin.rocketmq.entity.OrderBillEntity;
import com.demo.collin.rocketmq.enums.TransactionCode;
import com.demo.collin.rocketmq.service.ILocalTransactionStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BuyServiceImpl implements ILocalTransactionStrategy<BuyDTO> {

    private final OrderBillBiz orderBillBiz;
    private final MqTransactionLogBiz mqTransactionLogBiz;

    @Override
    public TransactionCode getTransactionCode() {
        return TransactionCode.BUY;
    }

    @Override
    @Transactional
    public boolean execute(BuyDTO buyDTO) {
        OrderBillEntity entity = new OrderBillEntity();
        entity.setOrderNo("O" + System.currentTimeMillis());
        entity.setAmount(buyDTO.getAmount());
        entity.setStatus((byte) 1);
        entity.setPayState((byte) 1);
        entity.setBuyer(buyDTO.getBuyer());

        entity.setId(IdWorker.getId());
        entity.setSysInsertTime(LocalDateTime.now());
        entity.setSysDelUser(0L);
        entity.setSysDelState((byte) 0);
        orderBillBiz.save(entity);
        mqTransactionLogBiz.save(buyDTO.getTransactionNo(), buyDTO.getTransactionCode().getCode(), "购买商品");
        return true;
    }

}