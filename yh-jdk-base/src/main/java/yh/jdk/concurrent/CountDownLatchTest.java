package yh.jdk.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  
 * @author hb 
 *         CountDownLatch最重要的方法是countDown()和await()，前者主要是倒数一次，后者是等待倒数到0，如果没有到达0 
 *         ，就只有阻塞等待了。 *JAVA同步器之 
 *         CountDownLatch（不能循环使用，如果需要循环使用可以考虑使用CyclicBarrier） 两种比较常规用法： 1：new 
 *         CountDownLatch(1);所有的线程在开始工作前需要做一些准备工作，当所有的线程都准备到位后再统一执行时有用 2：new 
 *         CountDownLatch(THREAD_COUNT);当所有的线程都执行完毕后，等待这些线程的其他线程才开始继续执行时有用 
 */  
public class CountDownLatchTest {  
    private static final int THREAD_COUNT = 10;  
    // 在调用startSingal.countDown()之前调用了startSingal.await()的线程一律等待，直到startSingal.countDown()的调用  
    private static final CountDownLatch startSingal = new CountDownLatch(1);
    // 在finishedSingal的初始化记数量通过调用finishedSingal.countDown()减少为0时调用了finishedSingal.await()的线程一直阻塞  
    private static final CountDownLatch finishedSingal = new CountDownLatch(  
            THREAD_COUNT);  

    static ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {  
        for (int i = 0; i < THREAD_COUNT; i++) {  
            lock.tryLock();
            new Thread("Task " + i) {
                public void run() {  
                    System.out.println(Thread.currentThread().getName()  
                            + " prepared!!");  
                    try {  
                        startSingal.await();  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                    System.out.println("\n"+Thread.currentThread().getName()
                            + " finished!!");  
                    finishedSingal.countDown();  
                };  
            }.start();
            lock.unlock();
        }  
        Thread.sleep(1000);  
        startSingal.countDown();// 所有的线程被唤醒，同时开始工作.countDown 方法的线程等到计数到达零时才继续  
        finishedSingal.await();// 等待所有的线程完成!!
        System.out.println("\n\n\n\nAll task are finished!!");
    }  
}  