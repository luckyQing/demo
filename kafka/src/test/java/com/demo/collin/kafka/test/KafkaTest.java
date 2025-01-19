package com.demo.collin.kafka.test;

import com.demo.collin.kafka.constants.KafkaConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class KafkaTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void testSendMsg() throws InterruptedException, ExecutionException {
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(KafkaConstants.Topic.TEST_SEND_TOPIC, "hello, kafka");
        SendResult<String, String> sendResult = listenableFuture.get();

        log.info("sendResult={}",sendResult.toString());
        TimeUnit.MINUTES.sleep(10);
    }

}