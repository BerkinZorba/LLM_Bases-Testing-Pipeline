import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_122_BaseTest {

    private final Solution s = new Solution();

    @Test
    void addElements_negativesIncluded_returnsMinus4() {
        assertEquals(-4, s.addElements(Arrays.asList(1, -2, -3, 41, 57, 76, 87, 88, 99), 3));
    }

    @Test
    void addElements_allFirstKAreThreeDigits_returnsZero() {
        assertEquals(0, s.addElements(Arrays.asList(111, 121, 3, 4000, 5, 6), 2));
    }

    @Test
    void addElements_allFirstKQualify_returns125() {
        assertEquals(125, s.addElements(Arrays.asList(11, 21, 3, 90, 5, 6, 7, 8, 9), 4));
    }

    @Test
    void addElements_datasetExample_returns24() {
        assertEquals(24, s.addElements(Arrays.asList(111, 21, 3, 4000, 5, 6, 7, 8, 9), 4));
    }

    @Test
    void addElements_singleton_returns1() {
        assertEquals(1, s.addElements(Arrays.asList(1), 1));
    }
}
