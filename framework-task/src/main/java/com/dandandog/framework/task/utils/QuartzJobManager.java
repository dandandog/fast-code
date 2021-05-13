package com.dandandog.framework.task.utils;

import com.dandandog.framework.task.entity.BaseJobEntity;
import com.dandandog.framework.task.entity.CronJobEntity;
import com.dandandog.framework.task.entity.SimpleJobEntity;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/13 14:40
 */
@Component
public class QuartzJobManager {

    private final String JOB_NAME = "TASK_";

    @Resource
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 获取调度器
     */
    public Scheduler getScheduler() {
        return schedulerFactoryBean.getScheduler();
    }

    /**
     * 获取 jobkey
     */
    public JobKey getJobKey(Long jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * 获取 triggerKey
     */
    public TriggerKey getTriggerKey(Long jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }

    public Trigger getTrigger(Scheduler scheduler, Long jobId) {
        try {
            return scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("获取定时任务CronTrigger出现异常", e);
        }
    }

    /**
     * 创建任务
     *
     * @param jobEntity 任务实体类（对应自定义的数据库任务表）
     * @return Date 返回创建任务成功后执行时间
     */
    public Date createScheduleJob(BaseJobEntity jobEntity) {
        try {
            Scheduler scheduler = getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(jobEntity.getJobClass()).withIdentity(getJobKey(jobEntity.getJobId())).build();
            ScheduleBuilder<?> scheduleBuilder = null;
            if (jobEntity instanceof CronJobEntity) {
                CronJobEntity entity = (CronJobEntity) jobEntity;
                if (!CronExpression.isValidExpression(entity.getCronExpression())) {
                    throw new RuntimeException("CronExpression '");
                }
                scheduleBuilder = CronScheduleBuilder.cronSchedule(entity.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            } else if (jobEntity instanceof SimpleJobEntity) {
                SimpleJobEntity entity = (SimpleJobEntity) jobEntity;
                SimpleScheduleBuilder simpleSchedule = SimpleScheduleBuilder.simpleSchedule();
                if (entity.isForever()) {
                    simpleSchedule = simpleSchedule.repeatForever();
                }
                scheduleBuilder = simpleSchedule.withRepeatCount(entity.getRepeatCount())
                        .withIntervalInSeconds(entity.getIntervalInSeconds());
            }

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobEntity.getJobId()))
                    .startAt(jobEntity.getStartAt()).endAt(jobEntity.getEndAt()).withSchedule(scheduleBuilder).build();

            jobDetail.getJobDataMap().put(BaseJobEntity.JOB_PARAM_KEY, jobEntity);
            Date startTime = scheduler.scheduleJob(jobDetail, trigger);
            if (jobEntity.getStatus() == 1) {
                pauseJob(jobEntity.getJobId());
            }
            return startTime;
        } catch (SchedulerException e) {
            throw new RuntimeException("创建定时任务失败", e);
        }
    }


    public Date updateScheduleJob(BaseJobEntity jobEntity) {
        try {
            TriggerKey triggerKey = getTriggerKey(jobEntity.getJobId());
            Scheduler scheduler = getScheduler();

            Trigger trigger = null;
            if (jobEntity instanceof CronJobEntity) {
                CronJobEntity entity = (CronJobEntity) jobEntity;
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(entity.getCronExpression()).withMisfireHandlingInstructionDoNothing();
                CronTrigger cronTrigger = (CronTrigger) getTrigger(scheduler, entity.getJobId());

                trigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            } else if (jobEntity instanceof SimpleJobEntity) {
                SimpleJobEntity entity = (SimpleJobEntity) jobEntity;
                SimpleScheduleBuilder simpleSchedule = SimpleScheduleBuilder.simpleSchedule();
                SimpleTrigger simpleTrigger = (SimpleTrigger) getTrigger(scheduler, entity.getJobId());
                if (entity.isForever()) {
                    simpleSchedule = simpleSchedule.repeatForever();
                }
                simpleSchedule.withRepeatCount(entity.getRepeatCount())
                        .withIntervalInSeconds(entity.getIntervalInSeconds());

                trigger = simpleTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(simpleSchedule).build();
            }

            if (trigger != null) {
                trigger.getJobDataMap().put(BaseJobEntity.JOB_PARAM_KEY, jobEntity);
                Date updateTime = scheduler.rescheduleJob(triggerKey, trigger);
                if (jobEntity.getStatus() == 1) {
                    pauseJob(jobEntity.getJobId());
                }
                return updateTime;
            }
            throw new RuntimeException();
        } catch (SchedulerException e) {
            throw new RuntimeException("更新定时任务失败", e);
        }
    }


    /**
     * 暂停任务
     *
     * @param jobId 任务jobId
     */
    public void pauseJob(Long jobId) {
        try {
            getScheduler().pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("暂停定时任务失败", e);
        }
    }

    /**
     * 恢复任务
     *
     * @param jobId 任务jobId
     */
    public void resumeJob(Long jobId) {
        try {
            getScheduler().resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("恢复定时任务失败", e);
        }
    }

    /**
     * 验证定时任务是否存在
     *
     * @param jobId 任务id
     * @return
     */
    public boolean check(Long jobId) {
        try {
            return getScheduler().checkExists(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("验证定时任务是否存在失败", e);
        }
    }

    /**
     * 删除定时任务
     *
     * @param jobId 定时任务id
     */
    public void deleteScheduleJob(Long jobId) {
        Scheduler scheduler = getScheduler();
        try {
            scheduler.pauseTrigger(getTriggerKey(jobId));
            scheduler.unscheduleJob(getTriggerKey(jobId));
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("删除定时任务失败", e);
        }
    }

}
