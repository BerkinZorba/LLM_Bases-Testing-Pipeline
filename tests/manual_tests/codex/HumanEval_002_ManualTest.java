import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

/**
 * Manual black-box tests for HumanEval_002 (codex / GPT).
 * Cases derived from HumanEval_002_blackbox.md.
 */
public class HumanEval_002_ManualTest {

    private final Solution s = new Solution();

    @Test @DisplayName("V1: exact integer 1.0 -> 0.0")
    void v1_exactIntegerOne() {
        assertEquals(0.0, s.truncateNumber(1.0), 1e-9);
    }

    @Test @DisplayName("V2: exact integer 100.0 -> 0.0")
    void v2_exactIntegerLarge() {
        assertEquals(0.0, s.truncateNumber(100.0), 1e-9);
    }

    @Test @DisplayName("V3: fraction-only 0.5 -> 0.5")
    void v3_fractionOnly() {
        assertEquals(0.5, s.truncateNumber(0.5), 1e-9);
    }

    @Test @DisplayName("V4: near-one fraction 0.999 -> 0.999")
    void v4_nearOneFraction() {
        assertEquals(0.999, s.truncateNumber(0.999), 1e-9);
    }

    @Test @DisplayName("V5: tiny fraction 0.001 -> 0.001")
    void v5_tinyFraction() {
        assertEquals(0.001, s.truncateNumber(0.001), 1e-9);
    }

    @Test @DisplayName("V6: docstring 3.5 -> 0.5")
    void v6_docstringExample() {
        assertEquals(0.5, s.truncateNumber(3.5), 1e-9);
    }

    @Test @DisplayName("V7: 1.33 -> 0.33")
    void v7_onePointThreeThree() {
        assertEquals(0.33, s.truncateNumber(1.33), 1e-9);
    }

    @Test @DisplayName("V8: 123.456 -> 0.456")
    void v8_largeWithFraction() {
        assertEquals(0.456, s.truncateNumber(123.456), 1e-9);
    }

    @Test @DisplayName("Boundary: result always in [0, 1)")
    void boundary_resultInRange() {
        double[] inputs = {0.001, 0.5, 0.999, 1.0, 3.5, 99.9};
        for (double v : inputs) {
            double frac = s.truncateNumber(v);
            assertTrue(frac >= 0.0 && frac < 1.0,
                    "Expected [0,1) for input " + v + " but got " + frac);
        }
    }
}
