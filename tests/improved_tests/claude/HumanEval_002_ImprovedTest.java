import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Improved suite for HumanEval_002 (truncateNumber).
 *
 * Test-smell improvements:
 *   - Assertion roulette: each case is a separate @Test / parameterized row.
 *   - Magic numbers: docstring example and boundary cases use descriptive names.
 *   - Eager test: partitioned into nested classes by input category.
 *
 * Branch-coverage targets in Solution.truncateNumber:
 *   - The single-line expression `number % 1.0` has no branches; coverage here is
 *     purely behavioral (result == 0 vs result != 0).
 *   - Behavioral paths: integer input (fraction == 0), fraction < 0.5, fraction >= 0.5.
 */
public class HumanEval_002_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Docstring examples")
    class DocstringExamples {
        @Test @DisplayName("truncateNumber(3.5) == 0.5")
        void threePointFive() { assertEquals(0.5, s.truncateNumber(3.5), 1e-9); }
    }

    @Nested
    @DisplayName("Exact integer inputs — fractional part is zero")
    class ExactIntegers {
        @ParameterizedTest(name = "truncateNumber({0}) == 0.0")
        @CsvSource({"1.0", "2.0", "100.0", "1000000.0"})
        void exactInteger(double v) { assertEquals(0.0, s.truncateNumber(v), 1e-9); }
    }

    @Nested
    @DisplayName("Small fractional part (< 0.5)")
    class SmallFraction {
        @Test @DisplayName("truncateNumber(1.33) == 0.33")
        void oneThreeThree() { assertEquals(0.33, s.truncateNumber(1.33), 1e-9); }

        @Test @DisplayName("truncateNumber(123.456) == 0.456")
        void largeIntPart() { assertEquals(0.456, s.truncateNumber(123.456), 1e-9); }
    }

    @Nested
    @DisplayName("Large fractional part (>= 0.5)")
    class LargeFraction {
        @Test @DisplayName("truncateNumber(0.5) == 0.5")
        void zeroPointFive() { assertEquals(0.5, s.truncateNumber(0.5), 1e-9); }

        @Test @DisplayName("truncateNumber(7.9) == 0.9")
        void sevenPointNine() { assertEquals(0.9, s.truncateNumber(7.9), 1e-9); }
    }

    @Nested
    @DisplayName("Result invariants")
    class Invariants {
        @Test @DisplayName("Result is always in [0, 1)")
        void resultInRange() {
            double[] inputs = {1.0, 1.5, 0.5, 99.999, 0.001};
            for (double v : inputs) {
                double frac = s.truncateNumber(v);
                assertTrue(frac >= 0.0 && frac < 1.0,
                        "Expected [0,1) but got " + frac + " for input " + v);
            }
        }

        @Test @DisplayName("floor(n) + truncateNumber(n) == n (within floating-point tolerance)")
        void addedBackEqualsOriginal() {
            double[] inputs = {3.5, 1.33, 123.456, 7.9};
            for (double v : inputs) {
                assertEquals(v, Math.floor(v) + s.truncateNumber(v), 1e-9);
            }
        }
    }
}
