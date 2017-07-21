package wang.mh.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 明辉 on 2017/7/2.
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.shutdownNow();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true){
                    System.out.println("running");
                }
            }
        });
        Thread.currentThread().sleep(1000);

    }
}
