package com.dandandog.framework.task.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dandandog.framework.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/13 15:51
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("task_job")
public class TaskJob extends BaseEntity {


    public enum JobType {
        SIMPLE, CRON
    }

    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    /**
     * 工作key
     */
    private String jobKey;

    /**
     * 工作组
     */
    private String jobGroup;

    /**
     * 执行器key
     */
    private String triggerKey;

    /**
     * 执行器组
     */
    private String triggerGroup;

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
    private int status = 0;

    /**
     * 开始时间
     */
    private LocalDateTime startAt;

    /**
     * 结束时间
     */
    private LocalDateTime endAt;

    /**
     * 备注
     */
    private String remark;

    /**
     * 类型
     */
    private JobType type;

    public String getTriggerKey() {
        return triggerKey == null ? getJobKey() : triggerKey;
    }

    public String getTriggerGroup() {
        return triggerGroup == null ? getTriggerGroup() : triggerGroup;
    }
}
