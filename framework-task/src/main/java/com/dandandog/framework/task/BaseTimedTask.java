package com.dandandog.framework.task;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.DelayQueue;

/**
 * @author JohnnyLiu
 */
public abstract class BaseTimedTask implements ITimedTask, InitializingBean {

    protected DelayQueue<ITask> queue = new DelayQueue<>();

    @Resource
    private ThreadPoolTaskExecutor executorService;


    @Override
    public void afterPropertiesSet() throws Exception {
        init();
        executorService.submit(task());
    }

    protected abstract void init();

    public void addTask(ITask task) {
        queue.offer(task);
    }

    private Runnable task() {
        return () -> {
            while (queue.size() != 0) {
                ITask task = null;
                try {
                    task = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runTask(task);
            }
        };
    }


}
