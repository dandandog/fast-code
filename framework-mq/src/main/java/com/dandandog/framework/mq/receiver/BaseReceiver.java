package com.dandandog.framework.mq.receiver;

import com.dandandog.framework.mq.model.IQueueEnum;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Johnny
 */

public abstract class BaseReceiver<T> {


    @RabbitHandler
    public void handle(T t) {

    }

}
