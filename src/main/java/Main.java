
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import wang.mh.common.Son;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by 明辉 on 2017/7/30.
 */
public class Main {
    public static void main(String[] args) {

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


