package thread;

import org.junit.Test;
import wang.mh.thread.demo.TwinLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class TestLock {


    @Test
    public void testTwinLock(){
        Lock lock = new TwinLock();




        class Worker implements Runnable {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println(Thread.currentThread().getName() + "   " +System.currentTimeMillis());
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Worker worker = new Worker();
            Thread thread = new Thread(worker);
            thread.setDaemon(true);
            thread.start();
        }
        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
