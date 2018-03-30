package wang.mh.thread.demo;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class CASCounter {

    private int i = 0;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        CASCounter counter = new CASCounter();
        ArrayList<Thread> list = Lists.newArrayListWithCapacity(1000);
        for (int i = 0; i < 1000; i++) {
            list.add(new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    counter.count();
                    counter.countCas();
                }
            }));
        }
        list.forEach(Thread::start);
        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("i ==>> " + counter.i);
        System.out.println("atomicInteger ==>> " + counter.atomicInteger);

    }

    public void count(){
        i++;
    }

    public void countCas(){
        atomicInteger.incrementAndGet();
    }
}
