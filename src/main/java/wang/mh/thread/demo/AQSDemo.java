package wang.mh.thread.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

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

            @Override
            protected boolean tryAcquire(int arg) {
                if (compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
                return false;
            }


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
