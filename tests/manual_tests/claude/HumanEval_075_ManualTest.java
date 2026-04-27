import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class HumanEval_075_ManualTest {

    private final Solution s = new Solution();

    // V1 — single prime (1 factor)
    @Test @DisplayName("V1: isMultiplyPrime(2) == false (1 factor)")
    void v1_two() { assertFalse(s.isMultiplyPrime(2)); }

    @Test @DisplayName("V1: isMultiplyPrime(3) == false (1 factor)")
    void v1_three() { assertFalse(s.isMultiplyPrime(3)); }

    @Test @DisplayName("V1: isMultiplyPrime(5) == false (1 factor)")
    void v1_five() { assertFalse(s.isMultiplyPrime(5)); }

    // V2 — product of 2 primes
    @Test @DisplayName("V2: isMultiplyPrime(4) == false (2*2)")
    void v2_four() { assertFalse(s.isMultiplyPrime(4)); }

    @Test @DisplayName("V2: isMultiplyPrime(6) == false (2*3)")
    void v2_six() { assertFalse(s.isMultiplyPrime(6)); }

    @Test @DisplayName("V2: isMultiplyPrime(22) == false (2*11)")
    void v2_twentyTwo() { assertFalse(s.isMultiplyPrime(22)); }

    // V3 — product of 3 distinct primes
    @Test @DisplayName("V3: isMultiplyPrime(30) == true (2*3*5, docstring)")
    void v3_thirty() { assertTrue(s.isMultiplyPrime(30)); }

    @Test @DisplayName("V3: isMultiplyPrime(42) == true (2*3*7)")
    void v3_fortyTwo() { assertTrue(s.isMultiplyPrime(42)); }

    // V4 — product of 3 primes with repeats
    @Test @DisplayName("V4: isMultiplyPrime(8) == true (2*2*2)")
    void v4_eight() { assertTrue(s.isMultiplyPrime(8)); }

    @Test @DisplayName("V4: isMultiplyPrime(12) == true (2*2*3)")
    void v4_twelve() { assertTrue(s.isMultiplyPrime(12)); }

    @Test @DisplayName("V4: isMultiplyPrime(18) == true (2*3*3)")
    void v4_eighteen() { assertTrue(s.isMultiplyPrime(18)); }

    @Test @DisplayName("V4: isMultiplyPrime(27) == true (3*3*3)")
    void v4_twentySeven() { assertTrue(s.isMultiplyPrime(27)); }

    // V5 — product of 4+ primes
    @Test @DisplayName("V5: isMultiplyPrime(16) == false (2^4)")
    void v5_sixteen() { assertFalse(s.isMultiplyPrime(16)); }

    @Test @DisplayName("V5: isMultiplyPrime(90) == false (2*3*3*5)")
    void v5_ninety() { assertFalse(s.isMultiplyPrime(90)); }

    // V6 — near 100 boundary
    @Test @DisplayName("V6: isMultiplyPrime(98) == true (2*7*7)")
    void v6_ninetyEight() { assertTrue(s.isMultiplyPrime(98)); }
}
