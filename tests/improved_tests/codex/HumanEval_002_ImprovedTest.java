import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

/**
 * Improved suite for HumanEval_002 (codex / GPT).
 *
 * Smells addressed:
 *   - Assertion roulette: each case is a separate @Test with a display name.
 *   - Magic numbers: values described in display names.
 *
 * Branch-coverage targets: no branches in the implementation (single return).
 * Additional value: exercises the delta tolerance, exact integer boundary,
 * and sub-1 input to validate the one-liner cast approach.
 */
public class HumanEval_002_ImprovedTest {

    private final Solution s = new Solution();

    @Test @DisplayName("docstring: 3.5 -> 0.5")
    void docstringExample() {
        assertEquals(0.5, s.truncateNumber(3.5), 1e-9);
    }

    @Test @DisplayName("1.33 -> 0.33")
    void onePointThreeThree() {
        assertEquals(0.33, s.truncateNumber(1.33), 1e-9);
    }

    @Test @DisplayName("123.456 -> 0.456")
    void largeIntegerPart() {
        assertEquals(0.456, s.truncateNumber(123.456), 1e-9);
    }

    @Test @DisplayName("exact integer 5.0 -> 0.0")
    void exactInteger() {
        assertEquals(0.0, s.truncateNumber(5.0), 1e-9);
    }

    @Test @DisplayName("exactly 1.0 -> 0.0")
    void one() {
        assertEquals(0.0, s.truncateNumber(1.0), 1e-9);
    }

    @Test @DisplayName("sub-one 0.5 -> 0.5 (integer part is 0)")
    void subOne() {
        assertEquals(0.5, s.truncateNumber(0.5), 1e-9);
    }

    @Test @DisplayName("0.999 -> 0.999 (nearly one)")
    void nearlyOne() {
        assertEquals(0.999, s.truncateNumber(0.999), 1e-9);
    }

    @Test @DisplayName("0.001 -> 0.001 (tiny fraction)")
    void tinyFraction() {
        assertEquals(0.001, s.truncateNumber(0.001), 1e-9);
    }

    @Test @DisplayName("100.0 -> 0.0 (large exact integer)")
    void largeExactInteger() {
        assertEquals(0.0, s.truncateNumber(100.0), 1e-9);
    }

    @Test @DisplayName("result is always in [0, 1)")
    void resultInRange() {
        double[] inputs = {0.1, 1.1, 9.9, 99.99, 0.5};
        for (double v : inputs) {
            double frac = s.truncateNumber(v);
            assertTrue(frac >= 0.0 && frac < 1.0,
                    "Expected [0,1) but got " + frac + " for input " + v);
        }
    }
}
