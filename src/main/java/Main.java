import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by 明辉 on 2017/7/30.
 * 14206
 * 1506  9685
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Thread t = Thread.currentThread();
        t.interrupt();
        t.interrupt();
        System.out.println(t.isInterrupted());


    }

}

interface Per {
    static void xx() {
        System.out.println("hello");
    }
}



