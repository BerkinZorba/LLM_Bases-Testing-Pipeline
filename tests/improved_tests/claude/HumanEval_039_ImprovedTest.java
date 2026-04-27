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
 * Improved suite for HumanEval_039 (primeFib).
 *
 * Test-smell improvements:
 *   - Assertion roulette: each case is a separate test or parameterized row.
 *   - Magic numbers: display names document the Fibonacci-prime sequence.
 *   - Eager test: cases partitioned by concern (docstring values, sequence properties,
 *     primality invariants, Fibonacci invariants).
 *
 * Branch-coverage targets in Solution.primeFib / isPrime:
 *   - isPrime: n < 2 (false), n == 2 (true), n == even (false), trial-loop not-entered
 *     (perfect square: 4), loop-exits-early (composite), loop-completes (prime).
 *   - primeFib outer while: count increments until n is reached; exercises several
 *     non-prime Fibonacci numbers (8, 21, 34, 55) and prime ones (2, 3, 5, 13, 89).
 */
public class HumanEval_039_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Docstring examples")
    class DocstringExamples {
        @ParameterizedTest(name = "primeFib({0}) == {1}")
        @CsvSource({"1,2", "2,3", "3,5", "4,13", "5,89"})
        void docstringCases(int n, int expected) { assertEquals(expected, s.primeFib(n)); }
    }

    @Nested
    @DisplayName("Extended sequence values")
    class ExtendedSequence {
        @Test @DisplayName("primeFib(6) == 233 (next prime Fibonacci after 89)")
        void sixth() { assertEquals(233, s.primeFib(6)); }

        @Test @DisplayName("primeFib(7) == 1597")
        void seventh() { assertEquals(1597, s.primeFib(7)); }
    }

    @Nested
    @DisplayName("Primality branch: isPrime helper coverage")
    class PrimalityBranch {
        @Test @DisplayName("Non-prime Fibonacci numbers are skipped: 8, 21, 34, 55")
        void nonPrimeFibsSkipped() {
            // primeFib(3) == 5 and primeFib(4) == 13 confirms 8 is skipped
            assertEquals(5,  s.primeFib(3));
            assertEquals(13, s.primeFib(4));
        }

        @Test @DisplayName("Result is always prime")
        void resultsArePrime() {
            int[] primes = {s.primeFib(1), s.primeFib(2), s.primeFib(3),
                            s.primeFib(4), s.primeFib(5)};
            for (int p : primes) assertTrue(isPrime(p), p + " should be prime");
        }
    }

    @Nested
    @DisplayName("Fibonacci invariant")
    class FibInvariant {
        @Test @DisplayName("Each result is a Fibonacci number")
        void resultsAreFibonacci() {
            int[] results = {s.primeFib(1), s.primeFib(2), s.primeFib(3),
                             s.primeFib(4), s.primeFib(5)};
            for (int v : results) assertTrue(isFibonacci(v), v + " should be Fibonacci");
        }
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; (long) i * i <= n; i++) if (n % i == 0) return false;
        return true;
    }

    private boolean isFibonacci(int n) {
        // n is Fibonacci iff 5*n*n ± 4 is a perfect square
        long a = 5L * n * n + 4, b = 5L * n * n - 4;
        long sqA = (long) Math.round(Math.sqrt(a));
        long sqB = (long) Math.round(Math.sqrt(b));
        return sqA * sqA == a || sqB * sqB == b;
    }
}
