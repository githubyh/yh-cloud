package com.yh.metrics;

import com.codahale.metrics.Histogram;
import com.yh.metrics.core.Base;
import com.yh.metrics.core.MyConsoleReport;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Histogram可以为数据流提供统计数据。 除了最大值，最小值，平均值外，它还可以测量 中值(median)， 
 * 百分比比如XX%这样的Quantile数据  
 */  
public class Histogram1Test extends Base
{  
    static final Histogram his = metric.histogram("com.yh.histogram.score");
    static List<Integer> scores = Arrays.asList(60, 75, 80, 62, 90, 42, 33, 95, 61, 73);
    public static void main(String[] args)   
    {  
        MyConsoleReport.startReport();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer score : scores){
                    his.update(score);
                milliSecondSleep(new Random().nextInt(500) * 2);
                }
            }
        }).start();  
        secondSleep(10);  
    }  
}  