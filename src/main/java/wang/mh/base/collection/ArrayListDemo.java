package wang.mh.base.collection;

import com.google.common.collect.Lists;
import com.sun.xml.internal.ws.api.model.CheckedException;

import java.util.*;

/**
 * Created by 明辉 on 2017/6/12.
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.set(0,2);



    }

    private static void testArrays(){
        List<Integer> arrList = Arrays.asList(1, 2);
        //error 这里改变数组大小的方法会  UnsupportedOperationException
        arrList.add(3);
    }
}
