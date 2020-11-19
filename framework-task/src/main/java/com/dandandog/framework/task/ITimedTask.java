package com.dandandog.framework.task;

/**
 * @author JohnnyLiu
 */
public interface ITimedTask {

    void addTask(ITask task);

    void runTask(ITask task);

}
