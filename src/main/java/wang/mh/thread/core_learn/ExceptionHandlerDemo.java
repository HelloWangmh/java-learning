package wang.mh.thread.core_learn;

/**
 * 线程的run方法不能抛出任何受查异常
 * 非受查异常如果不被自己的catch语句捕获,那么会调用未捕获异常处理器来处理
 * 未捕获异常处理器默认是ThreadGroup对象,或自定义的实现了Thread.UncaughtExceptionHandler的类实例
 */
public class ExceptionHandlerDemo {

    public static void main(String[] args) {
        testThrow();
    }




    private  static void testThrow(){
        Thread thread = new Thread(new RunException());
        System.out.println(thread.getUncaughtExceptionHandler());
        //设置未捕获异常处理器
        thread.setUncaughtExceptionHandler(new MyExceptionHandler());
        System.out.println(thread.getUncaughtExceptionHandler());

        thread.start();
    }
}
class RunException implements Runnable{

    @Override
    public void run() {
     throw new RuntimeException();
    }
}

/**
 * 自定义的线程处理器
 */
class MyExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("enter MyExceptionHandler");
    }
}

