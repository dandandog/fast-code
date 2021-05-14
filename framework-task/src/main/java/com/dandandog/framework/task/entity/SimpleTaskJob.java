package com.dandandog.framework.task.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/13 16:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SimpleTaskJob extends TaskJob {

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
