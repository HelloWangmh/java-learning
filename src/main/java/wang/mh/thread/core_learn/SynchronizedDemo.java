package wang.mh.thread.core_learn;

/**
 * 对象锁
 * 分为类锁和实例锁  两个互不影响
 */
public class SynchronizedDemo {

    public static void main(String[] args) throws InterruptedException {

        SynTest synTest = new SynTest();
       new Thread(SynTest::sysClassMethod).start();
       Thread.sleep(1000);
        new Thread(synTest::sysInstanceMethod).start();

    }
}

class SynTest{


    /**
     *类锁
     */
    public synchronized static void sysClassMethod(){
        System.out.println("进入类锁方法了");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("出去类锁方法了");
    }


    /**
     * 实例锁
     */
    public synchronized  void sysInstanceMethod(){
        System.out.println("进入实例锁方法了");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("出去实例锁方法了");
    }

}
