package org.example;

import java.util.*;

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

    public TreeNode balanceBST(TreeNode root) {
        // https://leetcode.com/problems/balance-a-binary-search-tree/?envType=daily-question&envId=2026-02-09

        List<Integer> elems = new ArrayList<>();
        inorderTraversal(root, elems);

        return buildBalancedBST(elems, 0, elems.size() - 1);
    }

    private void inorderTraversal(TreeNode node, List<Integer> elements) {
        if (node.left != null) {
            inorderTraversal(node.left, elements);
        }

        elements.add(node.val);

        if (node.right != null) {
            inorderTraversal(node.right, elements);
        }
    }

    private TreeNode buildBalancedBST(List<Integer> elems, int start, int end) {
        if (start > end) return null;

        int mid = (end + start) / 2;

        TreeNode node = new TreeNode(elems.get(mid));
        node.left = buildBalancedBST(elems, start, mid - 1);
        node.right = buildBalancedBST(elems, mid + 1, end);

        return node;
    }

    public int longestBalanced(int[] nums) {
        // https://leetcode.com/problems/longest-balanced-subarray-i/?envType=daily-question&envId=2026-02-10

        // iterate over every subarray since length < 1500
        // count distinct odds and evens using hash map
        // track max length

        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            Set<Integer> evens = new HashSet<>();
            Set<Integer> odds = new HashSet<>();

            for (int j = i; j < nums.length; j++) {
                if (nums[j] % 2 == 0) {
                    evens.add(nums[j]);
                } else {
                    odds.add(nums[j]);
                }

                if (evens.size() == odds.size()) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        return maxLength;
    }

    private int index;
    private final Map<Integer, Integer> inorderIndex  = new HashMap<>();

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        // https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/?envType=study-plan-v2&envId=top-interview-150

        // iterate over postorder array in reverse order
        // use in order traversal to reconstruct a tree using divide and conquer
        // store indexes in a hash map

        for (int i = 0; i < inorder.length; i++) {
            inorderIndex.put(inorder[i], i);
        }

        index = postorder.length - 1;

        return build(postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int start, int end) {
        if (start > end) return null;

        TreeNode node = new TreeNode(postorder[index--]);
        int mid = inorderIndex.get(node.val);

        node.right = build(postorder, mid + 1, end);
        node.left = build(postorder, start, mid - 1);

        return node;
    }

    public Node connect(Node root) {
        // https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/?envType=study-plan-v2&envId=top-interview-150

        // iterate with bfs
        // set pointers in each level

        if (root == null) return null;

        Queue<Node> bfs = new LinkedList<>();
        bfs.add(root);

        while (!bfs.isEmpty()) {
            int levelSize = bfs.size();

            Node prev = bfs.poll();

            if (prev.left != null) bfs.add(prev.left);
            if (prev.right != null) bfs.add(prev.right);

            for (int i = 1; i < levelSize; i++) {
                Node node = bfs.poll();
                prev.next = node;
                prev = node;

                if (prev.left != null) bfs.add(prev.left);
                if (prev.right != null) bfs.add(prev.right);
            }
        }

        return root;
    }

    public Node connectOptimized(Node root) {
        // https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/?envType=study-plan-v2&envId=top-interview-150

        // connect direct children of a node
        // connect cousins through direct siblings

        if (root == null) return null;

        Node leftChild = getLeftChild(root);
        Node rightChild = getRightChild(root);

        if (leftChild == null && rightChild == null) return root;

        if (leftChild != rightChild) {
            leftChild.next = rightChild;
        }

        if (root.next != null) {
            rightChild.next = getLeftChild(root.next);
        }

        connectOptimized(leftChild);
        if (leftChild != rightChild) connectOptimized(rightChild);

        return root;
    }

    private Node getLeftChild(Node node) {
        if (node.left != null) return node.left;

        if (node.right != null) return node.right;

        return null;
    }

    private Node getRightChild(Node node) {
        if (node.right != null) return node.right;

        if (node.left != null) return node.left;

        return null;
    }

    public int longestBalanced_2_skipped(int[] nums) {
        // https://leetcode.com/problems/longest-balanced-subarray-ii/?envType=daily-question&envId=2026-02-11

        // the answer is between 0 and nums.length
        // find max length with a binary search
        // if balanced subarray exists -> there exists a subsubarray for each balanced size
        return 0;
    }

    public void flatten(TreeNode root) {
        // https://leetcode.com/problems/flatten-binary-tree-to-linked-list/?envType=study-plan-v2&envId=top-interview-150

        // flatten(left) should follow the node
        // flatten(right) should come after the flatten(left)

        rec(root);
    }

    private TreeNode rec(TreeNode node) {
        if (node == null) return null;

        TreeNode leftEnd = rec(node.left);
        TreeNode rightEnd = rec(node.right);

        if (leftEnd != null) {
            leftEnd.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        if (rightEnd != null) return rightEnd;

        if (leftEnd != null) return leftEnd;

        return node;
    }

    public int longestBalanced(String s) {
        int n = s.length();
        int res = 0;
        int[] cnt = new int[26];

        for (int i = 0; i < n; i++) {
            Arrays.fill(cnt, 0);
            for (int j = i; j < n; j++) {
                boolean flag = true;
                int c = s.charAt(j) - 'a';
                cnt[c]++;

                for (int x : cnt) {
                    if (x > 0 && x != cnt[c]) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        // https://leetcode.com/problems/path-sum/?envType=study-plan-v2&envId=top-interview-150
        if (root == null) return false;

        return pathSum(root, targetSum, 0);
    }

    private boolean pathSum(TreeNode node, int targetSum, int sum) {
        if (node.left == null && node.right == null) {
            return node.val + sum == targetSum;
        }

        if (node.left != null && pathSum(node.left, targetSum, sum + node.val)) return true;

        return node.right != null && pathSum(node.right, targetSum, sum + node.val);
    }

    public int sumNumbers(TreeNode root) {
        // https://leetcode.com/problems/sum-root-to-leaf-numbers/?envType=study-plan-v2&envId=top-interview-150

        return pathNum(root, 0);
    }

    private int pathNum(TreeNode node, int sum) {
        if (node.left == null && node.right == null) {
            return sum * 10 + node.val;
        }

        int res = 0;

        if (node.left != null) {
            res += pathNum(node.left, sum * 10 + node.val);
        }

        if (node.right != null) {
            res += pathNum(node.right, sum * 10 + node.val);
        }

        return res;
    }

    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] A = new double[102][102];
        A[0][0] = (double) poured;
        for (int r = 0; r <= query_row; ++r) {
            for (int c = 0; c <= r; ++c) {
                double q = (A[r][c] - 1.0) / 2.0;
                if (q > 0) {
                    A[r+1][c] += q;
                    A[r+1][c+1] += q;
                }
            }
        }

        return Math.min(1, A[query_row][query_glass]);
    }

    public String addBinary(String a, String b) {
        boolean shift = false;

        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;

        while (i >= 0 && j >= 0) {
            int res = (a.charAt(i) - '0') + (b.charAt(j) - '0') + (shift ? 1 : 0);
            shift = res > 1;
            res %= 2;
            sb.append(res);
            i--;
            j--;
        }

        while (i >= 0) {
            int res = (a.charAt(i) - '0') + (shift ? 1 : 0);
            shift = res > 1;
            res %= 2;
            sb.append(res);
            i--;
        }

        while (j >= 0) {
            int res = (b.charAt(j) - '0') + (shift ? 1 : 0);
            shift = res > 1;
            res %= 2;
            sb.append(res);
            j--;
        }

        if (shift) {
            sb.append(1);
        }

        return sb.reverse().toString();
    }
}
