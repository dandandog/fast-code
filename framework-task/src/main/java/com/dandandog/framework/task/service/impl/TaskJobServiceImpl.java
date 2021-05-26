package com.dandandog.framework.task.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dandandog.framework.core.entity.BaseEntity;
import com.dandandog.framework.task.dao.TaskJobDao;
import com.dandandog.framework.task.entity.SimpleTaskJob;
import com.dandandog.framework.task.entity.TaskJob;
import com.dandandog.framework.task.service.TaskJobService;
import com.dandandog.framework.task.utils.QuartzJobManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/14 11:46
 */
@Service
public class TaskJobServiceImpl extends ServiceImpl<TaskJobDao, TaskJob> implements TaskJobService {


    @Resource
    private QuartzJobManager quartzJobManager;


    @Override
    @Transactional
    public void start(TaskJob taskJob) {
        Optional<TaskJob> opt = lambdaQuery().eq(TaskJob::getJobGroup, taskJob.getJobGroup()).eq(TaskJob::getJobKey, taskJob.getJobKey()).
                oneOpt();
        String id = opt.map(BaseEntity::getId).orElse(null);
        taskJob.setId(id);
        saveOrUpdate(taskJob);
        if (!quartzJobManager.check(taskJob.getJobKey(), taskJob.getJobGroup())) {
            quartzJobManager.createScheduleJob(taskJob);
        } else {
            quartzJobManager.updateScheduleJob(taskJob);
        }
    }

    @Override
    public void stop(TaskJob taskJob) {
        if (taskJob != null && taskJob.getId() != null) {
            quartzJobManager.pauseJob(taskJob.getJobKey(), taskJob.getJobGroup());
        }
    }

    @Override
    public void delete(TaskJob taskJob) {
        if (taskJob != null && taskJob.getId() != null) {
            quartzJobManager.deleteScheduleJob(taskJob.getJobKey(), taskJob.getJobGroup());
        }
    }
}
