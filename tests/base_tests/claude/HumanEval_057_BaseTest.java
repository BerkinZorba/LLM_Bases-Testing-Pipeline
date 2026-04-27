import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HumanEval_057_BaseTest {

    private final Solution s = new Solution();

    @Test
    void increasing_1_2_4_10_isMonotonic() {
        assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(1, 2, 4, 10))));
    }

    @Test
    void increasing_1_2_4_20_isMonotonic() {
        assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(1, 2, 4, 20))));
    }

    @Test
    void mixed_1_20_4_10_isNotMonotonic() {
        assertFalse(s.monotonic(new ArrayList<>(Arrays.asList(1, 20, 4, 10))));
    }

    @Test
    void decreasing_4_1_0_minus10_isMonotonic() {
        assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(4, 1, 0, -10))));
    }

    @Test
    void nonIncreasingWithEqual_4_1_1_0_isMonotonic() {
        assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(4, 1, 1, 0))));
    }

    @Test
    void mixed_1_2_3_2_5_60_isNotMonotonic() {
        assertFalse(s.monotonic(new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5, 60))));
    }

    @Test
    void increasing_1_2_3_4_5_60_isMonotonic() {
        assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 60))));
    }

    @Test
    void allEqual_9s_isMonotonic() {
        assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(9, 9, 9, 9))));
    }
}
