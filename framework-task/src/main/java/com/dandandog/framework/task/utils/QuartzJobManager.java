package com.dandandog.framework.task.utils;

import com.dandandog.framework.task.entity.TaskJob;
import com.dandandog.framework.task.entity.CronTaskJob;
import com.dandandog.framework.task.entity.QuartzJob;
import com.dandandog.framework.task.entity.SimpleTaskJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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
    public JobKey getJobKey(String jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * 获取 triggerKey
     */
    public TriggerKey getTriggerKey(String jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }

    public Trigger getTrigger(Scheduler scheduler, String jobId) {
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
    public Date createScheduleJob(TaskJob jobEntity) {
        try {
            Scheduler scheduler = getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).withIdentity(getJobKey(jobEntity.getId())).build();
            ScheduleBuilder<?> scheduleBuilder = null;
            if (jobEntity instanceof CronTaskJob) {
                CronTaskJob entity = (CronTaskJob) jobEntity;
                if (!CronExpression.isValidExpression(entity.getCronExpression())) {
                    throw new RuntimeException("CronExpression '");
                }
                scheduleBuilder = CronScheduleBuilder.cronSchedule(entity.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            } else if (jobEntity instanceof SimpleTaskJob) {
                SimpleTaskJob entity = (SimpleTaskJob) jobEntity;
                SimpleScheduleBuilder simpleSchedule = SimpleScheduleBuilder.simpleSchedule();
                if (entity.isForever()) {
                    simpleSchedule = simpleSchedule.repeatForever();
                }
                scheduleBuilder = simpleSchedule.withRepeatCount(entity.getRepeatCount())
                        .withIntervalInSeconds(entity.getIntervalInSeconds());
            }

            TriggerBuilder<?> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobEntity.getId())).withSchedule(scheduleBuilder);

            if (jobEntity.getStartAt() != null) {
                triggerBuilder.startAt(jobEntity.getStartAt());
            }
            if (jobEntity.getEndAt() != null) {
                triggerBuilder.startAt(jobEntity.getEndAt());
            }

            jobDetail.getJobDataMap().put(TaskJob.JOB_PARAM_KEY, jobEntity);
            Date startTime = scheduler.scheduleJob(jobDetail, triggerBuilder.build());
            if (jobEntity.getStatus() == 1) {
                pauseJob(jobEntity.getId());
            }
            return startTime;
        } catch (SchedulerException e) {
            throw new RuntimeException("创建定时任务失败", e);
        }
    }


    public Date updateScheduleJob(TaskJob jobEntity) {
        try {
            TriggerKey triggerKey = getTriggerKey(jobEntity.getId());
            Scheduler scheduler = getScheduler();

            Trigger trigger = null;
            if (jobEntity instanceof CronTaskJob) {
                CronTaskJob entity = (CronTaskJob) jobEntity;
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(entity.getCronExpression()).withMisfireHandlingInstructionDoNothing();
                CronTrigger cronTrigger = (CronTrigger) getTrigger(scheduler, entity.getId());

                trigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            } else if (jobEntity instanceof SimpleTaskJob) {
                SimpleTaskJob entity = (SimpleTaskJob) jobEntity;
                SimpleScheduleBuilder simpleSchedule = SimpleScheduleBuilder.simpleSchedule();
                SimpleTrigger simpleTrigger = (SimpleTrigger) getTrigger(scheduler, entity.getId());
                if (entity.isForever()) {
                    simpleSchedule = simpleSchedule.repeatForever();
                }
                simpleSchedule.withRepeatCount(entity.getRepeatCount())
                        .withIntervalInSeconds(entity.getIntervalInSeconds());

                trigger = simpleTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(simpleSchedule).build();
            }

            if (trigger != null) {
                trigger.getJobDataMap().put(TaskJob.JOB_PARAM_KEY, jobEntity);
                Date updateTime = scheduler.rescheduleJob(triggerKey, trigger);
                if (jobEntity.getStatus() == 1) {
                    pauseJob(jobEntity.getId());
                }
                return updateTime;
            }
            throw new RuntimeException();
        } catch (SchedulerException e) {
            throw new RuntimeException("更新定时任务失败", e);
        }
    }


    public void runJob(TaskJob taskJob) {
        try {
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(taskJob.JOB_PARAM_KEY, taskJob);
            getScheduler().triggerJob(getJobKey(taskJob.getId()), dataMap);
        } catch (SchedulerException e) {
            throw new RuntimeException("立即执行定时任务失败", e);
        }
    }

    /**
     * 暂停任务
     *
     * @param jobId 任务jobId
     */
    public void pauseJob(String jobId) {
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
    public void resumeJob(String jobId) {
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
    public boolean check(String jobId) {
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
    public void deleteScheduleJob(String jobId) {
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
