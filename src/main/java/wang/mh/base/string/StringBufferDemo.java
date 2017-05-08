package wang.mh.base.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 明辉 on 2017/5/8.
 */
public class StringBufferDemo {


    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        final StringBuffer sb = new StringBuffer();
        final List<Integer> list = new ArrayList<Integer>();
        new Thread(new Runnable() {
            public void run() {
                System.out.println("开始执行");
                for(int i = 0;i<100;i++){
                    sb.append("11111");
                    if(i==(100-1)){
                        list.add(1);
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("开始执行");
                for(int i = 0;i<100;i++){
                    sb.append("22222");
                    if(i==(100-1)){
                        list.add(1);
                    }
                }

            }
        }).start();
        Thread.currentThread().setPriority(1);
        while (true){
            if(list.size()==2){
                long end1 = System.currentTimeMillis();
                System.out.println("StringBUffer耗时:"+(end1-start1));//12
                System.out.println(sb.toString());
                break;
            }
        }




    }
}
