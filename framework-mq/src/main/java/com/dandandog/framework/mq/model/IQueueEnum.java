package com.dandandog.framework.mq.model;

/**
 * @author Johnny
 */
public interface IQueueEnum {

    /**
     * 获取交换机名称
     *
     * @return 交换机名称
     */
    String getExchange();

    /**
     * 获取队列名称
     *
     * @return 队列名称
     */
    String getName();

    /**
     * 获取路由键
     *
     * @return 路由键
     */
    String getRouteKey();
}
