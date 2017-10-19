package wang.mh.base.queue;

import java.time.LocalDate;
import java.util.PriorityQueue;

/**
 * 优先级队列
 * 保存实现了Comparable接口的对象或保存在构造器中提供的Comparator对象
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {

        PriorityQueue<LocalDate> queue = new PriorityQueue<>();

        queue.add(LocalDate.of(2008,8,9));
        queue.add(LocalDate.of(2007,8,9));
        queue.add(LocalDate.of(2009,8,9));
        queue.add(LocalDate.of(210,8,9));
        queue.add(LocalDate.of(210,8,9));

        for (LocalDate localDate : queue) {
            System.out.println(localDate);
        }

        System.out.println("remove:"+queue.remove());
    }
}
