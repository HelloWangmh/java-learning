package wang.mh.interview;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        ArrayList<Integer> list = Lists.newArrayList(1, 4, 5, 7, 99, 20);
        List<Integer> result = mergeSort(list);
        System.out.println(result);
    }

    public static List<Integer> mergeSort(List<Integer> list) {
        if (list.size() == 1) {
            return list;
        }

        int mid = list.size() / 2;
        List<Integer> left = mergeSort(list.subList(0, mid));
        List<Integer> right = mergeSort(list.subList(mid, list.size()));
        return merge(left, right);
    }

    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        int l = 0;
        int r = 0;
        List<Integer> result = Lists.newArrayList();
        while (l < left.size() && r < right.size()) {
            if (left.get(l) < right.get(r)) {
                result.add(left.get(l));
                l++;
            } else {
                result.add(right.get(r));
                r++;
            }
        }

        if (l < left.size()) {
            for (;l < left.size(); l++) {
                result.add(left.get(l));
            }
        }

        if (r < right.size()) {
            for (;r < right.size(); r++) {
                result.add(right.get(r));
            }
        }

        return result;
    }
}
