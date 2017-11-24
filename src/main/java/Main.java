
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * Created by 明辉 on 2017/7/30.
 */
public class Main {
    public static void main(String[] args) {
        HashMap<String, List<Integer>> map1 = Maps.newHashMap();
        map1.put("1", Lists.newArrayList(1,2,3));
        map1.put("2", Lists.newArrayList(1,2,3));
        HashMap<String, List<Integer>> map2 = Maps.newHashMap();
        map2.put("1", Lists.newArrayList(4,5,6));
        map2.put("3", Lists.newArrayList(1,2,3));

        map1.forEach((key, value) -> {
           map2.merge(key,value,(left, right) -> {
               right.addAll(left);
               return right;
           });
        });
        System.out.println(map2);

    }

    private static String getName(String name){
        return name;
    }
}

interface A {
    default void a(){
        System.out.println("A");
    }
}

interface B {
    default void a(){
        System.out.println("B");
    }

    static void print(){
        System.out.println("hello,world");
    }
}
class Test implements A,B {

    @Override
    public void a() {

    }

}
class SonTest extends Test {


}


