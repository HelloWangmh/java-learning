package wang.mh.thread.example;


import java.util.Random;

public class ThreadLocalExample implements Runnable{

    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "11111");

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new ThreadLocalExample(), "name" + i);
            thread.start();
        }
    }

    @Override
    public void run() {

        System.out.println("thread name : " + Thread.currentThread().getName() + ", value : " + threadLocal.get());

        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadLocal.set("222");

        System.out.println("thread name : " + Thread.currentThread().getName() + ", value : " + threadLocal.get());
    }

}
