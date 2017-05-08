package wang.mh.base.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 明辉 on 2017/5/8.
 * 多线程环境测试StringBuilder 和   StringBuffer 性能
 *
 * StringBuilder执行更快
 */
public class StringBuilderDemo {

    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        final StringBuilder sb = new StringBuilder();
        final List<Integer> list = new ArrayList<Integer>();
        new Thread(new Runnable() {
            public void run() {
                System.out.println("开始执行");
                for(int i = 0;i<1000;i++){
                    sb.append("11111");
                    if(i==(1000-1)){
                        list.add(1);
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("开始执行");
                for(int i = 0;i<1000;i++){
                    sb.append("22222");
                    if(i==(1000-1)){
                        list.add(1);
                    }
                }

            }
        }).start();
        Thread.currentThread().setPriority(1);
        while (true){
            if(list.size()==2){
                long end1 = System.currentTimeMillis();
                System.out.println("StringBuilder耗时:"+(end1-start1));//12
                System.out.println(sb.toString());
                break;
            }
        }




    }
}


