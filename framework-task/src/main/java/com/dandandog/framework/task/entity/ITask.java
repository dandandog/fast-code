package com.dandandog.framework.task.entity;

/**
 * @Author: JohnnyLiu
 * @Date: 2021/5/14 10:19
 */
public interface ITask {

    <T> TaskJobResult<T> run(String params);
}
