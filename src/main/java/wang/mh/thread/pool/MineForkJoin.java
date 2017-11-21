package wang.mh.thread.pool;

import com.google.common.collect.Lists;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.DoublePredicate;

/**
 * 通过threadPool和queue实现一个类似ForkJoin
 * //50秒
 */
public class MineForkJoin {

    private static final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws Exception {
        final int SIZE = 100000;
        double[] numbers = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = Math.random();
        }
        BlockingQueue<Callable<Integer>> queue = new ArrayBlockingQueue<>(10);
        ComputeSum computeSum = new ComputeSum(numbers,d -> d > 0.5,queue);
        long start = System.currentTimeMillis();
        new Thread(()->{
            try {
                computeSum.assignQueue(0,SIZE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        ExecutorService service = Executors.newFixedThreadPool(20);
        List<Future<Integer>> result = Lists.newCopyOnWriteArrayList();
        while (!queue.isEmpty()){
            Future<Integer> future = service.submit(queue.take());
            result.add(future);
        }
        int sum = 0;

        while (result.size() > 0) {
            for (Future<Integer> future : result) {
                if(future.isDone()){
                    sum +=future.get();
                    result.remove(future);
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(sum + ",耗时 : " + (end - start) + "ms");
        service.shutdown();
    }


}

class ComputeSum{


    private double[] values;
    private DoublePredicate predicate;
    private static  final int THRESHOLD = 10000;
    private BlockingQueue<Callable<Integer>> queue;

    public ComputeSum(double[] values, DoublePredicate predicate,BlockingQueue<Callable<Integer>> queue) {
        this.values = values;
        this.predicate = predicate;
        this.queue = queue;

    }

    public void assignQueue(int from,int to) throws InterruptedException {
        int sum = 0;
        if(from - to < THRESHOLD){
            queue.put(()->{
                int count = 0;
                for (int i = from; i < to; i++) {
                    if(predicate.test(values[i])) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        count++;
                    }
                }
                return count;
            });
        }else {
            int mid = (from+to)/2;
            assignQueue(from,mid);
            assignQueue( mid,to);
        }
    }






}
