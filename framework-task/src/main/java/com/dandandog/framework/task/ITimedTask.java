package com.dandandog.framework.task;

/**
 * @author JohnnyLiu
 */
public interface ITimedTask {

    void addTask(DelayedTask task);

    void runTask(DelayedTask task);

}
