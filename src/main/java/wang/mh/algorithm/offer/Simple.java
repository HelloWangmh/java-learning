package wang.mh.algorithm.offer;


public class Simple {


    public static void main(String[] args) {

    }

    public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
    }


    /**
     *找出数组中重复的数字。
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
     */
    public int findRepeatNumber(int[] nums) {
        //1.哈希表
        //2.原地交换

        return -1;
    }


    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * @return
     */
    public String replaceSpace(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        char[] arr = new char[s.length() * 3];//只需要创建一个数组就可以解决
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                arr[size++] = '%';
                arr[size++] = '2';
                arr[size++] = '0';
            } else {
                arr[size++] = s.charAt(i);
            }
        }
        return new String(arr, 0 , size);
    }

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {

    }
}
