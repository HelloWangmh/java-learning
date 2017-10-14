package wang.mh.thread.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * Fork-Join框架
 * 实现RecursiveTask<>T(生成一个T结果) 或 RecursiveAction  进行递归计算
 * 进行耗时操作才能看出来效果
 */
public class ForkJoinDemo {

    public static void main(String[] args) {
        final int SIZE = 100000;
        double[] numbers = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = Math.random();
        }
        long start = System.currentTimeMillis();
        Counter counter = new Counter(numbers,0,SIZE,x -> x>0.5);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start)); //16809
        //System.out.println(singleThread(numbers,x -> x>0.5));//67147




    }


    public static int singleThread(double[] values,DoublePredicate predicate){
        long start = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < values.length; i++) {
            if(predicate.test(values[i])){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            };
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
        return count;
    }
}

class Counter extends RecursiveTask<Integer>{


    private static  final int THRESHOLD = 10000;
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate predicate;


    public Counter(double[] values, int from, int to, DoublePredicate predicate) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.predicate = predicate;
    }

    @Override
    protected Integer compute() {

        if(to - from < THRESHOLD){
            int count = 0;
            for (int i = from; i < to; i++) {
                if(predicate.test(values[i])) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                };
            }
            return count;
        }else {
            int mid = (from + to)/2;
            Counter first = new Counter(values,from,mid,predicate);
            Counter second = new Counter(values,mid,to,predicate);
            invokeAll(first,second);
            return first.join() + second.join();

        }
    }


}
