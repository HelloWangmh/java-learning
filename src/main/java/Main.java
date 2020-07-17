import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 明辉 on 2017/7/30.
 * 14206
 * 1506  9685
 */
public class Main {
    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newSingleThreadExecutor(new MyThreadFactory());

        CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                System.out.println("thread is running");
                throw new RuntimeException("exception");
            });
            TimeUnit.SECONDS.sleep(5);
        }



        latch.await();
    }

    static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            System.out.println("enter newThread()");
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}



