package com.dandandog.framework.task.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dandandog.framework.task.entity.TaskJobLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/14 11:27
 */
@Mapper
public interface TaskJobLogDao extends BaseMapper<TaskJobLog> {
}
