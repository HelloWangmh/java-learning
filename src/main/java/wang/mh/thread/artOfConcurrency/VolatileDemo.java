package wang.mh.thread.artOfConcurrency;

import com.google.common.collect.Lists;

import java.util.ArrayList;

public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> list = Lists.newArrayList();
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

    private  long v = 0L; //等价将v声明为volatile,多线程环境下还是存在线程不安全

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
