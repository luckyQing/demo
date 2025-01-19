package com.demo.collin.kafka.mq;

import com.demo.collin.kafka.constants.KafkaConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @KafkaListener(topics = KafkaConstants.Topic.TEST_SEND_TOPIC, groupId = KafkaConstants.Group.TEST_SEND_GROUP)
    public void consumerSendMsg(String msg) {
        int x=1/0;
        log.info("============>Consumer received message: {}", msg);
    }

}