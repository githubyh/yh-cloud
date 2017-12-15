package com.yh.metrics.core;

import java.util.concurrent.TimeUnit;
import com.codahale.metrics.MetricRegistry;  
  
public class Base   
{  
    protected static MetricRegistry metric = MetricConstant.REGISTER;  
    protected static void secondSleep(long value)  
    {  
        try  
        {  
            TimeUnit.SECONDS.sleep(value);  
        } catch (InterruptedException e)  
        {  
            e.printStackTrace();  
        }  
    }  
    protected static void milliSecondSleep(long value)  
    {  
        try  
        {  
            TimeUnit.MILLISECONDS.sleep(value);  
        } catch (InterruptedException e)  
        {  
            e.printStackTrace();  
        }  
    }  
}  