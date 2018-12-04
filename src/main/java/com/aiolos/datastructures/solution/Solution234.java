package com.aiolos.datastructures.solution;

/**
 * @author aiolos
 * 2018-11-30
 */
public class Solution234 {

    private static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    ListNode head;
    public Solution234(int val) {
        head = new ListNode(val);
    }

    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null)
            return true;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        slow = reverse(slow);
        fast = head;
        while (slow != null) {
            if (slow.val != fast.val)
                return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {

        ListNode cur = head;
        ListNode next = null;
        ListNode tail = null;
        while (cur != null) {
            next = cur.next;
            cur.next = tail;
            tail = cur;
            cur = next;
        }
        return tail;
    }

    public static void main(String[] args) {

        Solution234 s = new Solution234(1);
        int[] nums = {2, 3, 2, 1};
        ListNode head = s.head;
        for (int i = 0; i < nums.length; i ++) {
            head.next = new ListNode(nums[i]);
            head = head.next;
        }

        System.out.println(s.isPalindrome(s.head));
    }
}
