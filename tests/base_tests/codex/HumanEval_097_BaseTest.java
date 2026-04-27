import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_097_BaseTest {

    private final Solution solution = new Solution();

    @Test
    void example148And412_returns16() {
        assertEquals(16, solution.multiply(148, 412));
    }

    @Test
    void example19And28_returns72() {
        assertEquals(72, solution.multiply(19, 28));
    }

    @Test
    void numberEndingInZero_returnsZero() {
        assertEquals(0, solution.multiply(2020, 1851));
    }

    @Test
    void negativeOperand_usesMagnitudeOfUnitDigit() {
        assertEquals(20, solution.multiply(14, -15));
    }

    @Test
    void midRangeValues_return42() {
        assertEquals(42, solution.multiply(76, 67));
    }

    @Test
    void teenAndTwentySeven_return49() {
        assertEquals(49, solution.multiply(17, 27));
    }

    @Test
    void zeroAndOne_returnsZero() {
        assertEquals(0, solution.multiply(0, 1));
    }

    @Test
    void zeroAndZero_returnsZero() {
        assertEquals(0, solution.multiply(0, 0));
    }
}
