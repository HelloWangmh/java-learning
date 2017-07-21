package wang.mh.thread.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by 明辉 on 2017/7/20.
 */
public class SetDemo {

    public static void main(String[] args) throws InterruptedException {
        //Set<Integer> set = Sets.newConcurrentHashSet();
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0 ;i<10;i++){
            new Thread(new Runnable() {
                @Override
                  public void run() {
                        if(list.isEmpty()){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            list.add(1);
                            list.add(2);
                            list.add(3);
                            list.add(4);
                        }


                }
            }).start();
        }
        Thread.sleep(5000);
        System.out.println(list.size());
    }
}

