package wang.mh.thread.collection;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by 明辉 on 2017/7/29.
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {

    }



    /**
     * 自增操作
     * 线程不安全
     * 若换成AtomicInteger类那么结果是争正确的,但是不保证输出顺序
     */
    private static void testValueIncrementMap(){
        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
        int num = 30;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        map.put("data",1);
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                 int newVal = map.get("data")+1;
                 System.out.printf("new value :%d%n",newVal);
                 try {
                     Thread.sleep((long)(Math.random() * 1000));
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 map.put("data",newVal);
                 countDownLatch.countDown();

            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(map.get("data"));
    }


    /**
     * 自增操作
     * 通过while循环配合replace方法,这样可以保证结果正确,但是顺序不能保证
     * 若是HashMap,那么结果也可能错误,因为HashMap内部线程不安全
     */
    private static void testValueIncrementMapWithReplace(){
        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
        int num = 30;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        map.put("data",1);
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                int oldVal,newVal;
                do{
                     oldVal = map.get("data");
                     newVal = oldVal +1;
                }while (!map.replace("data",oldVal,newVal));
                System.out.printf("new value :%d%n",newVal);

                map.put("data",newVal);
                countDownLatch.countDown();

            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(map.get("data"));
    }


    /**
     * 自增操作
     * java8的LongAdder   原子操作
     */
    private static void testValueIncrementMapWithLongAdder(){
        ConcurrentHashMap<String,LongAdder> map = new ConcurrentHashMap<>();
        int num = 30;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        map.put("data",new LongAdder());
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
               map.get("data").increment();
                countDownLatch.countDown();

            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(map.get("data"));
    }


    /**
     * java8提供的原子更新方法
     */

    public static void testMapWithJava8(){
        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();

        //如果没有对应的值,那么将2put进去,否则更改
        map.merge("data",2,(oldVal,newVal) ->{
            return oldVal / newVal;
        });

        System.out.println(map.get("data"));


        //计算并更改成新的值
        map.compute("data",(k,v) -> {
            return ++v;
        });
        System.out.println(map.get("data"));
    }


    private static long  threshold = 10000L;


    /**
     * ConcurrentHashMap的refuce
     */
    public static void testReduce(){
        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
        map.put("111",1);
        map.put("222",2);
        map.put("333",3);

        Integer result = map.reduceKeys(threshold, String::length, Integer::sum);
        System.out.println(result);

    }


    /**
     * ConcurrentHashMap的两种forEach
     */
    public static void testForEach(){
        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
        map.put("111",1);
        map.put("222",2);
        map.put("333",3);
        map.forEach(threshold,(k,v) -> {
            System.out.println(k+"==="+v);
        });

        map.forEach(threshold,
                (k,v) -> k+"==="+v, //Transfotmer
                System.out::println); //Consumer
    }


    /**
     * 4个searchfang方法  max表示阈值,超过这个阈值那么就会并行完成操作
     */
    public static void testSearch(){
        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
        map.put("data",10000);
        String result = map.search(threshold, (k, v) -> v > 1000 ? k : null);
        System.out.println(result);
        String keyResult = map.searchKeys(threshold, k -> k == "data" ? k+"data" : "Oo0");
        System.out.println(keyResult);
    }




}
