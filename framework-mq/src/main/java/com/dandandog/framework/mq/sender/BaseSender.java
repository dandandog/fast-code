package com.dandandog.framework.mq.sender;

import com.dandandog.framework.mq.model.IQueueEnum;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class BaseSender {


    private final AmqpTemplate amqpTemplate;

    @Autowired
    public BaseSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public <T> void sendDelayMessage(IQueueEnum queue, T data, long delayTimes) {
        amqpTemplate.convertAndSend(queue.getExchange(), queue.getRouteKey(), data, message -> {
            message.getMessageProperties().setHeader("x-delay", delayTimes);
            return message;
        });
    }

    public <T> void sendDelayMessage(IQueueEnum queue, T data, LocalDateTime delayDataTime) {
        Duration delayTimes = Duration.between(LocalDateTime.now(), delayDataTime);
        this.sendDelayMessage(queue, data, delayTimes.toMillis());
    }

}
