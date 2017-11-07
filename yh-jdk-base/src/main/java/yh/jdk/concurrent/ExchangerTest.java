package yh.jdk.concurrent;

import java.util.ArrayList;
import java.util.List;  
import java.util.concurrent.Exchanger;  
  
public class ExchangerTest {  
    final static Exchanger<List<String>> exchanger = new Exchanger<List<String>>();  
  
    public static void main(String[] args) {  
        new Producer("Producer", exchanger).start();  
        new Consumer("Consumer", exchanger).start();  
    }  
  
    static class Producer extends Thread {  
        private Exchanger<List<String>> exchanger;  
  
        /**  
         *   
         */  
        public Producer(String threadName, Exchanger<List<String>> exchanger) {  
            super(threadName);  
            this.exchanger = exchanger;  
        }  
  
        /* 
         * (non-Javadoc) 
         *  
         * @see java.lang.Thread#run() 
         */  
        @Override  
        public void run() {  
            List<String> products = new ArrayList<String>();  
            for (int i = 0; i < 10; i++) {  
                products.add("product " + i);  
            }  
            try {  
                List<String> results = exchanger.exchange(products);  
                System.out.println("get results from consumer");  
                for (String s : results) {  
                    System.out.println(Thread.currentThread() + "==products: "+s);
                }  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    }  
  
    static class Consumer extends Thread {  
        private Exchanger<List<String>> exchanger;  
  
        /**  
         *   
         */  
        public Consumer(String threadName, Exchanger<List<String>> exchanger) {  
            super(threadName);  
            this.exchanger = exchanger;  
        }  
  
        /* 
         * (non-Javadoc) 
         *  
         * @see java.lang.Thread#run() 
         */  
        @Override  
        public void run() {  
            List<String> products = new ArrayList<String>();  
            for (int i = 0; i < 10; i++) {  
                products.add("consumed " + i);  
            }  
            try {  
                List<String> results = exchanger.exchange(products);  
                System.out.println("got products from produces");  
                for (String s : results) {  
                    System.out.println(Thread.currentThread() + "==consumer: "+ s);
                }  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    }  
}  