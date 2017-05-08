package wang.mh.base.string;

import org.junit.Test;

/**
 * Created by 明辉 on 2017/5/8.
 * 比较StringBuilder 和 +号连接字符串的性能
 * +号 内部也是通过创建StringBuilder对象来实现的
 */
public class StringDemo {

    public static void main(String[] args) {
      /*  long start = System.currentTimeMillis();
        for(int i = 0;i<Integer.MAX_VALUE;i++){
            String s1 = "abcdefg";
            String s2 = s1 + "qwertyuio"+"zxcvbnm";
        }
        long end = System.currentTimeMillis();
        System.out.println("+号耗时:"+(end-start));//29820

        long start1 = System.currentTimeMillis();
        for(int i = 0;i<Integer.MAX_VALUE;i++){
            StringBuilder sb = new StringBuilder("abcdefg");
            String s2 = sb.append("qwertyuio").append("zxcvbnm").toString();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("StringBuilder耗时:"+(end1-start1));//41946*/


       /* long start = System.currentTimeMillis();
        String s1 = "abcdefg";
        for(int i = 0;i<1000;i++){
            s1 = s1 + "qwertyuio"+"zxcvbnm";
        }
        long end = System.currentTimeMillis();
        System.out.println("+号耗时:"+(end-start));//28

        long start1 = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("abcdefg");
        for(int i = 0;i<1000;i++){
            sb.append("qwertyuio").append("zxcvbnm");
        }
        long end1 = System.currentTimeMillis();
        System.out.println("StringBuilder耗时:"+(end1-start1));//1*/



       /* long start = System.currentTimeMillis();

        for(int i = 0;i<100000;i++){
            String s1 = new String(i+"");
            s1 = s1 + "qwertyuio";
            s1 = s1 + "zxcvbnm";
        }
        long end = System.currentTimeMillis();
        System.out.println("+号耗时:"+(end-start));//23

        long start1 = System.currentTimeMillis();

        for(int i = 0;i<100000;i++){
            StringBuilder sb = new StringBuilder(i+"");
            sb.append("qwertyuio");
            sb.append("zxcvbnm");
        }
        long end1 = System.currentTimeMillis();
        System.out.println("StringBuilder耗时:"+(end1-start1));//12*/


       /* for(int i = 0;i<10000;i++){
            String s1 = new String(i+"");
            s1 = s1 + "qwertyuio";
        }
        long end = System.currentTimeMillis();
        System.out.println("+号耗时:"+(end-start));//21

        long start1 = System.currentTimeMillis();

        for(int i = 0;i<10000;i++){
            StringBuilder sb = new StringBuilder(i+"");
            sb.append("qwertyuio");
        }
        long end1 = System.currentTimeMillis();
        System.out.println("StringBuilder耗时:"+(end1-start1));//13*/







    }


}
