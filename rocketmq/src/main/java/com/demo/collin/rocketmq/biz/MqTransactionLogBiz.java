package com.demo.collin.rocketmq.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.collin.rocketmq.dao.MqTransactionLogDao;
import com.demo.collin.rocketmq.entity.MqTransactionLogEntity;
import com.demo.collin.rocketmq.enums.TransactionCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author collin
 * @since 2025-01-15
 */
@Service
public class MqTransactionLogBiz extends ServiceImpl<MqTransactionLogDao, MqTransactionLogEntity> {

    public boolean save(String transactionNo, String code, String log) {
        MqTransactionLogEntity entity = new MqTransactionLogEntity();
        entity.setId(IdWorker.getId());
        entity.setSysInsertTime(LocalDateTime.now());
        entity.setSysDelUser(0L);
        entity.setSysDelState((byte) 0);

        entity.setTransactionNo(transactionNo);
        entity.setCode(code);
        entity.setLog(log);

        return save(entity);
    }

    public boolean exist(String transactionNo, TransactionCode transactionCode) {
        LambdaQueryWrapper<MqTransactionLogEntity> queryCondition = new LambdaQueryWrapper<>();
        queryCondition.select(MqTransactionLogEntity::getId)
                .eq(MqTransactionLogEntity::getTransactionNo, transactionNo)
                .eq(MqTransactionLogEntity::getCode, transactionCode.getCode())
                .last("limit 1");
        MqTransactionLogEntity entity = getOne(queryCondition);
        return entity != null && entity.getId() != null;
    }

}
