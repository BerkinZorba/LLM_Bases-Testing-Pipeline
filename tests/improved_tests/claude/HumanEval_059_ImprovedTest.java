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
 * Improved suite for HumanEval_059 (largestPrimeFactor: largest prime divisor of n,
 * with n > 1 and not prime per the spec).
 *
 * Test-smell improvements over the dataset base test:
 *   - Assertion roulette: the dataset Main packed 5 booleans into a single
 *     List<Boolean> with one AssertionError; here every case is its own @Test
 *     with a descriptive name and an assertEquals carrying the expected value.
 *   - Magic numbers: every input is paired with a @DisplayName that explains
 *     the prime-factorisation under test (e.g., "63 = 3^2 * 7 -> 7").
 *   - Eager test: cases are partitioned into nested classes by behavioural
 *     concern (small composites, prime powers, dataset parity, sqrt-loop
 *     boundary, large remainder branch).
 *   - Conditional logic in tests: no loops/ifs in test bodies; values are
 *     listed explicitly so a failure points to the exact input.
 *
 * Branch-coverage targets in `Solution.largestPrimeFactor`:
 *   - Outer while `factor*factor <= x`:
 *       false-on-entry never happens for n > 1 (factor starts at 2, x >= 2),
 *       but the false-exit branch is covered by every input where some prime
 *       factor exceeds sqrt(n) (e.g., 6 -> 3 leaves x = 3, factor reaches 2,
 *       2*2 > 3 exits).
 *       true-many-times branch covered by inputs that have several small
 *       factors (e.g., 2048 = 2^11, 27 = 3^3).
 *   - Inner while `x % factor == 0`:
 *       true (divisor found): every composite input.
 *       false (factor does not divide x): inputs with non-trivial factor
 *       progression like 15 = 3*5 (factor=2 misses), 63 = 3^2*7 (factor=2
 *       misses), 13195 = 5*7*13*29.
 *   - Final `if (x > 1)`:
 *       true (a prime remainder larger than sqrt(original n) survives the
 *       loop): 15 -> 5, 13195 -> 29, 22 -> 11, 9991 -> 103.
 *       false (n was a perfect prime power smaller than sqrt(original n)
 *       e.g., 4, 8, 9, 27, 2048): the inner while reduces x to 1.
 */
