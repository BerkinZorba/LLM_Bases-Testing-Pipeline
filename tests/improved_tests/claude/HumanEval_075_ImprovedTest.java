import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

/**
 * Improved suite for HumanEval_075 (isMultiplyPrime).
 *
 * Test-smell improvements:
 *   - Assertion roulette: each case is a separate @Test or parameterized row.
 *   - Magic numbers: display names document factorizations.
 *   - Eager test: cases partitioned by prime-factor count (1, 2, 3, 4+).
 *
 * Branch-coverage targets in Solution.isMultiplyPrime:
 *   - Outer for-loop: trivially entered for any a >= 2.
 *   - Inner while: p divides a at least once (count increments) vs p does not divide.
 *   - count > 3 early return: a has more than 3 prime factors.
 *   - count == 3 final check: exactly 3 prime factors (return true) vs not (return false).
 */
public class HumanEval_075_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Exactly 3 prime factors — should return true")
    class ThreeFactors {
        @ParameterizedTest(name = "{0} = {1} -> true")
        @CsvSource({
                "30,  '2*3*5'",
                "8,   '2*2*2'",
                "12,  '2*2*3'",
                "18,  '2*3*3'",
                "27,  '3*3*3'",
                "50,  '2*5*5'",
                "45,  '3*3*5'",
                "75,  '3*5*5'"
        })
        void threeFactors(int a, String factorization) {
            assertTrue(s.isMultiplyPrime(a), a + " = " + factorization + " should be true");
        }
    }

    @Nested
    @DisplayName("Fewer than 3 prime factors — should return false")
    class FewerFactors {
        @Test @DisplayName("3 = prime (1 factor) -> false")
        void singlePrime() { assertFalse(s.isMultiplyPrime(3)); }

        @Test @DisplayName("4 = 2*2 (2 factors) -> false")
        void twoFactors() { assertFalse(s.isMultiplyPrime(4)); }

        @Test @DisplayName("22 = 2*11 (2 factors) -> false")
        void twentyTwo() { assertFalse(s.isMultiplyPrime(22)); }

        @Test @DisplayName("2 = prime (1 factor) -> false")
        void two() { assertFalse(s.isMultiplyPrime(2)); }
    }

    @Nested
    @DisplayName("More than 3 prime factors — should return false")
    class MoreThanThree {
        @Test @DisplayName("16 = 2*2*2*2 (4 factors) -> false")
        void sixteen() { assertFalse(s.isMultiplyPrime(16)); }

        @Test @DisplayName("48 = 2*2*2*2*3 (5 factors) -> false")
        void fortyEight() { assertFalse(s.isMultiplyPrime(48)); }
    }

    @Nested
    @DisplayName("Boundary near 100")
    class Boundary {
        @Test @DisplayName("98 = 2*7*7 (3 factors) -> true")
        void ninetyEight() { assertTrue(s.isMultiplyPrime(98)); }

        @Test @DisplayName("90 = 2*3*3*5 (4 factors) -> false")
        void ninety() { assertFalse(s.isMultiplyPrime(90)); }
    }
}
