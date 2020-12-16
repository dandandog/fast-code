package com.dandandog.framework.mq.receiver;

/**
 * @author Johnny
 */

public interface BaseReceiver<T> {


    void handle(T t);

}
