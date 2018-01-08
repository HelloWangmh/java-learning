package wang.mh.thread.artOfConcurrency;

import java.util.concurrent.TimeUnit;

public class Profiler {

    private static ThreadLocal<Long> local = ThreadLocal.withInitial(System::currentTimeMillis);
    public static void main(String[] args) {
        Profiler.begin();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Profiler.end());
    }

    public static final void begin(){
        local.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - local.get();
    }
}
