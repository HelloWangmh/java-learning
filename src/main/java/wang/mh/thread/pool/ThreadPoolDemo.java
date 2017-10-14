package wang.mh.thread.pool;

import com.google.common.collect.Lists;
import wang.mh.common.Son;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) throws Exception {
        testCompletion();

    }





    /**
     * 控制一组任务   invokeAny  这种情况,返回第一个完成的
     * @throws Exception
     */
    public static void testInvokeAny() throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        Integer result = service.invokeAny(getListForInvoke());
        System.out.println(result);
        service.shutdown();

    }

    /**
     * 控制一组任务   invokeAll  这种情况,只有当任务全部完成才返回
     * @throws Exception
     */
    public static void testInvokeAll() throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<Integer>> futures = service.invokeAll(getListForInvoke());
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }
        service.shutdown();

    }


    /**
     * 控制一组任务   ExecutorCompletionService管理一个任务队列,每次返回先执行完成的
     * @throws Exception
     */
    public static void testCompletion() throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(service);
        ArrayList<Callable<Integer>> list = getListForInvoke();
        for (Callable<Integer> callable : list) {
            completionService.submit(callable);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(completionService.take().get());
        }
        service.shutdown();

    }

    private static ArrayList<Callable<Integer>> getListForInvoke(){
        ArrayList<Callable<Integer>> list = Lists.newArrayList();

        for (int i = 0; i < 5; i++) {
            final int a =i;
            list.add(() -> a);
        }
        list.add(() -> {
            Thread.sleep(5000);
            return 123;
        });
        return list;
    }

    /**
     * submit()方法返回一个Future对象
     * @throws Exception
     */
    public static void testFuture() throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();

        //提交Runnable
        Future future1 = service.submit(() -> {
            System.out.println("====");
        });
        System.out.println("future1 result:"+future1.get());

        Son son = new Son(10);

        Future<Son> future2 = service.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            son.setAge(11);
        }, son);
        System.out.println("future2结束前:"+son.getAge()); //10
        //阻塞
        System.out.println("future2 result:"+future2.get().getAge());  //11


        //提交Callable
        Future<Integer> future3 = service.submit(() -> 1);
        System.out.println("future3 result:"+future3.get());

        service.shutdown();
    }


    /**
     * Schedule ThreadPool
     */

    public static void testSchedulePool(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        service.schedule(()-> System.out.println("running"),2, TimeUnit.SECONDS);

        service.scheduleAtFixedRate(()-> System.out.println("runrun every 1 second")
        ,1,2,TimeUnit.SECONDS);
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
        int n = 0;
        while (true){
            try {
                Thread.sleep(200);
                n++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("活动的线程数:"+Thread.activeCount());
            if (n==20) {
                System.out.println("停止服务");
                service.shutdown();
            };
        }
    }
}
