package com.dandandog.framework.task.listener;

import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/13 16:12
 */
public class TriggerCountListener extends TriggerListenerSupport {

    private String name;

    private Integer count;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        Integer current = (Integer) context.getJobDetail().getJobDataMap().get("count");
        System.out.println("调用次数:" + current);
        if (count == 0) {
            try {
                context.getScheduler().shutdown();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
        super.triggerComplete(trigger, context, triggerInstructionCode);
    }
}
