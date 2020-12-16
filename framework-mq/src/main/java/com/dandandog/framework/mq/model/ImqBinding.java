package com.dandandog.framework.mq.model;

import lombok.Data;

/**
 * @author Johnny
 */

public interface ImqBinding {


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
    String getQueue();

    /**
     * 获取路由键
     *
     * @return 路由键
     */
    String getRouteKey();

}
