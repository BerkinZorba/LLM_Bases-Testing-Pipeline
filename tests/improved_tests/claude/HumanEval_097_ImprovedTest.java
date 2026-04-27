import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Improved suite for HumanEval_097
 * (multiply: product of the unit digits of two integers, sign-insensitive).
 *
 * Test-smell improvements over the dataset base test:
 *   - Assertion roulette: dataset Main packed 8 booleans into a single
 *     List<Boolean> with one AssertionError; here every case is its own
 *     @Test with a descriptive name and per-case expected value.
 *   - Magic numbers: every input pair is paired with @DisplayName explaining
 *     the behavioral concern (sign of a, sign of b, units involve zero,
 *     multiples of ten, ...).
 *   - Eager test: cases are partitioned into nested classes by behavioral
 *     concern (positives, zero-valued unit digit, negative inputs, dataset
 *     parity, invariants).
 *   - Conditional logic in tests: no loops/ifs in test bodies; values are
 *     listed explicitly.
 *
 * Branch / path targets in `Solution.multiply`:
 *   - `Math.abs(a % 10)`: a%10 >= 0 (a >= 0) and a%10 < 0 (a < 0).
 *   - `Math.abs(b % 10)`: b%10 >= 0 (b >= 0) and b%10 < 0 (b < 0).
 *   - Product = 0 path: at least one of |a%10|, |b%10| is 0
 *     (units 0 either via `a == 0` or via `a` being a multiple of 10).
 *   - Product != 0 path: both unit digits non-zero.
 *   - Sign-cross matrix: (++), (+-), (-+), (--) all yield non-negative result.
 */
public class HumanEval_097_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Both inputs non-negative (a%10 >= 0, b%10 >= 0)")
    class BothNonNegative {
        @Test
        @DisplayName("Single-digit pair: 3 * 7 -> 21 (smallest non-trivial)")
        void singleDigits() { assertEquals(21, s.multiply(3, 7)); }

        @Test
        @DisplayName("Multi-digit positives: units 8 * 2 = 16 (148, 412)")
        void datasetA() { assertEquals(16, s.multiply(148, 412)); }

        @Test
        @DisplayName("Multi-digit positives: units 9 * 8 = 72 (19, 28)")
        void datasetB() { assertEquals(72, s.multiply(19, 28)); }

        @Test
        @DisplayName("Largest single-digit unit product: 9 * 9 = 81")
        void maxUnitProduct() { assertEquals(81, s.multiply(9, 9)); }
    }

    @Nested
    @DisplayName("Zero-valued unit digit short-circuits product to 0")
    class ZeroUnit {
        @Test
        @DisplayName("a == 0: 0 * 1 -> 0 (dataset)")
        void aIsZero() { assertEquals(0, s.multiply(0, 1)); }

        @Test
        @DisplayName("Both zero: 0 * 0 -> 0 (dataset)")
        void bothZero() { assertEquals(0, s.multiply(0, 0)); }

        @Test
        @DisplayName("a is a multiple of 10: 2020 * 1851 -> unit 0 * unit 1 = 0 (dataset)")
        void aMultipleOfTen() { assertEquals(0, s.multiply(2020, 1851)); }

        @Test
        @DisplayName("b is a multiple of 10: 7 * 30 -> unit 7 * unit 0 = 0")
        void bMultipleOfTen() { assertEquals(0, s.multiply(7, 30)); }

        @Test
        @DisplayName("Both multiples of 10: 50 * 80 -> 0 * 0 = 0")
        void bothMultiplesOfTen() { assertEquals(0, s.multiply(50, 80)); }
    }

    @Nested
    @DisplayName("Negative inputs (Math.abs branch on the negative remainder)")
    class NegativeInputs {
        @Test
        @DisplayName("Only b negative: 14 * -15 -> 4 * 5 = 20 (dataset)")
        void onlyBNegative_dataset() { assertEquals(20, s.multiply(14, -15)); }

        @Test
        @DisplayName("Only a negative: -14 * 15 -> 4 * 5 = 20 (sign-mirror of dataset)")
        void onlyANegative() { assertEquals(20, s.multiply(-14, 15)); }

        @Test
        @DisplayName("Both negative: -14 * -15 -> 4 * 5 = 20")
        void bothNegative() { assertEquals(20, s.multiply(-14, -15)); }

        @Test
        @DisplayName("Negative single digits: -3 * -7 -> 3 * 7 = 21")
        void negativeSingleDigits() { assertEquals(21, s.multiply(-3, -7)); }

        @Test
        @DisplayName("Negative multiple of 10: -2020 * 1851 -> 0 * 1 = 0")
        void negativeMultipleOfTen() { assertEquals(0, s.multiply(-2020, 1851)); }
    }

    @Nested
    @DisplayName("Dataset reference values")
    class DatasetParity {
        @Test
        @DisplayName("multiply(76, 67) -> 6 * 7 = 42")
        void d1() { assertEquals(42, s.multiply(76, 67)); }

        @Test
        @DisplayName("multiply(17, 27) -> 7 * 7 = 49")
        void d2() { assertEquals(49, s.multiply(17, 27)); }

        @Test
        @DisplayName("multiply(0, 1) -> 0")
        void d3() { assertEquals(0, s.multiply(0, 1)); }
    }

    @Nested
    @DisplayName("Functional invariants (oracle-based, no recomputation in test)")
    class Invariants {
        @Test
        @DisplayName("Commutativity: multiply(a,b) == multiply(b,a)")
        void commutativity() {
            assertAll(
                    () -> assertEquals(s.multiply(148, 412), s.multiply(412, 148)),
                    () -> assertEquals(s.multiply(19, 28), s.multiply(28, 19)),
                    () -> assertEquals(s.multiply(14, -15), s.multiply(-15, 14)),
                    () -> assertEquals(s.multiply(0, 1), s.multiply(1, 0))
            );
        }

        @Test
        @DisplayName("Sign-insensitivity: result depends only on |a%10| and |b%10|")
        void signInsensitive() {
            int base = s.multiply(14, 15);
            assertAll(
                    () -> assertEquals(base, s.multiply(-14, 15)),
                    () -> assertEquals(base, s.multiply(14, -15)),
                    () -> assertEquals(base, s.multiply(-14, -15))
            );
        }

        @Test
        @DisplayName("Higher-order digits are ignored: multiply(a, b) == multiply(a + 10*k, b + 10*m)")
        void onlyUnitsMatter() {
            assertAll(
                    () -> assertEquals(s.multiply(3, 7), s.multiply(13, 17)),
                    () -> assertEquals(s.multiply(3, 7), s.multiply(123, 1007)),
                    () -> assertEquals(s.multiply(0, 5), s.multiply(1000, 1235))
            );
        }

        @Test
        @DisplayName("Result is always non-negative (any sign mix of inputs)")
        void alwaysNonNegative() {
            assertAll(
                    () -> assertEquals(20, s.multiply(14, -15)),
                    () -> assertEquals(20, s.multiply(-14, 15)),
                    () -> assertEquals(20, s.multiply(-14, -15)),
                    () -> assertEquals(0, s.multiply(-2020, -1851))
            );
        }

        @Test
        @DisplayName("Result is bounded: 0 <= multiply(a,b) <= 81")
        void boundedRange() {
            assertEquals(81, s.multiply(9, 9));
            assertEquals(81, s.multiply(-9, -9));
            assertEquals(81, s.multiply(99999, -99999));
            assertEquals(0, s.multiply(0, 0));
        }
    }
}
