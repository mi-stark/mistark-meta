package com.mistark.meta.time;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Clock {

    private static AtomicLong time = new AtomicLong(System.currentTimeMillis());
    private static ScheduledExecutorService executorService;
    private static long STEP = 1;

    public static long currentMillis(){
        if(executorService == null) {
            executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleAtFixedRate(()-> time.set(System.currentTimeMillis()), STEP, STEP, TimeUnit.MILLISECONDS);
        }
        return time.get();
    }

    public static Date currentDate(){
        return new Date(currentMillis());
    }

}
