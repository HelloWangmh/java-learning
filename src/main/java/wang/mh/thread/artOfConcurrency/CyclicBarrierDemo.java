package wang.mh.thread.artOfConcurrency;

import org.eclipse.jetty.util.thread.ExecutorThreadPool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier可以让n线程到达一个屏障时阻塞,直到最后一个线程到达.
 *
 */
public class CyclicBarrierDemo {
    //所有线程解除阻塞前先运行 EndThread
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new EndThread());
    static ExecutorThreadPool pool = new ExecutorThreadPool(5);

    public static void main(String[] args) throws InterruptedException {
        rightUse();

    }

    public static void rightUse(){
        for (int i = 0; i < 2; i++) {
            final int num = i;
            pool.execute(() -> {
                try {
                    cyclicBarrier.await();
                    System.out.println(num);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

    }


    public static void wrongUse(){
        pool.execute(() -> {
            try {
                //调用三次不可以  必须是三个线程
                cyclicBarrier.await();
                cyclicBarrier.await();
                cyclicBarrier.await();
                System.out.println("1");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
    }

    static class EndThread implements Runnable {
        @Override
        public void run() {
            System.out.println("end");
        }
    }
}
