package yh.jdk.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by yango on 2017/3/30.
 */
public class JConsoleTest {

    public static void createbusyThread() {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) ;
                    }
                }, "createbusyThread"
        );
        thread.start();
    }

    public static void createLockThread(final Object o) {
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        synchronized(o) {
                            try {
                                o.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                , "createLockThread");
        thread.start();
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*try {
            br.readLine();
            createbusyThread();
            br.readLine();
            Object o = new Object();
            createLockThread(o);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        deadLock();
    }

    public static void deadLock(){
        for (int i=0 ; i<100; i++) {
            new Thread(new DeadLock(1, 2)).start();
            new Thread(new DeadLock(2, 1)).start();
        }
    }

    static class DeadLock implements Runnable {
        int a,b;
        public  DeadLock(int a,int b){
            this.a= a;
            this.b = b;
        }


        @Override
        public void run() {
            synchronized (Integer.valueOf(a)){
                synchronized(Integer.valueOf(b)){
                    System.out.println(a+b);
                }
            }
        }
    }


}
