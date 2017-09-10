package wang.mh.java8;

import wang.mh.common.Father;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by 明辉 on 2017/9/10.
 */
public class ComparatorDemo {
    public static void main(String[] args) {
        Father[] arr = new Father[]{new Father("wang",4),
        new Father("ming",2),new Father("huihui",3)};

      /*  Arrays.sort(arr, Comparator.comparing(Father::getAge).
        thenComparing(Father::getName));*/

      Arrays.sort(arr,Comparator.comparing(Father::getName,
              (String::compareToIgnoreCase)));


      Arrays.sort(arr,Comparator.comparing(Father::getName,
              Comparator.comparingInt(String::length)));


        for (Father father : arr) {
            System.out.println(father);
        }

    }
}



