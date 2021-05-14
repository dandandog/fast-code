package com.dandandog.framework.task.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/13 15:51
 */
@Data
public class TaskJob implements Serializable {

    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    /**
     * 任务id
     */
    private String id;

    /**
     * spring bean名称
     */
    private String beanName;

    /**
     * 参数
     */
    private String params;

    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 开始时间
     */
    private Date startAt;

    /**
     * 结束时间
     */
    private Date endAt;

    /**
     * 备注
     */
    private String remark;

}
