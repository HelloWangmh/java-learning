package wang.mh.thread.example;

import com.google.common.collect.Lists;
import wang.mh.thread.demo.thread_pool.ThreadPool;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadLocalOOM {


    // -Xmx=100M
    public static void main(String[] args) throws Exception{
        ThreadLocalOOM oom = new ThreadLocalOOM();
        oom.run();
    }

    public void run() throws Exception{
        int count = 50;
        CountDownLatch latch = new CountDownLatch(count);
        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i < count; i++) {
            es.submit(() -> {
                ThreadLocal<byte[]> threadLocal = new ThreadLocal<>();
                threadLocal.set(ThreadLocalOOM.this.getBuffer());
                threadLocal.remove();//这里必须remove，因为threadLocalMap是绑定在thread上的，如果不remove，会oom
                latch.countDown();
            });
        }
        latch.await();
        es.shutdown();
    }

    public byte[] getBuffer() {
        return new byte[10 * 1024 * 1024];
    }
}
