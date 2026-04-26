import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Improved suite for HumanEval_039 (codex / GPT).
 *
 * Smells addressed:
 *   - Assertion roulette: each case is a separate @Test with a display name.
 *   - Magic numbers: expected values named in display names.
 *
 * Branch targets (inline primality check):
 *   - next < 2 branch: first Fibonacci iteration (next=1).
 *   - next % 2 == 0 && next != 2: even composites (8,21,34...) vs next==2.
 *   - For-loop body: composite odd Fibonacci (e.g. 21 divisible by 3).
 *   - prime=true path (count increment) and prime=false path (skip).
 *   - count == n: return triggered vs continue.
 */
public class HumanEval_039_ImprovedTest {

    private final Solution s = new Solution();

    @Test @DisplayName("n=1 -> 2 (first prime Fibonacci)")
    void n1() { assertEquals(2, s.primeFib(1)); }

    @Test @DisplayName("n=2 -> 3")
    void n2() { assertEquals(3, s.primeFib(2)); }

    @Test @DisplayName("n=3 -> 5")
    void n3() { assertEquals(5, s.primeFib(3)); }

    @Test @DisplayName("n=4 -> 13 (skips 8 as even composite)")
    void n4() { assertEquals(13, s.primeFib(4)); }

    @Test @DisplayName("n=5 -> 89")
    void n5() { assertEquals(89, s.primeFib(5)); }

    @Test @DisplayName("n=6 -> 233")
    void n6() { assertEquals(233, s.primeFib(6)); }

    @Test @DisplayName("n=7 -> 1597")
    void n7() { assertEquals(1597, s.primeFib(7)); }

    @Test @DisplayName("sequence is strictly increasing")
    void strictlyIncreasing() {
        int prev = s.primeFib(1);
        for (int i = 2; i <= 7; i++) {
            int curr = s.primeFib(i);
            assertTrue(curr > prev, "Expected strictly increasing at n=" + i);
            prev = curr;
        }
    }

    @Test @DisplayName("all results are prime Fibonacci numbers")
    void allResultsArePrimeFibonacci() {
        int[] expected = {2, 3, 5, 13, 89, 233, 1597};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], s.primeFib(i + 1));
        }
    }
}
