package com.zwyue.timeTask;

import com.zwyue.interfaces.Updator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

/**
 * TermTimeTask class
 * 定时任务
 *
 * @author zwy
 */
public class ScheduledUpdater {

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();


    private final long initialDelayInSeconds;

    private final long periodInSeconds;

    private final Updator updator;

    public ScheduledUpdater(Updator updator, long initialDelayInSeconds, long periodInSeconds) {
        this.updator = updator;
        this.initialDelayInSeconds = initialDelayInSeconds;
        this.periodInSeconds = periodInSeconds;
    }


    public void run() {
        executor.scheduleAtFixedRate(updator::update, this.initialDelayInSeconds,
            this.periodInSeconds,
            TimeUnit.SECONDS);
    }
}
