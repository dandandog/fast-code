package com.dandandog.framework.task.utils;

import com.dandandog.framework.task.entity.CronTaskJob;
import com.dandandog.framework.task.QuartzJob;
import com.dandandog.framework.task.entity.SimpleTaskJob;
import com.dandandog.framework.task.entity.TaskJob;
import org.quartz.*;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/13 14:40
 */
@Component
public class QuartzJobManager {

    private final String JOB_NAME = "TASK_";

    @Resource
    private SchedulerFactoryBean schedulerFactoryBean;


    public Scheduler getScheduler() {
        return schedulerFactoryBean.getScheduler();
    }


    public JobKey getJobKey(String key, String group) {
        return JobKey.jobKey(JOB_NAME + key, group);
    }


    public TriggerKey getTriggerKey(String key, String group) {
        return TriggerKey.triggerKey(JOB_NAME + key, group);
    }

    public Trigger getTrigger(Scheduler scheduler, String key, String group) {
        try {
            return scheduler.getTrigger(getTriggerKey(key, group));
        } catch (SchedulerException e) {
            throw new RuntimeException("获取定时任务CronTrigger出现异常", e);
        }
    }


    public Date createScheduleJob(TaskJob jobEntity) {
        try {
            Scheduler scheduler = getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).withIdentity(getJobKey(jobEntity.getJobKey(), jobEntity.getJobGroup())).build();
            ScheduleBuilder<?> scheduleBuilder = null;
            if (Objects.equals(jobEntity.getType(), TaskJob.JobType.SIMPLE)) {
                SimpleTaskJob entity = (SimpleTaskJob) jobEntity;
                scheduleBuilder = createSimpleSchedule(entity);
            } else if (Objects.equals(jobEntity.getType(), TaskJob.JobType.CRON)) {
                CronTaskJob entity = (CronTaskJob) jobEntity;
                scheduleBuilder = createCronSchedule(entity);
            }
            Trigger trigger = createTrigger(jobEntity, scheduleBuilder);
            jobDetail.getJobDataMap().put(TaskJob.JOB_PARAM_KEY, jobEntity);
            Date startTime = scheduler.scheduleJob(jobDetail, trigger);
            if (jobEntity.getStatus() == 1) {
                pauseJob(jobEntity.getJobKey(), jobEntity.getJobGroup());
            }
            return startTime;
        } catch (SchedulerException e) {
            throw new RuntimeException("创建定时任务失败", e);
        }
    }

    private CronScheduleBuilder createCronSchedule(CronTaskJob entity) {
        if (!CronExpression.isValidExpression(entity.getCronExpression())) {
            throw new RuntimeException("CronExpression '");
        }
        return CronScheduleBuilder.cronSchedule(entity.getCronExpression()).withMisfireHandlingInstructionDoNothing();
    }

    private SimpleScheduleBuilder createSimpleSchedule(SimpleTaskJob entity) {
        SimpleScheduleBuilder simpleSchedule = SimpleScheduleBuilder.simpleSchedule();
        if (entity.isForever()) {
            simpleSchedule.repeatForever();
        }
        if (entity.getRepeatCount() != 0) {
            simpleSchedule.withRepeatCount(entity.getRepeatCount());
        }
        if (entity.getIntervalInSeconds() != 0) {
            simpleSchedule.withIntervalInSeconds(entity.getIntervalInSeconds());
        }
        return simpleSchedule;
    }

    private Trigger createTrigger(TaskJob jobEntity, ScheduleBuilder<?> scheduleBuilder) {
        TriggerBuilder<?> triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(getTriggerKey(jobEntity.getTriggerKey(), jobEntity.getTriggerGroup()))
                .withSchedule(scheduleBuilder);
        if (jobEntity.getStartAt() != null) {
            Date start = Date.from(jobEntity.getStartAt().atZone(ZoneId.systemDefault()).toInstant());
            triggerBuilder.startAt(start);
        }
        if (jobEntity.getEndAt() != null) {
            Date end = Date.from(jobEntity.getEndAt().atZone(ZoneId.systemDefault()).toInstant());
            triggerBuilder.endAt(end);
        }
        return triggerBuilder.build();
    }


    public Date updateScheduleJob(TaskJob jobEntity) {
        try {
            TriggerKey triggerKey = getTriggerKey(jobEntity.getTriggerKey(), jobEntity.getTriggerGroup());
            Scheduler scheduler = getScheduler();
            Trigger trigger = null;
            if (jobEntity instanceof CronTaskJob) {
                CronTaskJob entity = (CronTaskJob) jobEntity;
                CronScheduleBuilder scheduleBuilder = createCronSchedule(entity);
                CronTrigger cronTrigger = (CronTrigger) getTrigger(scheduler, entity.getTriggerKey(), jobEntity.getTriggerGroup());
                trigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            } else if (jobEntity instanceof SimpleTaskJob) {
                SimpleTaskJob entity = (SimpleTaskJob) jobEntity;
                SimpleScheduleBuilder simpleSchedule = createSimpleSchedule(entity);
                SimpleTrigger simpleTrigger = (SimpleTrigger) getTrigger(scheduler, entity.getTriggerKey(), jobEntity.getTriggerGroup());
                trigger = simpleTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(simpleSchedule).build();
            }

            if (trigger != null) {
                trigger.getJobDataMap().put(TaskJob.JOB_PARAM_KEY, jobEntity);
                Date updateTime = scheduler.rescheduleJob(triggerKey, trigger);
                if (jobEntity.getStatus() == 1) {
                    pauseJob(jobEntity.getTriggerKey(), jobEntity.getTriggerGroup());
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
            dataMap.put(TaskJob.JOB_PARAM_KEY, taskJob);
            getScheduler().triggerJob(getJobKey(taskJob.getJobKey(), taskJob.getJobGroup()), dataMap);
        } catch (SchedulerException e) {
            throw new RuntimeException("立即执行定时任务失败", e);
        }
    }


    public void pauseJob(String jobId, String group) {
        try {
            getScheduler().pauseJob(getJobKey(jobId, group));
        } catch (SchedulerException e) {
            throw new RuntimeException("暂停定时任务失败", e);
        }
    }


    public void resumeJob(String jobId, String group) {
        try {
            getScheduler().resumeJob(getJobKey(jobId, group));
        } catch (SchedulerException e) {
            throw new RuntimeException("恢复定时任务失败", e);
        }
    }


    public boolean check(String jobId, String group) {
        try {
            return getScheduler().checkExists(getJobKey(jobId, group));
        } catch (SchedulerException e) {
            throw new RuntimeException("验证定时任务是否存在失败", e);
        }
    }

    @Transactional
    public void deleteScheduleJob(String jobId, String group) {
        Scheduler scheduler = getScheduler();
        try {
            scheduler.pauseTrigger(getTriggerKey(jobId, group));
            scheduler.unscheduleJob(getTriggerKey(jobId, group));
            scheduler.deleteJob(getJobKey(jobId, group));
        } catch (SchedulerException e) {
            throw new RuntimeException("删除定时任务失败", e);
        }
    }

}
