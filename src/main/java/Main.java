
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Date;


/**
 * Created by 明辉 on 2017/7/30.
 */
public class Main {
    public static void main(String[] args) {
       final String name = "hello,world";
        ArrayList<Integer> list = Lists.newArrayList();
        Runnable t1 = new Thread(() -> System.out.println(list));
        list.add(1);
    }




}


