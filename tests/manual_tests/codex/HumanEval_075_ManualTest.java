import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manual black-box tests for HumanEval_075 (codex / GPT).
 * Cases derived from HumanEval_075_blackbox.md.
 */
public class HumanEval_075_ManualTest {

    private final Solution s = new Solution();

    @Test @DisplayName("V_T1: 30 = 2*3*5 -> true")
    void vt1_thirty() { assertTrue(s.isMultiplyPrime(30)); }

    @Test @DisplayName("V_T2: 8 = 2*2*2 -> true")
    void vt2_eight() { assertTrue(s.isMultiplyPrime(8)); }

    @Test @DisplayName("V_T3: 12 = 2*2*3 -> true")
    void vt3_twelve() { assertTrue(s.isMultiplyPrime(12)); }

    @Test @DisplayName("V_T4: 18 = 2*3*3 -> true")
    void vt4_eighteen() { assertTrue(s.isMultiplyPrime(18)); }

    @Test @DisplayName("V_T5: 50 = 2*5*5 -> true")
    void vt5_fifty() { assertTrue(s.isMultiplyPrime(50)); }

    @Test @DisplayName("V_T6: 66 = 2*3*11 -> true (exercises j-loop)")
    void vt6_sixtySix() { assertTrue(s.isMultiplyPrime(66)); }

    @Test @DisplayName("V_F1: 22 = 2*11 -> false (only 2 factors)")
    void vf1_twentyTwo() { assertFalse(s.isMultiplyPrime(22)); }

    @Test @DisplayName("V_F2: 4 = 2*2 -> false (only 2 factors)")
    void vf2_four() { assertFalse(s.isMultiplyPrime(4)); }

    @Test @DisplayName("V_F3: 3 is prime -> false (1 factor)")
    void vf3_three() { assertFalse(s.isMultiplyPrime(3)); }

    @Test @DisplayName("V_F4: 2 is prime -> false (1 factor)")
    void vf4_two() { assertFalse(s.isMultiplyPrime(2)); }

    @Test @DisplayName("V_F5: 16 = 2^4 -> false (4 factors)")
    void vf5_sixteen() { assertFalse(s.isMultiplyPrime(16)); }

    @Test @DisplayName("V_F6: 60 = 2*2*3*5 -> false (4 factors)")
    void vf6_sixty() { assertFalse(s.isMultiplyPrime(60)); }

    @Test @DisplayName("Boundary: 8 is smallest product of 3 primes")
    void boundary_smallestTrue() { assertTrue(s.isMultiplyPrime(8)); }
}
