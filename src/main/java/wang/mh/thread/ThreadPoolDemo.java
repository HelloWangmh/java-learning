package wang.mh.thread;


import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by 明辉 on 2017/7/2.
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 100, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 15; i++) {

            int num = i;
            executor.execute(() -> {
                System.out.println("thread " + num + " start");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread " + num + " end");
            });
        }

        System.out.println("end");

    }
}
