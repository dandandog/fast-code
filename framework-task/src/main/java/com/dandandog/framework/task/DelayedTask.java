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
public class DelayedTask implements Delayed {

    protected LocalDateTime expiredTime;

    @Override
    public long getDelay(TimeUnit unit) {
        long seconds = LocalDateTimeUtil.between(LocalDateTimeUtil.now(), expiredTime).getSeconds();
        return unit.convert(seconds, TimeUnit.MILLISECONDS);
    }


    @Override
    public int compareTo(Delayed o) {
        return this.getExpiredTime().compareTo(((DelayedTask) o).getExpiredTime());
    }


}
