import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Manual black-box JUnit 6 suite for HumanEval_036.
 * Materializes the equivalence classes and boundaries from
 * tests/manual_tests/claude/HumanEval_036_blackbox.md.
 */
public class HumanEval_036_ManualTest {

    private final Solution s = new Solution();

    // V1 — no loop entry
    @Test @DisplayName("V1: n=0 -> 0")
    void v1_zero() { assertEquals(0, s.fizzBuzz(0)); }

    @Test @DisplayName("V1: n=1 -> 0")
    void v1_one() { assertEquals(0, s.fizzBuzz(1)); }

    // V2 — loop enters but no qualifying multiples
    @Test @DisplayName("V2: n=2 -> 0 (only i=1 considered, not divisible)")
    void v2_two() { assertEquals(0, s.fizzBuzz(2)); }

    @Test @DisplayName("V2: n=11 -> 0 (i ranges 1..10, no multiples of 11/13)")
    void v2_eleven() { assertEquals(0, s.fizzBuzz(11)); }

    // V3 — multiples present but no '7' digits
    @Test @DisplayName("V3: n=14 -> 0 (covers 11 and 13, neither has '7')")
    void v3_fourteen() { assertEquals(0, s.fizzBuzz(14)); }

    @Test @DisplayName("V3: n=50 -> 0 (covers 11,13,22,26,33,39,44, none have '7')")
    void v3_fifty() { assertEquals(0, s.fizzBuzz(50)); }

    // V4 — single '7' from 78
    @Test @DisplayName("V4: n=79 -> 3 (77 contributes 2, 78 contributes 1)")
    void v4_seventyNine() { assertEquals(3, s.fizzBuzz(79)); }

    // V5 — two '7's from 77
    @Test @DisplayName("V5: n=78 -> 2 (77 contributes both digits)")
    void v5_seventyEight() { assertEquals(2, s.fizzBuzz(78)); }

    // V6 — both-divisor overlap
    @Test @DisplayName("V6: n=144 includes 143=11*13 once, not twice")
    void v6_overlap_isCountedOnce() {
        // Difference between f(144) and f(143) must be 0 because 143 has digits 1,4,3.
        assertEquals(s.fizzBuzz(143), s.fizzBuzz(144));
    }

    // V7 — dataset spot checks
    @Test @DisplayName("V7: dataset n=100 -> 3")
    void v7_oneHundred() { assertEquals(3, s.fizzBuzz(100)); }

    @Test @DisplayName("V7: dataset n=200 -> 6")
    void v7_twoHundred() { assertEquals(6, s.fizzBuzz(200)); }

    @Test @DisplayName("V7: dataset n=4000 -> 192")
    void v7_fourThousand() { assertEquals(192, s.fizzBuzz(4000)); }

    @Test @DisplayName("V7: dataset n=10000 -> 639")
    void v7_tenThousand() { assertEquals(639, s.fizzBuzz(10000)); }

    @Test @DisplayName("V7: dataset n=100000 -> 8026")
    void v7_hundredThousand() { assertEquals(8026, s.fizzBuzz(100000)); }

    // I1/I2 — negative and MIN_VALUE: implementation returns 0 (undefined-by-spec; pinned)
    @Test @DisplayName("I1: n=-1 -> 0 (observed; spec undefined)")
    void i1_negativeOne() { assertEquals(0, s.fizzBuzz(-1)); }

    @Test @DisplayName("I1: n=-100 -> 0 (observed; spec undefined)")
    void i1_negativeHundred() { assertEquals(0, s.fizzBuzz(-100)); }

    @Test @DisplayName("I2: n=Integer.MIN_VALUE -> 0 (observed; spec undefined)")
    void i2_minValue() { assertEquals(0, s.fizzBuzz(Integer.MIN_VALUE)); }

    // Boundary transitions
    @Test @DisplayName("Boundary: f(77) == 0 (excludes 77 itself)")
    void boundary_77_exclusive() { assertEquals(0, s.fizzBuzz(77)); }

    @Test @DisplayName("Boundary: f(78) - f(77) == 2 (crossing 77 adds two '7' digits)")
    void boundary_crossing77_addsTwo() {
        assertEquals(2, s.fizzBuzz(78) - s.fizzBuzz(77));
    }

    @Test @DisplayName("Boundary: f(79) - f(78) == 1 (crossing 78 adds one '7' digit)")
    void boundary_crossing78_addsOne() {
        assertEquals(1, s.fizzBuzz(79) - s.fizzBuzz(78));
    }
}
