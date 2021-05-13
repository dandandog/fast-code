package com.dandandog.framework.task.entity;

import lombok.Data;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/13 16:56
 */
@Data
public class SimpleJobEntity extends BaseJobEntity {

    /**
     * 重复次数
     */
    private int repeatCount;

    /**
     * 时间间隔
     */
    private int intervalInSeconds;

    /**
     * 是否一直执行
     */
    private boolean forever = true;

}
