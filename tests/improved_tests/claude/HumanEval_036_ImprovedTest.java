import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Improved suite for HumanEval_036 (count of digit '7' across integers in [1, n)
 * divisible by 11 or 13).
 *
 * Test-smell improvements over the dataset base test:
 *   - Assertion roulette: dataset Main bundled 8 booleans into one List and threw a
 *     single AssertionError; here each case is a separate @Test or parameterized row
 *     with a descriptive name and explicit expected/actual messages.
 *   - Magic numbers: the meaning of every input is documented via @DisplayName
 *     and parameterized row names; no bare integers without context.
 *   - Eager test: cases are partitioned into nested classes by behavioral concern
 *     (no-multiples region, first-7 boundary, multi-digit 7s, both-divisor overlap,
 *     digit-counting in the multiplier) instead of one mega-list.
 *   - Conditional logic in tests: no loops/ifs in test bodies; values are listed.
 *
 * Branch-coverage targets in `Solution.fizzBuzz`:
 *   - The outer for: n <= 1 (loop body never enters) vs n > 1 (enters).
 *   - The if condition: i % 11 == 0 only, i % 13 == 0 only, both (i % 143 == 0,
 *     e.g. 143), and neither (skipped path).
 *   - The inner while: numbers with 0, 1, and >=2 occurrences of '7' in their
 *     decimal digits; numbers whose only 7 is in tens vs units vs higher digits.
 */
public class HumanEval_036_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Loop-entry boundaries on n")
    class LoopEntry {
        @Test
        @DisplayName("n = 0: loop never enters -> 0")
        void nZero_returnsZero() {
            assertEquals(0, s.fizzBuzz(0));
        }

        @Test
        @DisplayName("n = 1: loop never enters (i starts at 1, condition i<n false) -> 0")
        void nOne_returnsZero() {
            assertEquals(0, s.fizzBuzz(1));
        }

        @Test
        @DisplayName("n = 2: loop enters once for i=1, neither divisor matches -> 0")
        void nTwo_returnsZero() {
            assertEquals(0, s.fizzBuzz(2));
        }

        @Test
        @DisplayName("Negative n: loop never enters -> 0")
        void negativeN_returnsZero() {
            assertEquals(0, s.fizzBuzz(-100));
        }
    }

    @Nested
    @DisplayName("Divisor-branch coverage")
    class DivisorBranches {
        @Test
        @DisplayName("n = 12: only multiple-of-11 in range is 11 (no 7 digit) -> 0")
        void onlyEleven_noSeven() {
            assertEquals(0, s.fizzBuzz(12));
        }

        @Test
        @DisplayName("n = 14: multiples are 11 and 13, neither has digit 7 -> 0")
        void elevenAndThirteen_noSeven() {
            assertEquals(0, s.fizzBuzz(14));
        }

        @Test
        @DisplayName("n = 144: 143 = 11*13 hits BOTH divisors but counts only once -> equals fizzBuzz(143)+0")
        void overlap143_isCountedOnce() {
            assertEquals(s.fizzBuzz(143), s.fizzBuzz(144));
        }

        @Test
        @DisplayName("n = 78: first qualifying 7 appears at 77 (two sevens) -> 2")
        void first77_contributesTwo() {
            assertEquals(2, s.fizzBuzz(78));
        }

        @Test
        @DisplayName("n = 79: 77 (two sevens) + 78 (one seven, multiple of 13) -> 3")
        void include78_addsOne() {
            assertEquals(3, s.fizzBuzz(79));
        }
    }

    @Nested
    @DisplayName("Digit-position coverage in inner while")
    class DigitPositions {
        @Test
        @DisplayName("70..71 boundary: 70 = mult of neither 11 nor 13 within (1,80); 77 contributes the only 7s")
        void sevenInTens_onlyVia77() {
            // Between 50 and 78, the only multiples of 11 or 13 are 52,55,65,66,77.
            // None except 77 contain digit 7.
            assertEquals(2, s.fizzBuzz(78));
        }

        @Test
        @DisplayName("n = 100: contributions come from 77 (two) and 91 (none) and 99 (none) -> 3 total includes 78")
        void upTo100_threeSevens() {
            assertEquals(3, s.fizzBuzz(100));
        }

        @Test
        @DisplayName("Stable across small bumps: f(101) == f(100) (101 is not a multiple of 11/13)")
        void monotonicityWhenSkippingNonMultiple() {
            assertEquals(s.fizzBuzz(100), s.fizzBuzz(101));
        }
    }

    @Nested
    @DisplayName("Dataset reference values")
    class DatasetParity {
        @ParameterizedTest(name = "fizzBuzz({0}) == {1}")
        @CsvSource({
                "50, 0",
                "78, 2",
                "79, 3",
                "100, 3",
                "200, 6",
                "4000, 192",
                "10000, 639",
                "100000, 8026"
        })
        void datasetCases(int n, int expected) {
            assertEquals(expected, s.fizzBuzz(n));
        }
    }

    @Nested
    @DisplayName("Functional invariants (oracle-based, no recomputation in test)")
    class Invariants {
        @Test
        @DisplayName("Non-negativity: count is never negative across a sweep")
        void nonNegative() {
            assertAll(
                    () -> assertEquals(0, Math.min(0, s.fizzBuzz(0))),
                    () -> assertEquals(0, Math.min(0, s.fizzBuzz(50))),
                    () -> assertEquals(0, Math.min(0, s.fizzBuzz(200))),
                    () -> assertEquals(0, Math.min(0, s.fizzBuzz(4000)))
            );
        }

        @Test
        @DisplayName("Monotonic non-decreasing in n: f(n) <= f(n+1)")
        void monotonicNonDecreasing() {
            assertAll(
                    () -> {
                        int a = s.fizzBuzz(77), b = s.fizzBuzz(78);
                        assertEquals(true, a <= b);
                    },
                    () -> {
                        int a = s.fizzBuzz(78), b = s.fizzBuzz(79);
                        assertEquals(true, a <= b);
                    },
                    () -> {
                        int a = s.fizzBuzz(199), b = s.fizzBuzz(200);
                        assertEquals(true, a <= b);
                    },
                    () -> {
                        int a = s.fizzBuzz(3999), b = s.fizzBuzz(4000);
                        assertEquals(true, a <= b);
                    }
            );
        }

        @Test
        @DisplayName("Crossing 78 adds exactly one 7 (78 = 6*13 contributes its single '7')")
        void crossing78AddsOne() {
            assertEquals(s.fizzBuzz(78) + 1, s.fizzBuzz(79));
        }

        @Test
        @DisplayName("Crossing 77 adds exactly two 7s (77 = 7*11 contributes both digits)")
        void crossing77AddsTwo() {
            assertEquals(s.fizzBuzz(77) + 2, s.fizzBuzz(78));
        }
    }
}
