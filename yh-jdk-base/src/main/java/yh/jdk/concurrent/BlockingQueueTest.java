package yh.jdk.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author
 * @create 2017-09-28 15:17
 **/
public class BlockingQueueTest {

    private BlockingQueue blockingQueue  = new LinkedBlockingQueue();

    AtomicInteger ao = new AtomicInteger();

    public void produce(){
        String key  = "produce"+ao.incrementAndGet();
        try {
            blockingQueue.put(key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String consume(){
        try {
            return (String) blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }return  null;
    }

    // 定义苹果生产者
    class Producer implements Runnable {
        private String instance;
        private BlockingQueueTest basket;

        public Producer(String instance, BlockingQueueTest basket) {
            this.instance = instance;
            this.basket = basket;
        }

        public void run() {
            try {
                while (true) {
                    // 生产苹果
                    System.out.println(Thread.currentThread().getName()+"生产者准备生产苹果：" + instance+"==== "+ basket.blockingQueue.size());
                    basket.produce();
                    System.out.println(Thread.currentThread().getName()+"!生产者生产苹果完毕：" + instance+"==== "+ basket.blockingQueue.size());
                    // 休眠300ms
                    Thread.sleep(30);
                }
            } catch (InterruptedException ex) {
                System.out.println("Producer Interrupted");
            }
        }
    }

    // 定义苹果消费者
    class Consumer implements Runnable {
        private String instance;
        private BlockingQueueTest basket;

        public Consumer(String instance, BlockingQueueTest basket) {
            this.instance = instance;
            this.basket = basket;
        }

        public void run() {
            try {
                while (true) {
                    // 消费苹果
                    System.out.println(Thread.currentThread().getName()+"消费者准备消费苹果：" + instance+"==== "+ basket.blockingQueue.size());
                    System.out.println(basket.consume());
                    System.out.println(Thread.currentThread().getName()+"!消费者消费苹果完毕：" + instance+"==== "+ basket.blockingQueue.size());
                    // 休眠1000ms
                    Thread.sleep(300);
                }
            } catch (InterruptedException ex) {
                System.out.println("Consumer Interrupted");
            }
        }
    }


    public static void main(String[] args){
        BlockingQueueTest bl= new BlockingQueueTest();
        ExecutorService service = Executors.newFixedThreadPool(15);
        Producer producer = bl.new Producer("生产者001", bl);
        Producer producer2 = bl.new Producer("生产者002", bl);
        Consumer consumer = bl.new Consumer("消费者001", bl);
        service.submit(producer);
        service.submit(producer2);
        service.submit(consumer);
    }
}
