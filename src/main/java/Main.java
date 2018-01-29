import wang.mh.common.Son;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * Created by 明辉 on 2017/7/30.
 */
public class Main {


    private static String s = "hello";
    private static Integer i1 = 100;
    private static Integer i2 = 1000;
    private static int i3 =  1000;
    private static Son son =  new Son();

    public static void main(String[] args) throws InterruptedException {

    }


    private static void test(Integer i1, Integer i2, int i3, String s, Son son) {
        System.out.println(Main.i1 == ++i1);
        System.out.println(Main.i2 == ++i2);
        System.out.println(Main.i3 == ++i3);
        System.out.println(Main.s == s.trim());
        System.out.println(Main.son == son);
    }

}


