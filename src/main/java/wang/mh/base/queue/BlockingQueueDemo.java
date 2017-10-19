package wang.mh.base.queue;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by 明辉 on 2017/7/2.
 * BlockingQueue是一个接口
 *有多个实现:
 * ArrayBlockingQueue   先进先出   基于数组,创建时候需要设定上限.
 * DelayQueue,LinkedBlockingQueue,PriorityBlockingQueue,SynchronousQueue
 *
 *      抛异常	    特定值	        阻塞	            超时
 * 插入	add(o)	    offer(o)	    put(o)	    offer(o, timeout, timeunit)
 * 移除	remove(o)	poll(o)	        take(o) 	poll(timeout, timeunit)
 * 检查	element(o)	peek(o)
 *
 *
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        testMethod();
    }


    /**
     * 测试常用方法
     */
    private static void testMethod() throws InterruptedException {

        BlockingQueue queue = new ArrayBlockingQueue(2);

        //add  满了抛异常
//        System.out.println( queue.add(1));
//        System.out.println( queue.add(2));
//        System.out.println( queue.add(3));
        //offer     返回true/false
//        System.out.println( queue.offer(1));
//        System.out.println( queue.offer(2));
//        System.out.println( queue.offer(3));

        //put  添加失败则阻塞当前线程
//        queue.put(1);
//        queue.put(2);
//        queue.put(3);

        //添加失败阻塞当前线程2秒
        System.out.println( queue.offer(1,2,TimeUnit.SECONDS));
//        System.out.println( queue.offer(2,2,TimeUnit.SECONDS));
//         System.out.println( queue.offer(3,2,TimeUnit.SECONDS));


        //remove没有元素就抛异常
        //System.out.println(queue.remove());

        //poll  无值返回null
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());

        //take  无值阻塞当前线程
//        System.out.println(queue.take());
//        System.out.println(queue.take());

        //无值  阻塞两秒
//        System.out.println(queue.poll(2,TimeUnit.SECONDS));
//        System.out.println(queue.poll(2,TimeUnit.SECONDS));

        //都是检测值  有值都是返回值   无值peek返回null   element抛异常
        System.out.println(queue.peek());
        System.out.println(queue.element());

        System.out.println("队列元素数量:"+queue.size());
    }






    /**
     * 生产者消费者demo
     * @throws InterruptedException
     */
    public static void productConDemo() throws InterruptedException{
        // 声明一个容量为10的缓存队列
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);

        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        // 借助Executors
        ExecutorService service = Executors.newCachedThreadPool();
        // 启动线程
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer);

        // 执行10s
        Thread.sleep(10 * 1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();

        Thread.sleep(2000);
        // 退出Executor
        service.shutdown();
    }


    @Test
    public void testUnique(){
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(10);
        queue.add(1);
        queue.add(1);
        System.out.println(queue.size());//2
    }
}