public class HumanEval_059_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Dataset parity (mirrors Main.java exactly)")
    class DatasetParity {
        @Test
        @DisplayName("15 = 3 * 5 -> 5")
        void d15() { assertEquals(5, s.largestPrimeFactor(15)); }

        @Test
        @DisplayName("27 = 3^3 -> 3")
        void d27() { assertEquals(3, s.largestPrimeFactor(27)); }

        @Test
        @DisplayName("63 = 3^2 * 7 -> 7")
        void d63() { assertEquals(7, s.largestPrimeFactor(63)); }

        @Test
        @DisplayName("330 = 2 * 3 * 5 * 11 -> 11")
        void d330() { assertEquals(11, s.largestPrimeFactor(330)); }

        @Test
        @DisplayName("13195 = 5 * 7 * 13 * 29 -> 29")
        void d13195() { assertEquals(29, s.largestPrimeFactor(13195)); }
    }

    @Nested
    @DisplayName("Smallest composites (loop-entry boundaries)")
    class SmallestComposites {
        @Test
        @DisplayName("4 = 2^2 -> 2 (smallest composite, prime square)")
        void four() { assertEquals(2, s.largestPrimeFactor(4)); }

        @Test
        @DisplayName("6 = 2 * 3 -> 3 (smallest semiprime with distinct primes)")
        void six() { assertEquals(3, s.largestPrimeFactor(6)); }

        @Test
        @DisplayName("8 = 2^3 -> 2 (cube of smallest prime)")
        void eight() { assertEquals(2, s.largestPrimeFactor(8)); }

        @Test
        @DisplayName("9 = 3^2 -> 3 (next prime square)")
        void nine() { assertEquals(3, s.largestPrimeFactor(9)); }
    }

    @Nested
    @DisplayName("Pure prime powers (final-if false branch)")
    class PrimePowers {
        @Test
        @DisplayName("2048 = 2^11 -> 2 (prompt example, deep inner-while)")
        void p2048() { assertEquals(2, s.largestPrimeFactor(2048)); }

        @Test
        @DisplayName("3125 = 5^5 -> 5")
        void p3125() { assertEquals(5, s.largestPrimeFactor(3125)); }

        @Test
        @DisplayName("2401 = 7^4 -> 7 (factor advances past 2,3,5)")
        void p2401() { assertEquals(7, s.largestPrimeFactor(2401)); }

        @Test
        @DisplayName("1024 = 2^10 -> 2")
        void p1024() { assertEquals(2, s.largestPrimeFactor(1024)); }
    }

    @Nested
    @DisplayName("Semiprimes with a large prime > sqrt(n) (final-if true branch)")
    class LargeRemainder {
        @Test
        @DisplayName("22 = 2 * 11 -> 11")
        void s22() { assertEquals(11, s.largestPrimeFactor(22)); }

        @Test
        @DisplayName("26 = 2 * 13 -> 13")
        void s26() { assertEquals(13, s.largestPrimeFactor(26)); }

        @Test
        @DisplayName("9991 = 97 * 103 -> 103 (both factors near sqrt)")
        void s9991() { assertEquals(103, s.largestPrimeFactor(9991)); }

        @Test
        @DisplayName("1000003*2 too large for int — use 2 * 9973 = 19946 -> 9973")
        void s19946() { assertEquals(9973, s.largestPrimeFactor(19946)); }
    }

    @Nested
    @DisplayName("Sqrt-loop boundary cases")
    class SqrtBoundary {
        @Test
        @DisplayName("25 = 5^2 -> 5 (factor*factor == x exactly)")
        void sq25() { assertEquals(5, s.largestPrimeFactor(25)); }

        @Test
        @DisplayName("49 = 7^2 -> 7 (next perfect prime square)")
        void sq49() { assertEquals(7, s.largestPrimeFactor(49)); }

        @Test
        @DisplayName("121 = 11^2 -> 11")
        void sq121() { assertEquals(11, s.largestPrimeFactor(121)); }

        @Test
        @DisplayName("50 = 2 * 5^2 -> 5 (mixed: small factor then perfect square)")
        void sq50() { assertEquals(5, s.largestPrimeFactor(50)); }
    }

    @Nested
    @DisplayName("Multi-prime composites (inner-while false branch many times)")
    class MultiPrime {
        @Test
        @DisplayName("2*3*5*7 = 210 -> 7")
        void m210() { assertEquals(7, s.largestPrimeFactor(210)); }

        @Test
        @DisplayName("2*3*5*7*11 = 2310 -> 11")
        void m2310() { assertEquals(11, s.largestPrimeFactor(2310)); }

        @Test
        @DisplayName("2^4 * 3^2 * 5 = 720 -> 5 (largest factor is mid-sized)")
        void m720() { assertEquals(5, s.largestPrimeFactor(720)); }
    }

    @Nested
    @DisplayName("Functional invariants")
    class Invariants {
        @Test
        @DisplayName("Multiplying n by a smaller prime never decreases the answer")
        void multiplyingBySmallerPrimeIsMonotone() {
            assertAll(
                    () -> assertEquals(7, s.largestPrimeFactor(63)),
                    () -> assertEquals(7, s.largestPrimeFactor(126)),  // 2 * 63
                    () -> assertEquals(7, s.largestPrimeFactor(189))   // 3 * 63
            );
        }

        @Test
        @DisplayName("Multiplying n by a larger prime raises the answer to that prime")
        void multiplyingByLargerPrimeRaisesAnswer() {
            assertAll(
                    () -> assertEquals(7, s.largestPrimeFactor(63)),
                    () -> assertEquals(11, s.largestPrimeFactor(63 * 11)),
                    () -> assertEquals(13, s.largestPrimeFactor(63 * 13))
            );
        }
    }
}
