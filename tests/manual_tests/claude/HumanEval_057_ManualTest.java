import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Manual black-box JUnit 6 suite for HumanEval_057.
 * Materializes the equivalence classes and boundaries from
 * tests/manual_tests/claude/HumanEval_057_blackbox.md.
 */
public class HumanEval_057_ManualTest {

    private final Solution s = new Solution();

    // V1 — empty list
    @Test @DisplayName("V1: empty list -> true (vacuously monotonic)")
    void v1_empty() { assertTrue(s.monotonic(new ArrayList<>())); }

    // V2 — single element
    @Test @DisplayName("V2: [42] -> true (vacuously monotonic)")
    void v2_singleton() {
        assertTrue(s.monotonic(new ArrayList<>(Collections.singletonList(42))));
    }

    // V3 — strict up pair
    @Test @DisplayName("V3: [1,2] -> true")
    void v3_strictUpPair() { assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(1, 2)))); }

    // V4 — strict down pair
    @Test @DisplayName("V4: [2,1] -> true")
    void v4_strictDownPair() { assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(2, 1)))); }

    // V5 — equal pair
    @Test @DisplayName("V5: [7,7] -> true")
    void v5_equalPair() { assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(7, 7)))); }

    // V6 — strictly increasing, len >= 3
    @Test @DisplayName("V6: [1,2,4,10] -> true (dataset)")
    void v6_strictUp_a() { assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(1, 2, 4, 10)))); }

    @Test @DisplayName("V6: [1,2,4,20] -> true (dataset)")
    void v6_strictUp_b() { assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(1, 2, 4, 20)))); }

    @Test @DisplayName("V6: [-5,-1,0,3,100] -> true (sign-straddling strict up)")
    void v6_strictUp_signed() {
        assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(-5, -1, 0, 3, 100))));
    }

    // V7 — strictly decreasing, len >= 3
    @Test @DisplayName("V7: [4,1,0,-10] -> true (dataset, sign-straddling strict down)")
    void v7_strictDown() { assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(4, 1, 0, -10)))); }

    // V8 — non-decreasing / non-increasing with plateau
    @Test @DisplayName("V8: [1,2,2,3] -> true (non-decreasing plateau)")
    void v8_plateauUp() { assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(1, 2, 2, 3)))); }

    @Test @DisplayName("V8: [4,1,1,0] -> true (non-increasing plateau, dataset)")
    void v8_plateauDown() { assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(4, 1, 1, 0)))); }

    // V9 — all equal
    @Test @DisplayName("V9: [9,9,9,9] -> true (dataset)")
    void v9_allEqual() { assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(9, 9, 9, 9)))); }

    // V10 — up then down
    @Test @DisplayName("V10: [1,3,2] -> false (smallest up-then-down)")
    void v10_upThenDown_min() { assertFalse(s.monotonic(new ArrayList<>(Arrays.asList(1, 3, 2)))); }

    @Test @DisplayName("V10: [1,20,4,10] -> false (dataset spike)")
    void v10_spike() { assertFalse(s.monotonic(new ArrayList<>(Arrays.asList(1, 20, 4, 10)))); }

    // V11 — down then up
    @Test @DisplayName("V11: [3,1,2] -> false (smallest down-then-up)")
    void v11_downThenUp_min() { assertFalse(s.monotonic(new ArrayList<>(Arrays.asList(3, 1, 2)))); }

    @Test @DisplayName("V11: [1,2,3,2,5,60] -> false (dataset dip)")
    void v11_dip() { assertFalse(s.monotonic(new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5, 60)))); }

    // V12 — extreme range
    @Test @DisplayName("V12: [MIN_VALUE, MAX_VALUE] -> true (no overflow; uses <,> only)")
    void v12_extremeRangeUp() {
        assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE))));
    }

    @Test @DisplayName("V12: [MAX_VALUE, MIN_VALUE] -> true (no overflow on strict down)")
    void v12_extremeRangeDown() {
        assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(Integer.MAX_VALUE, Integer.MIN_VALUE))));
    }
}
