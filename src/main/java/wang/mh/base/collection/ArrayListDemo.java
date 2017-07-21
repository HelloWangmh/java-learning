package wang.mh.base.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 明辉 on 2017/6/12.
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0;i<1000;i++){
            list.add(i);
        }
        for (Integer i : list) {
            list.remove(i);
        }
    }
}
