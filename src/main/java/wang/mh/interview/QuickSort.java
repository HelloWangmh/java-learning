package wang.mh.interview;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 99, 56, 3};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int start, int end) {
       if (start < end) {
           int mid = partition(arr, start, end);
           quickSort(arr, start, mid - 1);
           quickSort(arr, mid, end);
       }
    }

    public static int partition(int[] arr, int start, int end) {
        int i = start;
        for (int j = start; j < end; j++) {
            if (arr[j] < arr[end]) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, end);
        return i;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
