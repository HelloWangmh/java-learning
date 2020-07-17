package wang.mh.interview;

public class WaitNotifyDemo {

    private static Object obj = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (obj) {
                while (true) {
                    System.out.println("thread 1");
                }
            }
        }).start();
    }
}
