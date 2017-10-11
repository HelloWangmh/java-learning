package wang.mh.thread.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        testSingleThreadPool();

    }

    /**
     *只有一个线程的线程池
     */
    public static void testSingleThreadPool(){
        ExecutorService service = Executors.newSingleThreadExecutor();
        commonService(service);
    }



    /**
     * 固定数量的线程  空闲线程一直保留
     */
    public static void testFixThreadPool(){
        ExecutorService service = Executors.newFixedThreadPool(5);

        commonService(service);
    }


    /**
     * 必要时创建新的线程   空闲线程会保留60秒
     */
    public static void testCachedThreadPool(){
        ExecutorService service = Executors.newCachedThreadPool();
        commonService(service);
    }


    private static void commonService(ExecutorService service){
        for (int i = 0; i < 10; i++) {
            service.submit(() -> {
                System.out.printf("%s开始工作了%n",Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("%s结束工作了%n",Thread.currentThread().getName());

            });
        }

        while (true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("活动的线程数:"+Thread.activeCount());

        }
    }
}
