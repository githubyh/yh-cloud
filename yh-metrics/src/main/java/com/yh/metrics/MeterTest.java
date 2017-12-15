package com.yh.metrics;

import com.codahale.metrics.Meter;
import com.yh.metrics.core.Base;
import com.yh.metrics.core.MyConsoleReport;

import java.util.Random;

/**
 * Meter用来计算事件的速率 
 */  
public class MeterTest extends Base
{  
    static final Meter requests = metric.meter("com.yh.meter.invoke");
    public static void main(String[] args)  
    {  
        MyConsoleReport.startReport();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 2; i++) {
                    requests.mark();
                    milliSecondSleep(new Random().nextInt(500) * 2);
                }
            }
        }).start();  
        secondSleep(2);  
    }  
}  