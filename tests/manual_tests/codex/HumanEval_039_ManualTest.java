import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

/**
 * Manual black-box tests for HumanEval_039 (codex / GPT).
 * Cases derived from HumanEval_039_blackbox.md.
 */
public class HumanEval_039_ManualTest {

    private final Solution s = new Solution();

    @Test @DisplayName("V1: n=1 -> 2 (only even prime)")
    void v1_first() { assertEquals(2, s.primeFib(1)); }

    @Test @DisplayName("V2: n=2 -> 3")
    void v2_second() { assertEquals(3, s.primeFib(2)); }

    @Test @DisplayName("V3: n=3 -> 5 (8 skipped as even composite)")
    void v3_third() { assertEquals(5, s.primeFib(3)); }

    @Test @DisplayName("V4: n=4 -> 13 (8 and 21 skipped as composites)")
    void v4_fourth() { assertEquals(13, s.primeFib(4)); }

    @Test @DisplayName("V5: n=5 -> 89")
    void v5_fifth() { assertEquals(89, s.primeFib(5)); }

    @Test @DisplayName("V6: n=6 -> 233")
    void v6_sixth() { assertEquals(233, s.primeFib(6)); }

    @Test @DisplayName("V7: n=7 -> 1597")
    void v7_seventh() { assertEquals(1597, s.primeFib(7)); }

    @Test @DisplayName("Boundary: sequence is strictly increasing")
    void boundary_strictlyIncreasing() {
        int prev = 0;
        for (int n = 1; n <= 6; n++) {
            int val = s.primeFib(n);
            assertTrue(val > prev, "Not strictly increasing at n=" + n);
            prev = val;
        }
    }

    @Test @DisplayName("Boundary: n=1 returns the only even prime (2)")
    void boundary_onlyEvenPrime() {
        assertEquals(2, s.primeFib(1));
        // all subsequent prime Fibonacci numbers must be odd
        for (int n = 2; n <= 5; n++) {
            assertTrue(s.primeFib(n) % 2 != 0, "Expected odd prime Fibonacci at n=" + n);
        }
    }
}
