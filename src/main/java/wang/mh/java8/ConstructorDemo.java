package wang.mh.java8;

import com.google.common.collect.Lists;
import wang.mh.common.Father;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 明辉 on 2017/9/7.
 */
public class ConstructorDemo {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        Stream<Father> stream = list.stream().map(Father::new);
       /* List<Father> fathers = stream_core_java.collect(Collectors.toList());
        fathers.forEach((f)-> System.out.println(f.getAge()));*/
        Father[] fathers1 = stream.toArray(Father[]::new);
        for (Father father : fathers1) {
            System.out.println(father.getAge());
        }



        Stream<int[]> stream1 = list.stream().map(int[]::new);
        List<int[]> collect = stream1.collect(Collectors.toList());
        for (int[] ints : collect) {
            System.out.println(ints.length);
        }
    }

}


