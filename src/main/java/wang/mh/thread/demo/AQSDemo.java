package wang.mh.thread.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * AQS是一个用来构建锁和同步器的框架，使用AQS能简单且高效地构造出应用广泛的大量的同步器
 * 只需要继承 AbstractQueuedSynchronizer,重写相应的方法,便可以创建同步组件了
 */
public class AQSDemo {


    private static int count = 0;
    private static Mutex mutex = new Mutex();

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increment();
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println("count : " + count);
    }

    private static void increment() {
        mutex.lock();
        count++;
        mutex.unlock();
    }

    static class Mutex {

        private Sync sync = new Sync();

        public void lock() {
             sync.acquire(1);
        }

        public void unlock() {
             sync.release(1);
        }


        private  class Sync extends AbstractQueuedSynchronizer {

            //是否被占用
            @Override
            protected boolean isHeldExclusively() {
                return getState() == 1;
            }

            //当state为0时,获取锁,并通过CAS方式将state更改为1
            @Override
            protected boolean tryAcquire(int arg) {
                if (compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
                return false;
            }

            //释放锁,将state更改为0
            @Override
            protected boolean tryRelease(int arg) {
                if (getState() == 0) {
                    throw new IllegalMonitorStateException();
                }
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }
        }
    }
}
