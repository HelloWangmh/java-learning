package wang.mh.thread.artOfConcurrency;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * volatile : 1.保证此变量的可见性,修改后立即同步到主内存,使用前立即从主内存刷新
 *      2.禁止指令重排序优化
 */
public class VolatileDemo {

    private static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            num++;
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(num);
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(num);
        }).start();
    }

    public static void visibility() throws InterruptedException {
       List<Thread> list = Lists.newArrayList();
        VolatileFeature volatileFeature = new VolatileFeature();
        for (int i = 0; i < 5; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    volatileFeature.getAndIncrement();
                }
            }));
        }


        for (Thread thread : list) {
            thread.start();
        }
        for (Thread thread : list) {
            thread.join();
        }

        //list.stream().peek(Thread::start).count();   error
        System.out.println(volatileFeature.get());
    }
}

/**
 *
 */
class VolatileFeature{

    private long v = 0L; //等价将v声明为volatile,多线程环境下还是存在线程不安全

    public  synchronized void set(long value){
        this.v = value;
    }

    public  synchronized long get(){
        return v;
    }

    public   void getAndIncrement(){
        long temp = get();
        temp += 1;
        set(temp);
    }
}
