package java.jdk.base;

import java.util.concurrent.*;

/**
 * Created by yango on 2017/1/24.
 */
public class FinalTest {
      volatile  int[] intarr;
    int i  = 0 ;
    static FinalTest finalTest;
   static CountDownLatch c = new CountDownLatch(1);

    public FinalTest() {
        System.out.println(Thread.currentThread().getName()+" FinalTest start ...");
        this.intarr = new int[1];
        this.intarr[0] = 1;
        this.i = 10;
        System.out.println(Thread.currentThread().getName()+" FinalTest start ..." + intarr[0]);

    }

    public  static void writerOne() {
        System.out.println(Thread.currentThread().getName()+" writeOne start ...");
        finalTest = new FinalTest();
        System.out.println(Thread.currentThread().getName()+" writeOne end ..." + finalTest.intarr[0]);
    }

    public  static void writeTwo() {
        System.out.println(Thread.currentThread().getName()+" writeTwo start ..." + finalTest.intarr[0]);
        finalTest.intarr[0] = 2;
        System.out.println(Thread.currentThread().getName()+" writeTwo end ..." + finalTest.intarr[0]);
    }

    public   static int read() {
        System.out.println(Thread.currentThread().getName()+" read start ..." + finalTest.intarr[0]);
        System.out.println(Thread.currentThread().getName()+" read start i..." + finalTest.i );
        if (finalTest != null) {
            return finalTest.intarr[0];
        }
        System.out.println(Thread.currentThread().getName()+" read start ..." + finalTest.intarr[0]);
        return -1;
    }

    public static void main(String[] args) {
//        FinalTest finalTest = new FinalTest();
        FinalTest.writerOne();
        Executor executor = Executors.newFixedThreadPool(3);
        Runnable rA = new Runnable() {
            @Override
            public void run() {
                FinalTest.writerOne();
            }
        };
        Runnable rB = new Runnable() {
            @Override
            public void run() {
                FinalTest.writeTwo();
//                c.countDown();
            }
        };
        Runnable rC = new Runnable() {
            @Override
            public void run() {
//                try {
//                    c.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                FinalTest.read();
            }
        };
        Thread tA = new Thread(rA);
        tA.setName("tA");
        Thread tB = new Thread(rB);
        tB.setName("tB");
        Thread tC = new Thread(rC);
        tC.setName("tC");
//        tA.start();
        tB.start();
        tC.start();
//        try {
//            c.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("end......");
        ConcurrentHashMap<String, String> hash = new ConcurrentHashMap();
        String s  = hash.putIfAbsent("a1","valuea1");
        System.out.println("=="+s);
        s  = hash.putIfAbsent("a1","valuea2");
        System.out.println("=="+s);
        s  = hash.putIfAbsent("a1","valuea3");
        System.out.println("=="+s);
        String b  = hash.putIfAbsent("b1","valueb1");
        System.out.println("=="+b);
    }


}
