package com.yh.metrics;

import com.codahale.metrics.Gauge;
import com.yh.metrics.core.Base;
import com.yh.metrics.core.MyConsoleReport;

/**
 * 获取某个值 
 */  
public class GaugeTest extends Base
{  
    public static void main(String[] args)  
    {  
        MyConsoleReport.startReport();
        metric.register("com.yh.gauge.freeMemory", new Gauge<Long>(){
            public Long getValue() {  
                //这里是获取当前JVM可用内存  
                return Runtime.getRuntime().freeMemory();  
            }  
        });  
        secondSleep(2);  
    }  
}  