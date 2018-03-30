package wang.mh.thread.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinLock implements Lock{
    /**
     * 初始状态为2  每一个线程持有锁,则减1   所以最好两个线程同时持有锁
     */
    private final Sync sync = new Sync(2);

    static final class Sync extends AbstractQueuedSynchronizer {

        public Sync(int count) {
            if (count <= 0) {
                throw  new IllegalArgumentException("must large than 0 ");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            while (true) {
                int currentState = getState();
                int newCount = currentState - reduceCount;
                if (newCount < 0 || compareAndSetState(currentState, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int reduceCount) {
            for (;;) {
                int currentState = getState();
                int newCount = currentState + reduceCount;
                if (compareAndSetState(currentState, newCount)) {
                    return true;
                }
            }
        }
    }



    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return true;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
