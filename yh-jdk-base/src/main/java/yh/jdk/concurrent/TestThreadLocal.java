package yh.jdk.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadLocal {

    private static final int HASH_INCREMENT = 0x61c88647;
    private static AtomicInteger nextHashCode =
            new AtomicInteger();
    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }
    private static final ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        System.out.println(nextHashCode());
        for (int i = 0; i < 5; i++) {
            new Thread(new MyThread(i)).start();
        }
    }

    static class MyThread implements Runnable {
        private int index;

        public MyThread(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("Thread " + index + " init value:" + value.get());
            for (int i = 0; i < 10; i++) {
                value.set(value.get() + i);
            }
            System.out.println("Thread " + index + " value:" + value.get());
        }
    }
}