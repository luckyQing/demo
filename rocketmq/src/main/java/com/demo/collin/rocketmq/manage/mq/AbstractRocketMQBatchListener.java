package com.demo.collin.rocketmq.manage.mq;

import com.demo.collin.rocketmq.annotation.BatchConsumerConfig;
import com.demo.collin.rocketmq.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 批量消费消息监听器
 *
 * @param <T>
 * @author collin
 * @date 2025-01-02
 */
@Slf4j
public abstract class AbstractRocketMQBatchListener<T> implements RocketMQListener<List<T>>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        BatchConsumerConfig batchConsumerConfig = AnnotationUtils.findAnnotation(getClass(), BatchConsumerConfig.class);
        int consumeMessageBatchMaxSize = batchConsumerConfig == null ? BatchConsumerConfig.DEFAULT_CONSUME_MESSAGE_BATCH_MAX_SIZE : batchConsumerConfig.consumeMessageBatchMaxSize();
        int pullBatchSize = batchConsumerConfig == null ? BatchConsumerConfig.DEFAULT_PULL_BATCH_SIZE : batchConsumerConfig.pullBatchSize();
        long pullInterval = batchConsumerConfig == null ? BatchConsumerConfig.DEFAULT_PULL_INTERVAL : batchConsumerConfig.pullInterval();
        int consumeThreadMin = batchConsumerConfig == null ? BatchConsumerConfig.DEFAULT_CONSUME_THREAD_MIN : batchConsumerConfig.consumeThreadMin();
        int consumeThreadMax = batchConsumerConfig == null ? BatchConsumerConfig.DEFAULT_CONSUME_THREAD_MAX : batchConsumerConfig.consumeThreadMax();
        int delayLevelWhenNextConsume = batchConsumerConfig == null ? BatchConsumerConfig.DEFAULT_DELAY_LEVEL_WHEN_NEXT_CONSUME : batchConsumerConfig.delayLevelWhenNextConsume();

        //设置消费者单次批量消费的消息数目上限
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        //设置每个队列每次拉取的最大消费数
        consumer.setPullBatchSize(pullBatchSize);
        //设置每次消息拉取的时间间隔（单位：毫秒）
        consumer.setPullInterval(pullInterval);
        //最小消费线程池数
        consumer.setConsumeThreadMin(consumeThreadMin);
        //最大消费线程池数
        consumer.setConsumeThreadMax(consumeThreadMax);
        Class<T> msgClass = getTypeClass();
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            try {
                List<T> msgList = msgs.stream()
                        .map(ext -> JacksonUtil.parseObject(ext.getBody(), msgClass))
                        .collect(Collectors.toList());
                onMessage(msgList);
            } catch (Exception e) {
                log.warn("consume message failed. msgs:{}", JacksonUtil.toJson(msgs), e);
                context.setDelayLevelWhenNextConsume(delayLevelWhenNextConsume);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
    }

    private Class<T> getTypeClass() {
        Type superClass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superClass;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        return (Class<T>) typeArguments[0];
    }

}