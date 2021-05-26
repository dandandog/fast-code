package com.dandandog.framework.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dandandog.framework.task.entity.TaskJob;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/25 10:00
 * T
 */
public interface TaskJobService extends IService<TaskJob> {


    void start(TaskJob taskJob);

    void stop(TaskJob taskJob);

    void delete(TaskJob taskJob);

}
