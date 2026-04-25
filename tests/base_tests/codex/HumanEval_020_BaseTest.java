import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_020_BaseTest {

    private final Solution s = new Solution();

    @Test
    void closestPairNearFour() {
        assertEquals(Arrays.asList(3.9, 4.0),
                s.findClosestElements(Arrays.asList(1.0, 2.0, 3.9, 4.0, 5.0, 2.2)));
    }

    @Test
    void closestPairNearSix() {
        assertEquals(Arrays.asList(5.0, 5.9),
                s.findClosestElements(Arrays.asList(1.0, 2.0, 5.9, 4.0, 5.0)));
    }

    @Test
    void promptExampleWithDecimalNeighbor() {
        assertEquals(Arrays.asList(2.0, 2.2),
                s.findClosestElements(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.2)));
    }

    @Test
    void promptExampleWithDuplicate() {
        assertEquals(Arrays.asList(2.0, 2.0),
                s.findClosestElements(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.0)));
    }

    @Test
    void closestPairInSortedMiddle() {
        assertEquals(Arrays.asList(2.2, 3.1),
                s.findClosestElements(Arrays.asList(1.1, 2.2, 3.1, 4.1, 5.1)));
    }
}
