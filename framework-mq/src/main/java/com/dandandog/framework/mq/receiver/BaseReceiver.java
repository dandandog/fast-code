package com.dandandog.framework.mq.receiver;

import com.dandandog.framework.mq.model.IQueueEnum;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BaseReceiver<T> {

    private final String queue;

    public BaseReceiver(IQueueEnum queueEnum) {
        this.queue = queueEnum.getName();
    }


    @RabbitListener(queues = queue)
    @RabbitHandler
    public void handle(T t) {

    }

}
