import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Manual black-box JUnit 6 suite for HumanEval_097.
 * Materializes the equivalence classes and boundaries from
 * tests/manual_tests/claude/HumanEval_097_blackbox.md.
 */
public class HumanEval_097_ManualTest {

    private final Solution s = new Solution();

    // V1 — both single-digit positives
    @Test @DisplayName("V1: multiply(3, 7) -> 21")
    void v1_singleDigits() { assertEquals(21, s.multiply(3, 7)); }

    // V2 — multi-digit positives
    @Test @DisplayName("V2: multiply(148, 412) -> 16 (dataset)")
    void v2_multiDigit_a() { assertEquals(16, s.multiply(148, 412)); }

    // V3 — multi-digit positives
    @Test @DisplayName("V3: multiply(19, 28) -> 72 (dataset)")
    void v3_multiDigit_b() { assertEquals(72, s.multiply(19, 28)); }

    // V4 — a is a multiple of 10
    @Test @DisplayName("V4: multiply(2020, 1851) -> 0 (dataset, a is multiple of 10)")
    void v4_aMultipleOfTen() { assertEquals(0, s.multiply(2020, 1851)); }

    // V5 — b is a multiple of 10
    @Test @DisplayName("V5: multiply(7, 30) -> 0 (b is multiple of 10)")
    void v5_bMultipleOfTen() { assertEquals(0, s.multiply(7, 30)); }

    // V6 — both multiples of 10
    @Test @DisplayName("V6: multiply(50, 80) -> 0 (both multiples of 10)")
    void v6_bothMultiplesOfTen() { assertEquals(0, s.multiply(50, 80)); }

    // V7 — a is zero
    @Test @DisplayName("V7: multiply(0, 1) -> 0 (dataset)")
    void v7_aIsZero() { assertEquals(0, s.multiply(0, 1)); }

    // V8 — both zero
    @Test @DisplayName("V8: multiply(0, 0) -> 0 (dataset)")
    void v8_bothZero() { assertEquals(0, s.multiply(0, 0)); }

    // V9 — a > 0, b < 0 (dataset sign-insensitivity probe)
    @Test @DisplayName("V9: multiply(14, -15) -> 20 (dataset)")
    void v9_negB() { assertEquals(20, s.multiply(14, -15)); }

    // V10 — a < 0, b > 0
    @Test @DisplayName("V10: multiply(-14, 15) -> 20 (sign-mirror of V9)")
    void v10_negA() { assertEquals(20, s.multiply(-14, 15)); }

    // V11 — both negative
    @Test @DisplayName("V11: multiply(-14, -15) -> 20")
    void v11_bothNeg() { assertEquals(20, s.multiply(-14, -15)); }

    // V12 — negative single digits
    @Test @DisplayName("V12: multiply(-3, -7) -> 21")
    void v12_negSingleDigits() { assertEquals(21, s.multiply(-3, -7)); }

    // V13 — largest single-digit unit product
    @Test @DisplayName("V13: multiply(9, 9) -> 81 (max unit product)")
    void v13_maxUnitProduct() { assertEquals(81, s.multiply(9, 9)); }

    // V14 — higher-order digits irrelevant
    @Test @DisplayName("V14a: multiply(123, 1007) -> 21 (only units 3 and 7 matter)")
    void v14_higherOrderIrrelevant_a() { assertEquals(21, s.multiply(123, 1007)); }

    @Test @DisplayName("V14b: multiply(13, 17) -> 21 (units 3 and 7)")
    void v14_higherOrderIrrelevant_b() { assertEquals(21, s.multiply(13, 17)); }

    // V15 — negative multiple of 10
    @Test @DisplayName("V15: multiply(-2020, 1851) -> 0 (negative multiple of 10)")
    void v15_negMultipleOfTen() { assertEquals(0, s.multiply(-2020, 1851)); }

    // V16 — extreme magnitudes
    @Test @DisplayName("V16: multiply(99999, -99999) -> 81 (extreme magnitudes, no overflow)")
    void v16_extremeMagnitudes() { assertEquals(81, s.multiply(99999, -99999)); }

    // V17 — dataset parity
    @Test @DisplayName("V17: multiply(76, 67) -> 42 (dataset)")
    void v17_dataset() { assertEquals(42, s.multiply(76, 67)); }

    // V18 — dataset parity
    @Test @DisplayName("V18: multiply(17, 27) -> 49 (dataset)")
    void v18_dataset() { assertEquals(49, s.multiply(17, 27)); }
}
