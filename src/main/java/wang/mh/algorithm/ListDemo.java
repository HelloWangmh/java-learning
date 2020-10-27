package wang.mh.algorithm;

public class ListDemo {

   static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
   }


    /**
     * reverse-linked-list
     * 翻转链表
     */
    public ListNode reverseLinkedList(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode oldNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = oldNext;
        }
        return prev;
    }


    /**
     * 环形链表  linked-list-cycle
     * 给定一个链表，判断链表中是否有环。
     */
    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }

        ListNode oneStep = head;
        ListNode twoStep = head;
        while (twoStep != null && twoStep.next != null) {
            oneStep = oneStep.next;
            twoStep = twoStep.next.next;
            if (oneStep == twoStep) {
                return true;
            }
        }
        return false;
    }


}
