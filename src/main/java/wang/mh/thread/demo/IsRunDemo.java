package wang.mh.thread.demo;

/**
 * 多线程环境下防止重复执行  比如定时任务
 */
public class IsRunDemo {


    public static void main(String[] args) {
        IsRunTest isRunTest = new IsRunTest();
        for(int i = 0;i<20;i++){
            new Thread(()->{
                isRunTest.task();
            }).start();
        }
    }



}
class IsRunTest{
    private boolean isRun = false;

    public void task(){
        try {
            long start = System.currentTimeMillis();
            synchronized (this){
               if(isRun){
                   System.out.println("running"+",耗时:"+(System.currentTimeMillis()-start));
                   return;
               }
               //假装在延迟
               Thread.currentThread().sleep(500);
               isRun = true;
           }

            //模仿业务逻辑
            Thread.currentThread().sleep(2000);
            System.out.println("执行一次"+isRun+",耗时:"+(System.currentTimeMillis()-start));
        } catch (InterruptedException e) {

        }finally {
            isRun = false;
        }
    }
}
