package com.dandandog.framework.task.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dandandog.framework.task.entity.TaskJob;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/14 11:27
 */
@Mapper
public interface TaskJobDao extends BaseMapper<TaskJob> {
}
