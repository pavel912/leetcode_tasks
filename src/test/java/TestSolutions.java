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
}
