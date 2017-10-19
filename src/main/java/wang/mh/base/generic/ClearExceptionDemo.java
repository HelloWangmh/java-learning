package wang.mh.base.generic;

import java.io.File;
import java.util.Scanner;

public class ClearExceptionDemo {

    public static void main(String[] args) {
        new Block(){
            @Override
            public void body() throws Exception {
                //正常来说以下代码要抛出受查异常
                File f = new File("/test.properties");

                Scanner sc = new Scanner(f);
                while (sc.hasNext()){
                    System.out.println(sc.next());
                }
            }
        }.toThread().start();
    }
}

abstract class Block{
    public abstract void body() throws Exception;

    public Thread toThread(){
        return new Thread(()->{
            try {
                body();
            }catch (Throwable t){
                Block.throwAs(t);
            }
        });
    }
    @SuppressWarnings("unchecked")
    public static <T extends Throwable> void throwAs(Throwable t) throws T {
        throw (T)t;
    }
}
