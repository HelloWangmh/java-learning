package wang.mh.guava;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import wang.mh.common.Father;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CollectionDemo {

    public static void main(String[] args) {
        ArrayList<String> list = Lists.newArrayList();
        for(int i =0 ;i<10000;i++){
            list.add("test");
        }
        List<List<String>> partition = Lists.partition(list, 2000);
        partition.forEach(l -> {
            System.out.println(l.size());
        });
    }
}
