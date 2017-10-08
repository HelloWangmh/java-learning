package wang.mh.thread.core_learn;

import wang.mh.common.Constant;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 两个锁:ReentrantLock  and  ReentrantReadWriteLock
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
        testWriteThenRead();
    }


    /**
     * 先进行多个写操作然后进行读操作  写,读都会堵塞
     * 结论:写锁会排斥所有读操作和写操作
     */
    private static void  testWriteThenRead(){
        ReadWriteService readWriteService = new ReadWriteService();
        //写锁
        for (int i = 0; i < 3; i++) {
            new Thread(() -> readWriteService.setMsg("heiheihei")).start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //读锁
        for (int i = 0; i < 3; i++) {
            new Thread(() -> System.out.println(readWriteService.getMsg())).start();
        }

    }

    /**
     * 先进行多个读操作,然后写操作,写操作会阻塞,读操作是并行的
     * 结论:读锁只会排斥所有写锁
     */
    private static void  testReadThenWrite(){
        ReadWriteService readWriteService = new ReadWriteService();
        //读锁
        for (int i = 0; i < 3; i++) {
            new Thread(() -> System.out.println(readWriteService.getMsg())).start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //写锁
        new Thread(() ->{
            readWriteService.setMsg("heiheihei");}).start();

    }
}

/**
 * 读写锁测试服务
 */
class ReadWriteService{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = null;
    private ReentrantReadWriteLock.WriteLock writeLock = null;
    private String msg = "hello,world";

    public ReadWriteService(){
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }


    public String getMsg(){
        System.out.println(Thread.currentThread().getName()+"进入getMsg了  "+ Constant.curTime());
        try {
            readLock.lock();
            System.out.println("getMsg start        "+Constant.curTime());
            Thread.sleep(1500);
            return msg;
        }catch (InterruptedException e){
            e.printStackTrace();
            return null;
        }finally {
            System.out.println("getMsg end          "+Constant.curTime());
            readLock.unlock();
        }
    }

    public void setMsg(String msg) {
        System.out.println(Thread.currentThread().getName()+"进入setMsg了  "+Constant.curTime());
        try {
            writeLock.lock();
            System.out.println("setMsg start   "+Constant.curTime());
            Thread.sleep(5000);
            this.msg = msg;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            System.out.println("setMsg end     "+Constant.curTime());
            writeLock.unlock();
        }
    }
}
