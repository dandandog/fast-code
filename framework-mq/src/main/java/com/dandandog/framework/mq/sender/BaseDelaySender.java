package com.dandandog.framework.mq.sender;

import com.dandandog.framework.mq.model.ImqBinding;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author Johnny
 */
@Component
public abstract class BaseDelaySender<T extends ImqBinding> {


    private final AmqpTemplate amqpTemplate;

    private final T binding;

    @Autowired
    public BaseDelaySender(AmqpTemplate amqpTemplate, T binding) {
        this.amqpTemplate = amqpTemplate;
        this.binding = binding;
    }

    public void sendDelayMessage(Object data, long delayTimes) {
        amqpTemplate.convertAndSend(binding.getExchange(), binding.getRouteKey(), data, message -> {
            message.getMessageProperties().setHeader("x-delay", delayTimes);
            return message;
        });
    }

    public void sendDelayMessage(Object data, LocalDateTime delayDataTime) {
        Duration delayTimes = Duration.between(LocalDateTime.now(), delayDataTime);
        this.sendDelayMessage(data, delayTimes.toMillis());
    }
}
