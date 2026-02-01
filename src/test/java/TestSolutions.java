import org.example.Solution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSolutions {
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
}
