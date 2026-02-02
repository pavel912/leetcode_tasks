package org.example;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        //https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/?envType=study-plan-v2&envId=top-interview-150
        ListNode dummyHead = new ListNode(0, head);
        ListNode cur = dummyHead;

        while (cur.next != null) {
            int cnt = 0;
            int val = cur.next.val;
            ListNode iter = cur.next;
            while (iter != null && iter.val == val) {
                cnt++;
                iter = iter.next;
            }

            if (cnt > 1) {
                cur.next = iter;
            } else {
                cur = cur.next;
            }
        }

        return dummyHead.next;
    }

    public int minimumCost(int[] nums) {
        // https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-i/?envType=daily-question&envId=2026-02-01

        int min1 = 51;
        int min2 = 52;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min1) {
                min2 = min1;
                min1 = nums[i];
            } else if (nums[i] < min2) {
                min2 = nums[i];
            }
        }

        return nums[0] + min1 + min2;
    }

    public ListNode rotateRight(ListNode head, int k) {
        // https://leetcode.com/problems/rotate-list/?envType=study-plan-v2&envId=top-interview-150

        if (head == null) return null;

        ListNode cur = head;
        ListNode tail = null;
        int n = 0;
        while (cur != null) {
            tail = cur;
            cur = cur.next;
            n++;
        }

        k %= n;

        if (k == 0) return head;

        ListNode newTail = head;

        int cnt = 0;
        while (cnt < n - k - 1) {
            newTail = newTail.next;
            cnt++;
        }


        ListNode newHead = newTail.next;
        newTail.next = null;
        tail.next = head;

        return newHead;
    }

    public ListNode partition(ListNode head, int x) {
        // https://leetcode.com/problems/partition-list/?envType=study-plan-v2&envId=top-interview-150

        ListNode smallHead = new ListNode(-1, null);
        ListNode smallTail = smallHead;
        ListNode largeHead = new ListNode(1, null);
        ListNode largeTail = largeHead;

        ListNode iter = head;

        while (iter != null) {
            ListNode next = iter.next;
            iter.next = null;

            if (iter.val < x) {
                smallTail.next = iter;
                smallTail = smallTail.next;
            } else {
                largeTail.next = iter;
                largeTail = largeTail.next;
            }

            iter = next;
        }

        smallTail.next = largeHead.next;
        return smallHead.next;
    }
}
