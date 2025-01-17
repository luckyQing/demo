package com.demo.collin.rocketmq.manage.mq;

import com.demo.collin.rocketmq.biz.MqTransactionLogBiz;
import com.demo.collin.rocketmq.dto.BaseTransactionMsgDTO;
import com.demo.collin.rocketmq.service.impl.LocalTransactionStrategyFactory;
import com.demo.collin.rocketmq.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQTransactionListener
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    @Autowired
    private LocalTransactionStrategyFactory localTransactionStrategyFactory;
    @Autowired
    private MqTransactionLogBiz mqTransactionLogBiz;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // 执行本地事务
        try {
            byte[] payload = (byte[]) msg.getPayload();
            BaseTransactionMsgDTO msgDTO = (BaseTransactionMsgDTO)JacksonUtil.parseObject(new String(payload), (Class)arg);
            log.info("executeLocalTransaction.msgDTO={}", JacksonUtil.toJson(msgDTO));
            if (localTransactionStrategyFactory.execute(msgDTO)) {
                return RocketMQLocalTransactionState.COMMIT;
            }
            return RocketMQLocalTransactionState.ROLLBACK;
        } catch (Exception e) {
            log.error("executeLocalTransaction.error|{}", JacksonUtil.toJson(msg), e);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // 检查本地事务状态
        BaseTransactionMsgDTO msgDTO = (BaseTransactionMsgDTO) msg.getPayload();
        log.info("checkLocalTransaction.msgDTO={}", JacksonUtil.toJson(msgDTO));
        if (mqTransactionLogBiz.exist(msgDTO.getTransactionNo(), msgDTO.getTransactionCode())) {
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }

}