package wang.mh.thread.demo.thread_pool;

import wang.mh.thread.demo.thread_pool.MineThreadPool;

import java.io.IOException;

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        try {
            SimpleHttpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void testThreadPool(){
        MineThreadPool pool = new MineThreadPool();
        for (int i = 0; i < 1000; i++) {
            int num = i ;
            pool.execute(() -> System.out.println((num)));
        }
    }
}
