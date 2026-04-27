import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class HumanEval_039_ManualTest {

    private final Solution s = new Solution();

    // V1–V5 — docstring values
    @Test @DisplayName("V1: primeFib(1) == 2")
    void v1_first() { assertEquals(2, s.primeFib(1)); }

    @Test @DisplayName("V2: primeFib(2) == 3")
    void v2_second() { assertEquals(3, s.primeFib(2)); }

    @Test @DisplayName("V3: primeFib(3) == 5")
    void v3_third() { assertEquals(5, s.primeFib(3)); }

    @Test @DisplayName("V4: primeFib(4) == 13")
    void v4_fourth() { assertEquals(13, s.primeFib(4)); }

    @Test @DisplayName("V5: primeFib(5) == 89")
    void v5_fifth() { assertEquals(89, s.primeFib(5)); }

    // V6–V7 — beyond docstring
    @Test @DisplayName("V6: primeFib(6) == 233")
    void v6_sixth() { assertEquals(233, s.primeFib(6)); }

    @Test @DisplayName("V7: primeFib(7) == 1597")
    void v7_seventh() { assertEquals(1597, s.primeFib(7)); }

    // Invariants
    @Test @DisplayName("Invariant: every result is prime")
    void invariant_prime() {
        int[] ns = {1, 2, 3, 4, 5};
        for (int n : ns) assertTrue(isPrime(s.primeFib(n)), "primeFib(" + n + ") must be prime");
    }

    @Test @DisplayName("Invariant: every result is a Fibonacci number")
    void invariant_fibonacci() {
        int[] ns = {1, 2, 3, 4, 5};
        for (int n : ns) assertTrue(isFib(s.primeFib(n)), "primeFib(" + n + ") must be Fibonacci");
    }

    @Test @DisplayName("Boundary: non-prime Fibonacci 8 is skipped (primeFib(3)==5, primeFib(4)==13)")
    void boundary_eightSkipped() {
        assertEquals(5,  s.primeFib(3));
        assertEquals(13, s.primeFib(4));
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; (long) i * i <= n; i++) if (n % i == 0) return false;
        return true;
    }

    private boolean isFib(int n) {
        long a = 5L * n * n + 4, b = 5L * n * n - 4;
        long sqA = Math.round(Math.sqrt(a)), sqB = Math.round(Math.sqrt(b));
        return sqA * sqA == a || sqB * sqB == b;
    }
}
