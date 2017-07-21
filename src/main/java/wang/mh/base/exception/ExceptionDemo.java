package wang.mh.base.exception;

/**
 * Created by 明辉 on 2017/5/9.
 * 用给定的原因 构造一个对象
 */
public class ExceptionDemo {

    public static void main(String[] args) {
        Exception e = new Exception("hello,world", new MyException());
        System.out.println(e.getMessage());
        System.out.println(e.getCause());
    }
}



class MyException extends Exception {
    public MyException(){
        super("MyException");
    }
}
