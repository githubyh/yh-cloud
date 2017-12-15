package com.yh.metrics;

import com.codahale.metrics.Timer;
import com.yh.metrics.core.Base;
import com.yh.metrics.core.MyConsoleReport;

/**
 * Timer用来测量一段代码被调用的速率和用时。 
 * 等于Meter+Hitogram，既算TPS，也算执行时间。 
 */  
public class TimerTest extends Base
{  
    static final Timer timer = metric.timer("com.pp.timer.invoke");
    static void inovke(long time)  
    {  
        final Timer.Context context = timer.time();  
        try  
        {  
            secondSleep(time);  
        }finally  
        {  
            context.stop();  
        }  
    }  
    public static void main(String[] args)   
    {  
        MyConsoleReport.startReport();
        inovke(1);  
        inovke(2);  
        inovke(2);  
        inovke(8);  
        secondSleep(1);  
    }  
}  