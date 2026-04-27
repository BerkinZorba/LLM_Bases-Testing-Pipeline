import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

/**
 * Improved suite for HumanEval_075 (codex / GPT).
 *
 * Smells addressed:
 *   - Assertion roulette: each case is a separate @Test with a display name.
 *   - Magic numbers: factorizations explained in display names.
 *   - Eager test: grouped by concern (true cases, false cases).
 *
 * Branch targets:
 *   - Outer for-loop: entered (a >= 2) and exited (a fully factored).
 *   - While-loop: entered (i divides a) and skipped (i doesn't divide a).
 *   - i < 2 check: always false (i starts at 2) — structurally unreachable true side.
 *   - i % 2 == 0 && i != 2: unreachable for valid inputs (composite i never divides remaining a).
 *   - j-loop: executed for larger primes (i=11, j=3 where j*j < i).
 *   - prime=true path (count++) vs prime=false path (return false): false path unreachable.
 *   - Final return: count==3 && a==1 both true and false.
 */
public class HumanEval_075_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("True cases — product of exactly 3 primes")
    class TrueCases {
        @Test @DisplayName("30 = 2 * 3 * 5 (docstring)")
        void thirty() { assertTrue(s.isMultiplyPrime(30)); }

        @Test @DisplayName("8 = 2 * 2 * 2 (repeated prime)")
        void eight() { assertTrue(s.isMultiplyPrime(8)); }

        @Test @DisplayName("12 = 2 * 2 * 3")
        void twelve() { assertTrue(s.isMultiplyPrime(12)); }

        @Test @DisplayName("18 = 2 * 3 * 3")
        void eighteen() { assertTrue(s.isMultiplyPrime(18)); }

        @Test @DisplayName("50 = 2 * 5 * 5")
        void fifty() { assertTrue(s.isMultiplyPrime(50)); }

        @Test @DisplayName("66 = 2 * 3 * 11 (exercises j-loop for i=11)")
        void sixtySix() { assertTrue(s.isMultiplyPrime(66)); }
    }

    @Nested
    @DisplayName("False cases — not a product of exactly 3 primes")
    class FalseCases {
        @Test @DisplayName("22 = 2 * 11 (only 2 prime factors)")
        void twentyTwo() { assertFalse(s.isMultiplyPrime(22)); }

        @Test @DisplayName("4 = 2 * 2 (only 2 prime factors)")
        void four() { assertFalse(s.isMultiplyPrime(4)); }

        @Test @DisplayName("3 is prime (only 1 factor)")
        void three() { assertFalse(s.isMultiplyPrime(3)); }

        @Test @DisplayName("2 is prime (only 1 factor)")
        void two() { assertFalse(s.isMultiplyPrime(2)); }

        @Test @DisplayName("16 = 2^4 (4 prime factors)")
        void sixteen() { assertFalse(s.isMultiplyPrime(16)); }

        @Test @DisplayName("60 = 2 * 2 * 3 * 5 (4 prime factors)")
        void sixty() { assertFalse(s.isMultiplyPrime(60)); }
    }
}
