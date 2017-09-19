package wang.mh.thread.queue;

import lombok.Data;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 *
 */
public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayObj> queue = new DelayQueue<>();

        Stream.of(10,20,-30).forEach(i ->{
            DelayObj obj = new DelayObj(i);
            queue.put(obj);
        });

        System.out.println("数量"+queue.size());
        System.out.println(queue.take());
        Thread.sleep(3000);

        System.out.println(queue.remove());

        //Thread.sleep(3000);
        //System.out.println(queue.take());
        System.out.println("数量"+queue.size());
    }
}
@Data
class DelayObj implements Delayed{


    private int value;

    public DelayObj(int value) {
        this.value = value;
    }

    @Override
    public long getDelay(TimeUnit unit) {

      return unit.convert(value,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));

    }
}
