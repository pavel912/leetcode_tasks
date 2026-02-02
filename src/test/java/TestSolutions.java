import org.example.LRUCache;
import org.example.ListNode;
import org.example.Solution;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
