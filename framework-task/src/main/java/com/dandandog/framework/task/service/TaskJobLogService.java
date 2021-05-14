package com.dandandog.framework.task.service;

import com.dandandog.framework.task.entity.TaskJobLog;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/14 9:55
 */
public interface TaskJobLogService {

    <T extends TaskJobLog> void saveLog(T log);

}
