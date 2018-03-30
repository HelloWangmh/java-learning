package wang.mh.thread.artOfConcurrency;

import org.eclipse.jetty.util.thread.ExecutorThreadPool;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore  可以控制最大并发数
 */
public class SemaphoreDemo {

    static ExecutorThreadPool pool = new ExecutorThreadPool(5);

    public static void main(String[] args) {
        Semaphore semaphore =new Semaphore(3);
        for (int i = 0; i < 5; i++) {
            pool.execute(() -> {
                try {
                    semaphore.acquire();
                    TimeUnit.SECONDS.sleep(100);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
