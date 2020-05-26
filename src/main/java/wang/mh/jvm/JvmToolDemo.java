package wang.mh.jvm;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 1.jps : 查看正在执行的虚拟机进程
 *          -l : 输出主类名   -m : 输出虚拟机启动时传给main函数的参数   -v : 输出jvm启动参数
 * 2.jstat : jvm统计信息监控数据,用于显示jvm类装载,内存,垃圾回收,JIT编译等信息
 *         格式 : jstat option pid  s|ms count
 * 3.jinfo : 查看和调整jvm参数
 *
 * 4.jmap : java内存影像工具,可用户生成堆存储快照,查看堆详细信息,堆中对象统计信息
 *          jmap -option pid
 */
public class JvmToolDemo {

    private final static int ONE_KB= 1024 ;
    private static ExecutorService service = Executors.newFixedThreadPool(5);;

    public static void main(String[] args) {
        checkCpu();
    }

    public int add(int a, int b) {
        return a + b;
    }


    public static void checkCpu() {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        new Thread(() -> {
            int i = 0;
            while (true) {
                i++;
                i--;
            }
        }, "busy").start();
        new Thread(() -> {
            int i = 0;
            while (true) {
                i++;
                i--;
            }
        }, "busy2").start();
    }

    public static void testDeadLock() {
        Object lockA = new Object();
        Object lockB = new Object();

        service.execute(() -> {
            synchronized (lockA) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("thread-1  get  lockA");
                    synchronized (lockB) {
                        System.out.println("thread-1  get  lockB");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        service.execute(() -> {
            synchronized (lockB) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("thread-2  get  lockB");
                    synchronized (lockA) {
                        System.out.println("thread-2  get  lockA");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    /**
     * 使用Jconsole的线程页签查看线程状态
     */
    public static void testThread() {

        Object lock = new Object();
        //死循环
        service.execute(() -> {
            while (true) {
                int i = 0;
                i++;
                i--;
            }
        });

        //线程锁
        service.execute(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void testMemory() {
        List<byte[]> list = Lists.newArrayList();

        for (int i = 0; i < 10000; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new byte[ONE_KB]);
        }
    }
}
