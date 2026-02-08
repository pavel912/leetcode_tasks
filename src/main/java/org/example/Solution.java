package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public boolean isTrionic(int[] nums) {
        // https://leetcode.com/problems/trionic-array-i/?envType=daily-question&envId=2026-02-03

        int n = nums.length;

        int cnt = 1;
        int i = 1;
        while (i < n && nums[i - 1] < nums[i]) {
            cnt++;
            i++;
        }

        if (i >= n || cnt < 2) return false;

        cnt = 1;

        while (i < n && nums[i - 1] > nums[i]) {
            cnt++;
            i++;
        }

        if (i >= n || cnt < 2) return false;

        cnt = 1;

        while (i < n && nums[i - 1] < nums[i]) {
            cnt++;
            i++;
        }

        return i == n && cnt > 1;
    }

    public int maxDepth(TreeNode root) {
        // https://leetcode.com/problems/maximum-depth-of-binary-tree/?envType=study-plan-v2&envId=top-interview-150
        if (root == null) return 0;

        return rec(root, 1);
    }

    private int rec(TreeNode node, int depth) {
        int left = depth;
        int right = depth;

        if (node.left != null) left = rec(node.left, depth + 1);

        if (node.right != null) right = rec(node.right, depth + 1);

        return Math.max(left, right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // https://leetcode.com/problems/same-tree/?envType=study-plan-v2&envId=top-interview-150
        if (p == null && q == null) return true;

        if (p == null || q == null || p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public TreeNode invertTree(TreeNode root) {
        // https://leetcode.com/problems/invert-binary-tree/?envType=study-plan-v2&envId=top-interview-150
        if (root == null) return null;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        // https://leetcode.com/problems/symmetric-tree/description/?envType=study-plan-v2&envId=top-interview-150
        if (root == null) return true;

        return compare(root.left, root.right);
    }

    private boolean isMirror(TreeNode p, TreeNode q) {
        if (p.val != q.val) return false;

        return compare(p.left, q.right) && compare(p.right, q.left);
    }

    private boolean compare(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;

        return a != null && b != null && isMirror(a, b);
    }

    public int minimumDeletions(String s) {
        // https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/?envType=daily-question&envId=2026-02-07

        int totalA = 0;
        int totalB = 0;
        int cntA = 0;
        int cntB = 0;

        for (char c : s.toCharArray()) {
            if (c == 'a') totalA++;
            else totalB++;
        }

        int minDels = Math.min(totalA, totalB);

        for (char c : s.toCharArray()) {
            if (c == 'a') cntA++;
            else cntB++;

            minDels = Math.min(minDels, cntB + totalA - cntA);
        }

        return minDels;
    }

    private int preorderIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/?envType=study-plan-v2&envId=top-interview-150

        Map<Integer, Integer> inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(preorder, inorderMap, 0, inorder.length - 1);
        /*
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode cur = root;

        for (int i = 1; i < preorder.length; i++) {
            int target = inorderMap.get(preorder[i]);

            while (true) {
                if (inorderMap.get(cur.val) < target) {
                    if (cur.right != null) {
                        cur = cur.right;
                    } else {
                        cur.right = new TreeNode(preorder[i]);
                        break;
                    }
                } else {
                    if (cur.left != null) {
                        cur = cur.left;
                    } else {
                        cur.left = new TreeNode(preorder[i]);
                        break;
                    }
                }
            }

            cur = root;
        }

        return root;
         */
    }

    private TreeNode build(int[] preorder, Map<Integer, Integer> inorderMap, int start, int end) {
        if (start > end) return null;

        int val = preorder[preorderIndex++];
        TreeNode node = new TreeNode(val);
        int mid = inorderMap.get(val);

        node.left = build(preorder, inorderMap, start, mid - 1);
        node.right = build(preorder, inorderMap, mid + 1, end);

        return node;
    }

    private boolean ans = true;

    public boolean isBalanced(TreeNode root) {
        // https://leetcode.com/problems/balanced-binary-tree/description/?envType=daily-question&envId=2026-02-08
        if (root == null) return true;

        height(root, 1);

        return ans;
    }

    public int height(TreeNode node, int height) {
        int left = height;
        int right = height;

        if (node.left != null) left = height(node.left, height + 1);
        if (node.right != null) right = height(node.right, height + 1);

        if (Math.abs(left - right) > 1) ans = false;

        return Math.max(left, right);
    }
}
