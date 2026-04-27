import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_097_BaseTest {

    private final Solution s = new Solution();

    @Test
    void multiply_148_412_returns_16() {
        assertEquals(16, s.multiply(148, 412));
    }

    @Test
    void multiply_19_28_returns_72() {
        assertEquals(72, s.multiply(19, 28));
    }

    @Test
    void multiply_2020_1851_returns_0() {
        assertEquals(0, s.multiply(2020, 1851));
    }

    @Test
    void multiply_14_neg15_returns_20() {
        assertEquals(20, s.multiply(14, -15));
    }

    @Test
    void multiply_76_67_returns_42() {
        assertEquals(42, s.multiply(76, 67));
    }

    @Test
    void multiply_17_27_returns_49() {
        assertEquals(49, s.multiply(17, 27));
    }

    @Test
    void multiply_0_1_returns_0() {
        assertEquals(0, s.multiply(0, 1));
    }

    @Test
    void multiply_0_0_returns_0() {
        assertEquals(0, s.multiply(0, 0));
    }
}
