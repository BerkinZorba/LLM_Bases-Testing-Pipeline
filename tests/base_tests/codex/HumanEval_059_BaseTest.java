import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_059_BaseTest {

    private final Solution solution = new Solution();

    @Test
    void factorizationOfFifteenReturnsFive() {
        assertEquals(5, solution.largestPrimeFactor(15));
    }

    @Test
    void repeatedFactorThreeInTwentySevenReturnsThree() {
        assertEquals(3, solution.largestPrimeFactor(27));
    }

    @Test
    void sixtyThreeReturnsSeven() {
        assertEquals(7, solution.largestPrimeFactor(63));
    }

    @Test
    void threeHundredThirtyReturnsEleven() {
        assertEquals(11, solution.largestPrimeFactor(330));
    }

    @Test
    void datasetRepresentativeLargeCompositeReturnsTwentyNine() {
        assertEquals(29, solution.largestPrimeFactor(13195));
    }
}
