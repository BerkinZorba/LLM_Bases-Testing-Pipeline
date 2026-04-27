import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Manual black-box JUnit 6 suite for HumanEval_059.
 * Materializes the equivalence classes and boundaries from
 * tests/manual_tests/claude/HumanEval_059_blackbox.md.
 *
 * Spec-valid cases (V*) verify required behavior. Invalid/undefined-by-spec
 * cases (I*) pin observed behavior only — these rows are NOT correctness
 * claims for the spec but a record of how the verbatim Claude output behaves
 * on out-of-spec inputs.
 */
public class HumanEval_059_ManualTest {

    private final Solution s = new Solution();

    // V1 — smallest composite
    @Test @DisplayName("V1: 4 = 2^2 -> 2")
    void v1_smallestComposite() { assertEquals(2, s.largestPrimeFactor(4)); }

    // V2 — smallest semiprime distinct primes
    @Test @DisplayName("V2: 6 = 2*3 -> 3")
    void v2_smallestDistinctSemiprime() { assertEquals(3, s.largestPrimeFactor(6)); }

    // V3 — pure power of 2 (prompt example)
    @Test @DisplayName("V3: 2048 = 2^11 -> 2 (prompt example)")
    void v3_pow2_prompt() { assertEquals(2, s.largestPrimeFactor(2048)); }

    // V4 — pure prime power (dataset)
    @Test @DisplayName("V4: 27 = 3^3 -> 3 (dataset)")
    void v4_pow3_dataset() { assertEquals(3, s.largestPrimeFactor(27)); }

    // V5 — larger prime power
    @Test @DisplayName("V5: 2401 = 7^4 -> 7")
    void v5_pow7() { assertEquals(7, s.largestPrimeFactor(2401)); }

    // V6 — semiprime with one factor > sqrt(n) (dataset)
    @Test @DisplayName("V6: 15 = 3*5 -> 5 (dataset)")
    void v6_semiprime_dataset() { assertEquals(5, s.largestPrimeFactor(15)); }

    // V7 — composite with a large prime > sqrt(n)
    @Test @DisplayName("V7: 22 = 2*11 -> 11")
    void v7_largePrime() { assertEquals(11, s.largestPrimeFactor(22)); }

    // V8 — multi-prime (dataset)
    @Test @DisplayName("V8: 13195 = 5*7*13*29 -> 29 (dataset, prompt example)")
    void v8_multiPrime_dataset() { assertEquals(29, s.largestPrimeFactor(13195)); }

    // V9 — largest prime is small mid-sized composite
    @Test @DisplayName("V9: 720 = 2^4 * 3^2 * 5 -> 5")
    void v9_smallLargest() { assertEquals(5, s.largestPrimeFactor(720)); }

    // V10 — prime square exactly at sqrt boundary
    @Test @DisplayName("V10: 121 = 11^2 -> 11 (factor*factor == x)")
    void v10_primeSquareLarge() { assertEquals(11, s.largestPrimeFactor(121)); }

    // V11 — two near-sqrt primes
    @Test @DisplayName("V11: 9991 = 97*103 -> 103")
    void v11_twoLargePrimes() { assertEquals(103, s.largestPrimeFactor(9991)); }

    // V12 — dataset parity
    @Test @DisplayName("V12a: 63 = 3^2 * 7 -> 7 (dataset)")
    void v12_d63() { assertEquals(7, s.largestPrimeFactor(63)); }

    @Test @DisplayName("V12b: 330 = 2*3*5*11 -> 11 (dataset)")
    void v12_d330() { assertEquals(11, s.largestPrimeFactor(330)); }

    // Boundaries — sqrt-loop equality
    @Test @DisplayName("Boundary: 25 = 5^2 -> 5 (factor*factor == x exactly)")
    void boundary_25() { assertEquals(5, s.largestPrimeFactor(25)); }

    @Test @DisplayName("Boundary: 49 = 7^2 -> 7 (next perfect prime square)")
    void boundary_49() { assertEquals(7, s.largestPrimeFactor(49)); }

    // Inner-while exercise
    @Test @DisplayName("Boundary: 1024 = 2^10 -> 2 (deep inner-while)")
    void boundary_1024() { assertEquals(2, s.largestPrimeFactor(1024)); }

    // Pinned undefined-by-spec behaviors (I*) — observation only
    @Test @DisplayName("I1 (out of spec): n = 7 (prime) returns n itself (7)")
    void i1_primeReturnsItself() { assertEquals(7, s.largestPrimeFactor(7)); }

    @Test @DisplayName("I2 (out of spec): n = 1 returns 1 (loop never enters)")
    void i2_one() { assertEquals(1, s.largestPrimeFactor(1)); }

    @Test @DisplayName("I3 (out of spec): n = 0 returns 1 (loop guard false; x>1 false)")
    void i3_zero() { assertEquals(1, s.largestPrimeFactor(0)); }

    @Test @DisplayName("I4 (out of spec): n = -15 returns 1 (loop guard false; x>1 false)")
    void i4_negative() { assertEquals(1, s.largestPrimeFactor(-15)); }
}
