package wang.mh.guava;

import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Collection<Integer> filter = Collections2.filter(list, input -> input !=null && input == 1);
        filter.forEach(integer -> System.out.println(integer));
        list.forEach(integer -> System.out.println(integer));
    }
}
