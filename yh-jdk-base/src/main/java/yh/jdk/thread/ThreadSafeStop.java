package yh.jdk.thread;

import java.util.concurrent.TimeUnit;

/**
 * 安全终止线程
 *
 * @author Jeff Lee @ bysocket.com
 * @since 2018年02月23日19:03:02
 */
public class ThreadSafeStop {

    public static void main(String[] args) throws Exception {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "CountThread1");
        countThread.start();
        // 睡眠 1 秒，通知 CountThread 中断，并终止线程
        TimeUnit.SECONDS.sleep(1);
        Thread.interrupted();
        System.out.println("InterruptedThread interrupted is " + countThread.isInterrupted());
        Runner two = new Runner();
        countThread = new Thread(two,"CountThread2");
        countThread.start();
        // 睡眠 1 秒，然后设置线程停止状态，并终止线程
        TimeUnit.SECONDS.sleep(1);
        two.stopSafely();



        System.out.println("A处线程：" + Thread.currentThread().getName() + ", 所属线程：" + Thread.currentThread().getThreadGroup().getName() +
                ", 组中有线程组数量：" + Thread.currentThread().getThreadGroup().activeGroupCount());
        ThreadGroup group = new ThreadGroup("新的组");
        System.out.println("B处线程：" + Thread.currentThread().getName() + ", 所属线程：" + Thread.currentThread().getThreadGroup().getName() +
                ", 组中有线程组数量：" + Thread.currentThread().getThreadGroup().activeGroupCount());
        ThreadGroup[] tg = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        Thread.currentThread().getThreadGroup().enumerate(tg);
        for (int i = 0; i < tg.length; i++)
            System.out.println("第一个线程组名称为：" + tg[i].getName());
    }

    private static class Runner implements Runnable {

        private long i;

        // 终止状态
        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                // 线程执行具体逻辑
                i++;
            }
            System.out.println(Thread.currentThread().getName()+"Count i = " + i);
        }

        public void stopSafely() {
            on = false;
        }
    }
}