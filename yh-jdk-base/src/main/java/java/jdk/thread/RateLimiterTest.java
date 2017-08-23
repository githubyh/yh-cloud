package java.jdk.thread;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by yango on 2017/7/5.
 */
public class RateLimiterTest {

    static RateLimiter  limiter = RateLimiter.create(1);


    static Executor executor = Executors.newFixedThreadPool(10);
    public static void  main(String[] args) {
        /*for (int i = 0; i < 20; i++) {
            executor.execute(new Thread("thread" + i) {
                @Override
                public void run() {
                    if (limiter.tryAcquire(5, TimeUnit.SECONDS)) {
                        System.out.println(Thread.currentThread().getName() + "=开始执行！");
                        try {
                            Thread.sleep(3000l);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "= 执行万！");
                    } else {
                        System.out.println(Thread.currentThread().getName() + "else...");
                        try {
                            Thread.sleep(5000l);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


        }*/
        testNoRateLimiter();
        testWithRateLimiter();
    }
    public static void testNoRateLimiter(){
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);

    }

    public static void testWithRateLimiter(){
        Long start = System.currentTimeMillis();
        RateLimiter limiter = RateLimiter.create(5.0); // 每秒不超过10个任务被提交
        for (int i = 0; i < 10; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
            if (!limiter.tryAcquire(5, TimeUnit.SECONDS)) {
                try {
                    Thread.sleep(5000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);

    }




}
