package com.yh.metrics;

import com.codahale.metrics.Counter;
import com.yh.metrics.core.Base;
import com.yh.metrics.core.MyConsoleReport;

import java.util.Random;

/**
 * 记录执行次数 
 */  
public class CounterTest extends Base
{  
    final static Counter exec = metric.counter("com.yh.counter.invoke");
    public static void main(String[] args)  
    {  
        MyConsoleReport.startReport();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=3;i++)
                {
                    exec.inc();
                    milliSecondSleep(new Random().nextInt(500)*2);
                }
            }
        }).start();
        secondSleep(3);  
    }  
} 