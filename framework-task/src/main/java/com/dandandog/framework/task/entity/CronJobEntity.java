package com.dandandog.framework.task.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/13 16:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CronJobEntity extends BaseJobEntity {

    /**
     * cron表达式
     */
    private String cronExpression;

}
