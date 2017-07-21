package wang.mh.base.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 明辉 on 2017/6/12.
 *
 * ArrayList使用普通for循环遍历时间为8ms
 * ArrayList使用foreach循环遍历时间为15ms
 * LinkedList使用普通for循环遍历时间为XXXXXXXms    很慢
 * LinkedList使用foreach循环遍历时间为XXms
 */
public class LinkedListDemo {

    private static int SIZE = 1111111;

    private static void loopList(List<Integer> list)
    {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++)
        {
            list.get(i);
        }
        System.out.println(list.getClass().getSimpleName() + "使用普通for循环遍历时间为" +
                (System.currentTimeMillis() - startTime) + "ms");

        startTime = System.currentTimeMillis();
        for (Integer i : list)
        {

        }
        System.out.println(list.getClass().getSimpleName() + "使用foreach循环遍历时间为" +
                (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void main(String[] args)
    {
        List<Integer> arrayList = new ArrayList<Integer>(SIZE);
        List<Integer> linkedList = new LinkedList<Integer>();

        for (int i = 0; i < SIZE; i++)
        {
            arrayList.add(i);
            linkedList.add(i);
        }

        loopList(arrayList);
        loopList(linkedList);
        System.out.println();
    }
}
