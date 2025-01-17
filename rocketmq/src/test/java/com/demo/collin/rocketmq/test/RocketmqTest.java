package com.demo.collin.rocketmq.test;

import com.demo.collin.rocketmq.dto.*;
import com.demo.collin.rocketmq.enums.TransactionCode;
import com.demo.collin.rocketmq.manage.mq.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RocketmqTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 普通同步消息发送
     *
     * @throws InterruptedException
     */
    @Test
    void testSendSynchronously() throws InterruptedException {
        SynchronouslyDTO dto = new SynchronouslyDTO();
        dto.setId(10L);
        dto.setName("mobile101");

        rocketMQTemplate.syncSend(MqConstants.Topic.SYNCHRONOUSLY, dto);

        TimeUnit.MINUTES.sleep(10);
    }

    /**
     * 普通异步消息发送
     *
     * @throws InterruptedException
     */
    @Test
    void testSendAsynchronously() throws InterruptedException {
        AsynchronouslyDTO dto = new AsynchronouslyDTO();
        dto.setId(20L);
        dto.setName("这是一个异步消息");

        rocketMQTemplate.asyncSend(MqConstants.Topic.ASYNCHRONOUSLY, dto, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.warn("send async msg success|{}", sendResult);
            }

            @Override
            public void onException(Throwable e) {

            }
        });

        TimeUnit.MINUTES.sleep(10);
    }

    /**
     * 发送延迟消息
     *
     * @throws InterruptedException
     */
    @Test
    void testSendDelay() throws InterruptedException {
        DelayDTO dto = new DelayDTO();
        dto.setId(20L);
        dto.setName("这是个延迟消息");
        log.info("----------->send msg start");
        rocketMQTemplate.syncSendDelayTimeSeconds(MqConstants.Topic.DELAY, dto, 10L);
        log.info("----------->send msg end");

        TimeUnit.SECONDS.sleep(20);
    }

    /**
     * 批量发送消息
     *
     * @throws InterruptedException
     */
    @Test
    void testSendBatch() throws InterruptedException {
        BatchSendDTO dto1 = new BatchSendDTO();
        dto1.setId(20L);
        dto1.setName("这是个批量消息1");
        Message<BatchSendDTO> message1 = MessageBuilder.withPayload(dto1).build();

        BatchSendDTO dto2 = new BatchSendDTO();
        dto2.setId(20L);
        dto2.setName("这是个批量消息2");
        Message<BatchSendDTO> message2 = MessageBuilder.withPayload(dto2).build();

        log.info("----------->send msg start");
        rocketMQTemplate.syncSend(MqConstants.Topic.BATCH_SEND, Arrays.asList(message1, message2));
        log.info("----------->send msg end");

        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * 批量消费
     *
     * @throws InterruptedException
     */
    @Test
    void testBatchConsmer() throws InterruptedException {
        List<Message<BatchConsumerDTO>> messages = new ArrayList<>();
        for (long i = 0; i < 21; i++) {
            BatchConsumerDTO dto = new BatchConsumerDTO();
            dto.setId(i);
            dto.setName("这是消息" + i);
            messages.add(MessageBuilder.withPayload(dto).build());
        }

        log.info("----------->send msg (batch consume) start");
        rocketMQTemplate.syncSend(MqConstants.Topic.BATCH_CONSUMER, messages);
        log.info("----------->send msg (batch consume) end");

        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * 事务消息
     */
    @Test
    public void testTransaction() throws InterruptedException {
        BuyDTO dto = new BuyDTO();
        dto.setAmount(100L);
        dto.setBuyer(1L);
        dto.setSkuId(1L);
        dto.setSkuCount(100L);
        dto.setTransactionCode(TransactionCode.BUY);
        dto.setTransactionNo(UUID.randomUUID().toString().replaceAll("-", ""));

        Message<BuyDTO> message = MessageBuilder.withPayload(dto)
                .build();

        rocketMQTemplate.sendMessageInTransaction(MqConstants.Topic.TRANSACTION_CONSUMER, message, BuyDTO.class);
        TimeUnit.MINUTES.sleep(10);
    }

}