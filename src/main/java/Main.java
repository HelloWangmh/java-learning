import wang.mh.common.Son;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * Created by 明辉 on 2017/7/30.
 */
public class Main {


    private static String s = "hello";
    private static Integer i1 = 100;
    private static Integer i2 = 1000;
    private static int i3 =  1000;
    private static Son son =  new Son();

    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        new Thread(() -> {
            writeLock.lock();
            try {
                readLock.lock();
                System.out.println("get read lock");
                readLock.unlock();
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            writeLock.unlock();
        }).start();

        while (true) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(lock.getQueueLength());;
        }


    }


    private static void test(Integer i1, Integer i2, int i3, String s, Son son) {
        System.out.println(Main.i1 == ++i1);
        System.out.println(Main.i2 == ++i2);
        System.out.println(Main.i3 == ++i3);
        System.out.println(Main.s == s.trim());
        System.out.println(Main.son == son);
    }

}


