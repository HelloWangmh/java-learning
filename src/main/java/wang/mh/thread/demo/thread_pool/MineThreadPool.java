package wang.mh.thread.demo.thread_pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程池实现
 * 原理 : 创建一定数量的线程池,从队列中不断读取job(wait/notify机制)
 * @param <Job>
 */
public class MineThreadPool<Job extends Runnable> implements ThreadPool<Job> {


    // 线程池最大限制数
    private static final int MAX_WORKER_NUMBERS = 10;
    // 线程池默认的数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    // 线程池最小的数量
    private static final int MIN_WORKER_NUMBERS = 1;
    // 这是一个工作列表，将会向里面插入工作
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    // 工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    // 工作者线程的数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    // 线程编号生成
    private AtomicLong threadNum = new AtomicLong();



    public MineThreadPool() {
        initWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public MineThreadPool(int num) {
        num = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS :
                num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
        initWorkers(num);
        this.workerNum = num;
    }

    @Override
    public void execute(Job job) {
        synchronized (jobs) {
            jobs.add(job);
            jobs.notify();
        }
    }

    @Override
    public void shutdown() {
        workers.forEach(Worker::shutDown);
    }

    @Override
    public void addWorkers(int num) {
        if ((num + this.workerNum) > MAX_WORKER_NUMBERS) {
            num = MAX_WORKER_NUMBERS - this.workerNum;
        }
        initWorkers(num);
        this.workerNum += num;
    }

    @Override
    public void removeWorker(int num) {
        if (num > this.workerNum) {
            throw new IllegalArgumentException("wrong num");
        }
        synchronized (jobs) {
            for (int i = 0; i < num; i++) {
                Worker worker = workers.get(i);
                if (workers.remove(worker)) {
                    worker.shutDown();
                }
            }
            this.workerNum -= num;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }


    private void initWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "workerThread" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    /**
     * 从job队列中取任务执行
     */
    class Worker implements Runnable {

        private volatile boolean isRun = true;

        @Override
        public void run() {
            synchronized (jobs) {
               while (isRun) {
                   if (jobs.isEmpty()) {
                       try {
                           jobs.wait();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   } else {
                       Job job = jobs.removeFirst();
                       if (job != null) {
                           job.run();
                       }
                   }
               }
            }
        }

        public void shutDown(){
            isRun = false;
        }
    }
}
