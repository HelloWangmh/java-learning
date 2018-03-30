package wang.mh.thread.artOfConcurrency;

import java.util.concurrent.TimeUnit;

public class Interrupted {
    public static void main(String[] args) {
        Thread sleepThread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "SleepThread");

        Thread busyTHread = new Thread(() -> {while (true) {}}, "BusyThread");
        sleepThread.setDaemon(true);
        busyTHread.setDaemon(true);
        sleepThread.start();
        busyTHread.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sleepThread.interrupt();
        busyTHread.interrupt();


        System.out.println("sleepThread : " + sleepThread.isInterrupted());//false  因为该thread已经停止了
        System.out.println("busyTHread : " + busyTHread.isInterrupted());//true


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
