package wang.mh.thread.collection;

import java.util.Arrays;
import java.util.Comparator;

/**
 *并行数组算法
 */
public class ParallelArray {
    private static int[] arr = new int[]{1,2,3,4,5,6,7,8};

    public static void main(String[] args) {
        testPatallerSort();
    }


    /**
     * 替换
     */
    public static void testPatallerSet(){
        //
        Arrays.parallelSetAll(arr,index -> arr[index] * index);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * 排序
     */
    public static void testPatallerSort(){
        Integer[] arr2 = new Integer[]{1,2,3,4,5,6,7,8};
        Arrays.parallelSort(arr2,Comparator.comparingInt(i -> 0-i));
        for (int i : arr2) {
            System.out.println(i);
        }
    }



    public static void testParallelPrefix(){
        Arrays.parallelPrefix(arr,(x,y) -> x*y);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
