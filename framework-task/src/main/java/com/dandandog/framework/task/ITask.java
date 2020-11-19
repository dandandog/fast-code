package com.dandandog.framework.task;

import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author JohnnyLiu
 */
@Data
public class ITask implements Delayed {

    private LocalDateTime expiredTime;

    private Object sendData;


    public ITask(LocalDateTime expiredTime, Object sendData) {
        this.expiredTime = expiredTime;
        this.sendData = sendData;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long seconds = LocalDateTimeUtil.between(LocalDateTimeUtil.now(), expiredTime).getSeconds();
        return unit.convert(seconds, TimeUnit.MILLISECONDS);
    }


    @Override
    public int compareTo(Delayed o) {
        return this.getExpiredTime().compareTo(((ITask) o).getExpiredTime());
    }


}
