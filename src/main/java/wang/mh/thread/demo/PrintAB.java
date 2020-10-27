package wang.mh.thread.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintAB {

    private static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws Exception{
        ReentrantLock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();

        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    while (count.get()%2 != 0) {
                        a.await();
                    }
                    System.out.println("A");
                    count.incrementAndGet();
                    b.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "AAA").start();

        new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    while (count.get()%2 != 1) {
                        b.await();
                    }
                    System.out.println("B");
                    count.incrementAndGet();
                    a.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "BBB").start();

        TimeUnit.SECONDS.sleep(1000);
    }
}
