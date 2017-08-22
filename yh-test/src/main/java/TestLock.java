import com.ecwid.consul.v1.ConsulClient;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Random;

public class TestLock {
 
    private Logger logger = Logger.getLogger(getClass());
 
    @Test
    public void testLock() throws Exception  {
        new Thread(new LockRunner(1)).start();
        new Thread(new LockRunner(2)).start();
        new Thread(new LockRunner(3)).start();
        new Thread(new LockRunner(4)).start();
        new Thread(new LockRunner(5)).start();
        Thread.sleep(200000L);
    }
  
    class LockRunner implements Runnable {
 
        private Logger logger = Logger.getLogger(getClass());
        private int flag;
 
        public LockRunner(int flag) {
            this.flag = flag;
        }
 
        @Override
        public void run() {
            Lock lock = new Lock(new ConsulClient(), "lock-session", "lock-key");
            try {
                if (lock.lock(true)) {
                    logger.info("Thread " + flag + " start!");
                    Thread.sleep(new Random().nextInt(3000));
                    logger.info("Thread " + flag + " end!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
  
}