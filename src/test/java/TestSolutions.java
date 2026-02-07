import org.example.LRUCache;
import org.example.ListNode;
import org.example.Solution;
import org.example.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSolutions {
    private List<Integer> llToList(ListNode head) {
        List<Integer> res = new ArrayList<>();
        ListNode cur = head;

        while (cur != null) {
            res.add(cur.val);
            cur = cur.next;
        }

        return res;
    }

    private boolean listEquals(ListNode a, ListNode b) {
        var listA = llToList(a);
        var listB = llToList(b);

        if (listA.size() != listB.size()) return false;

        for (int i = 0; i < listA.size(); i++) {
            if (!listA.get(i).equals(listB.get(i))) return false;
        }

        return true;
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p == null || q == null || p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    @Test
    public void testMinCost1() {
        assertEquals(6, new Solution().minimumCost(new int[] {1, 2, 3, 12}));
    }

    @Test
    public void testMinCost2() {
        assertEquals(12, new Solution().minimumCost(new int[] {5, 4, 3}));
    }

    @Test
    public void testMinCost3() {
        assertEquals(12, new Solution().minimumCost(new int[] {10, 3, 1, 1}));
    }

    @Test
    public void testDeleteDuplicates1() {
        ListNode input = new ListNode(1, new ListNode(1, null));
        ListNode output = null;
        Solution s = new Solution();
        assertTrue(listEquals(output, s.deleteDuplicates(input)));
    }

    @Test
    public void testDeleteDuplicates2() {
        ListNode input = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(3, null))));
        ListNode output = new ListNode(1, new ListNode(3, null));
        Solution s = new Solution();
        assertTrue(listEquals(output, s.deleteDuplicates(input)));
    }

    @Test
    public void testRotateRight1() {
        ListNode input = new ListNode(1, new ListNode(2, null));
        ListNode output = new ListNode(2, new ListNode(1, null));
        Solution s = new Solution();
        assertTrue(listEquals(output, s.rotateRight(input, 1)));
    }

    @Test
    public void testRotateRight2() {
        ListNode input = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode output = new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(1, new ListNode(2, null)))));
        Solution s = new Solution();
        assertTrue(listEquals(output, s.rotateRight(input, 3)));
    }

    @Test
    public void testPartitionList1() {
        ListNode input = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2, null))))));
        ListNode output = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(4, new ListNode(3, new ListNode(5))))));
        Solution s = new Solution();
        assertTrue(listEquals(output, s.partition(input, 3)));
    }

    @Test
    public void testPartitionList2() {
        ListNode input = new ListNode(1, new ListNode(2, null));
        ListNode output = new ListNode(1, new ListNode(2, null));
        Solution s = new Solution();
        assertTrue(listEquals(output, s.partition(input, 0)));
    }

    @Test
    public void testLRU() {
        LRUCache cache = new LRUCache(1);
        assertEquals(-1, cache.get(6));
        assertEquals(-1, cache.get(8));
        cache.put(12, 1);
        assertEquals(-1, cache.get(2));
        cache.put(15, 11);
        cache.put(5, 2);
        cache.put(11, 15);
        cache.put(4, 2);
        assertEquals(2, cache.get(4));
        cache.put(15, 15);
    }

    @Test
    public void testLRU2() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertEquals(1, cache.get(1));
        cache.put(3, 3);
        assertEquals(-1, cache.get(2));
        cache.put(4, 4);
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    public void testTrionic() {
        assertTrue(new Solution().isTrionic(new int[] {1, 2, 1, 3}));
    }

    @Test
    public void testTrionic2() {
        assertFalse(new Solution().isTrionic(new int[] {2, 1, 3}));
    }

    @Test
    public void testTrionic3() {
        assertTrue(new Solution().isTrionic(new int[] {1, 3, 5, 4, 2, 6}));
    }

    @Test
    public void testMaxDepth() {
        TreeNode input = new TreeNode(1, null, new TreeNode(2));
        assertEquals(2, new Solution().maxDepth(input));
    }

    @Test
    public void testMaxDepth2() {
        TreeNode input = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        assertEquals(3, new Solution().maxDepth(input));
    }

    @Test
    public void testMaxDepth1() {
        assertEquals(0, new Solution().maxDepth(null));
    }

    @Test
    public void testIsSameTree() {
        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        assertTrue(new Solution().isSameTree(p, q));
    }

    @Test
    public void testIsSameTree2() {
        TreeNode p = new TreeNode(1, new TreeNode(2), null);
        TreeNode q = new TreeNode(1, null, new TreeNode(2));
        assertFalse(new Solution().isSameTree(p, q));
    }

    @Test
    public void testIsSameTree3() {
        TreeNode p = new TreeNode(1, new TreeNode(3), null);
        TreeNode q = new TreeNode(1, new TreeNode(2), null);
        assertFalse(new Solution().isSameTree(p, q));
    }

    @Test
    public void testInvertTree() {
        TreeNode input = new TreeNode(4,
                new TreeNode(2,
                        new TreeNode(1),
                        new TreeNode(3)),
                new TreeNode(7,
                        new TreeNode(6),
                        new TreeNode(9))
        );
        TreeNode output = new TreeNode(4,
                new TreeNode(7,
                        new TreeNode(9),
                        new TreeNode(6)),
                new TreeNode(2,
                        new TreeNode(3),
                        new TreeNode(1))
        );
        assertTrue(isSameTree(output, new Solution().invertTree(input)));
    }

    @Test
    public void testInvertTree2() {
        TreeNode input = null;
        TreeNode output = null;
        assertTrue(isSameTree(output, new Solution().invertTree(input)));
    }

    @Test
    public void testIsSymmetric() {
        TreeNode input = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3),
                        new TreeNode(4)),
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(3))
        );
        assertTrue(new Solution().isSymmetric(input));
    }

    @Test
    public void testIsSymmetric2() {
        TreeNode input = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3),
                        null),
                new TreeNode(2,
                        new TreeNode(3),
                        null)
        );
        assertFalse(new Solution().isSymmetric(input));
    }

    @Test
    void testMinDelitions() {
        assertEquals(41, new Solution().minimumDeletions("aabbaababbababaabbbaabbbbaababababbabbbababbabbaabaaabbbbbbaaabbbbabaababbaaabbbbaaabababbbaaa"));
    }

    @Test
    void testBuildTree() {
        TreeNode ans = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        assertTrue(isSameTree(ans, new Solution().buildTree(new int[] {3,9,20,15,7}, new int[] {9,3,15,20,7})));
    }

}
