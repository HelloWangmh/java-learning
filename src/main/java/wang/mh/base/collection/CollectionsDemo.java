package wang.mh.base.collection;

import com.google.common.collect.Lists;

import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        listToArr();
    }


    /**
     * 集合转数组
     */
    private static void listToArr(){
        ArrayList<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        //返回一个新数组
        Integer[] arr = list.toArray(new Integer[0]);

        System.out.println("arr:"+arr[1]);

        //如果arr.length >= size  那么返回的就是这个数组
        Integer[] existArr = new Integer[list.size()+1];
        Integer[] newArr = list.toArray(existArr);
        System.out.println(newArr == existArr); //true
        System.out.println("existArr:"+existArr[1]);
        System.out.println("existArr:"+existArr[3]); //null
        System.out.println("newArr:"+newArr[1]);
    }



    /**
     * 返回一个空集合
     * 不可修改
     */
    private static void testEmpty(){
        Set<String> set = Collections.emptySet();
        set.add("hello"); //error UnsupportedOperationException
    }

    /**
     * 返回给定范围的集合  包前不包后
     * subList的改变也会影响原始List
     */
    private static void testSubList(){
        ArrayList<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Integer> subList = list.subList(0, 2);
        subList.remove(new Integer(1));

        System.out.println(subList);  //2
        System.out.println(list);  // 2 3 4
    }

    /**
     * 返回一个数组元素的列表视图,可以修改,但是不能改变大小
     */
    private static void testAsList(){
        List<Integer> list = Arrays.asList(1, 2, 3);
        list.set(0,11);
        list.add(5);//error
    }

    /**
     * 不可修改
     */
    private static void testSingleton(){
        Set<Integer> singleton = Collections.singleton(1);
        singleton.add(2); //error
    }

    /**
     * 不可改变  返回一个包含n个相同元素的列表,集合
     */
    private static void testNCopies(){
        List<Integer> integers = Collections.nCopies(100, 88);
        integers.add(1);
        System.out.println("size:"+integers.size());//100
    }

    /**
     * 受查视图
     * 添加的时候会进行类型检查,避免在get()时候发现
     *
     */
    private static void testCheckedList(){
        List<String> list = Collections.checkedList(new ArrayList<>(),String.class);
        list.add("test");
        List listObj = list;
        //error   ClassCastException
        listObj.add(1);
    }
}
