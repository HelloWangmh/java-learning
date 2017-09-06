package wang.mh.base.grammer;

import wang.mh.common.Father;
import wang.mh.common.Son;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by 明辉 on 2017/9/6.
 */
public class SortDemo {
    public static void main(String[] args) {


        Son[] arr = new Son[]{new Son(1),
                new Son(2),new Son(3)};
        //T[] a, Comparator<? super T> c     通过本身或父类的比较器
        Arrays.sort(arr,new AgeComparator());
        Arrays.asList(arr).forEach( i -> System.out.println(i.getAge()));
    }
}


class AgeComparator implements Comparator<Father>{

    @Override
    public int compare(Father o1, Father o2) {
        return o1.getAge() - o2.getAge();
    }
}


