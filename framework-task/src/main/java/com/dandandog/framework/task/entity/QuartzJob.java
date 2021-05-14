package com.dandandog.framework.task.entity;

import com.dandandog.framework.common.utils.SpringContextUtil;
import com.dandandog.framework.task.service.TaskJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/13 15:50
 */
@Slf4j
public class QuartzJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        TaskJob taskJob = (TaskJob) jobExecutionContext.getMergedJobDataMap().get(TaskJob.JOB_PARAM_KEY);
        log.info("定时器参数，{}", taskJob);

        // 获取日志服务对象Bean
        TaskJobLogService logService = SpringContextUtil.getBean(TaskJobLogService.class);
        //数据库保存执行记录
        TaskJobLog logEntity = new TaskJobLog();
        logEntity.setJobId(taskJob.getId());
        logEntity.setBeanName(taskJob.getBeanName());
        logEntity.setParams(taskJob.getParams());

        // 任务开始执行时间
        long startTime = System.currentTimeMillis();
        try {
            //执行任务
            log.debug("任务准备执行，任务ID：" + taskJob.getId());

            Object target = SpringContextUtil.getBean(taskJob.getBeanName());
            Method method = target.getClass().getDeclaredMethod("run", String.class);
            TaskJobResult result = (TaskJobResult) method.invoke(target, taskJob.getParams());


            long times = System.currentTimeMillis() - startTime;
            logEntity.setTimes((int) times);
            logEntity.setStatus(0);
            if (null != result) {
                logEntity.setStatus(result.getCode());
                logEntity.setMessage(result.getMsg());
            }
            log.debug("任务执行完毕，任务ID：" + taskJob.getId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            log.error("任务执行失败，任务ID：" + taskJob.getId(), e);

            long times = System.currentTimeMillis() - startTime;

            logEntity.setTimes((int) times);
            logEntity.setStatus(1);
            logEntity.setError(e.toString().substring(0, 2000));
        } finally {
            // 最终记录到数据库
            logService.saveLog(logEntity);
        }
    }
}
