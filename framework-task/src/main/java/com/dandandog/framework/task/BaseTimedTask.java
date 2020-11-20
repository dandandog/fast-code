package com.dandandog.framework.task;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.DelayQueue;

/**
 * @author JohnnyLiu
 */
public abstract class BaseTimedTask implements ITimedTask, InitializingBean {

    protected DelayQueue<DelayedTask> queue = new DelayQueue<>();

    @Resource
    private ThreadPoolTaskExecutor executorService;


    @Override
    public void afterPropertiesSet() throws Exception {
        init();
        executorService.submit(task());
    }

    protected abstract void init();

    public void addTask(DelayedTask task) {
        queue.offer(task);
    }

    private Runnable task() {
        return () -> {
            while (queue.size() != 0) {
                DelayedTask task = null;
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
