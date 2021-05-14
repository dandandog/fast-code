package com.dandandog.framework.task.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/13 17:56
 */
@Data
public class TaskJobLog implements Serializable {

    /**
     * 任务id
     */
    private String jobId;

    /**
     * spring bean名称
     */
    private String beanName;

    /**
     * 参数
     */
    private String params;

    /**
     * 任务状态    0：成功    1：失败
     */
    private Integer status;

    /**
     * 正常信息
     */
    private String message;

    /**
     * 失败信息
     */
    private String error;

    /**
     * 耗时(单位：毫秒)
     */
    private Integer times;

}
