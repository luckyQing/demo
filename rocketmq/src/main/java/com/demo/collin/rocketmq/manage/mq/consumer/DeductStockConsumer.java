package com.demo.collin.rocketmq.manage.mq.consumer;

import com.demo.collin.rocketmq.biz.ProductInfoBiz;
import com.demo.collin.rocketmq.dto.BuyDTO;
import com.demo.collin.rocketmq.manage.mq.MqConstants;
import com.demo.collin.rocketmq.util.JacksonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(consumerGroup = MqConstants.Group.TRANSACTION_CONSUMER, topic = MqConstants.Topic.TRANSACTION_CONSUMER)
public class DeductStockConsumer implements RocketMQListener<BuyDTO> {

    private final ProductInfoBiz productInfoBiz;

    @Override
    public void onMessage(BuyDTO message) {
        productInfoBiz.updateStock(message.getSkuId(), message.getSkuCount());
        log.info("consumer transaction msg success msg={}", JacksonUtil.toJson(message));
    }

}