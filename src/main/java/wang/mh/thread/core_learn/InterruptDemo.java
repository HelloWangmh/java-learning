package wang.mh.thread.core_learn;

public class InterruptDemo {
    public static void main(String[] args) {
        testInterruptException();
    }


    /**
     * 线程中断状态下调用wait()  sleep()会throw  InterruptedException 并解除线程的中断状态
     */
    private static void testInterruptException(){


        try {
            Thread.currentThread().interrupt();
            System.out.println("线程是否中断:"+Thread.currentThread().isInterrupted()); //true
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("线程是否中断:"+Thread.currentThread().isInterrupted()); //false
            e.printStackTrace();
        }
    }




    /**
     * 检测是否中断
     * isInterrupted()   实例方法,只是检测
     */
    private static void testInterrupt(){
        Thread.currentThread().interrupt();
        System.out.println("是否中断:"+Thread.currentThread().isInterrupted());//true
        System.out.println("是否中断:"+Thread.currentThread().isInterrupted()); //true

    }



    /**
     * 检测是否中断
     * interrupted()   静态方法,检测当前线程是否中断,并且清楚中断状态
     */
    private static void testIsInterrupt(){
        Thread.currentThread().interrupt();
        System.out.println("是否中断:"+Thread.interrupted());//true
        System.out.println("是否中断:"+Thread.currentThread().isInterrupted());//false

    }
}
