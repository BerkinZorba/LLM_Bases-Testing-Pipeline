import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_122_BaseTest {

    private final Solution solution = new Solution();

    @Test
    void firstThreeElementsCanSumToNegativeValue() {
        assertEquals(-4, solution.addElements(Arrays.asList(1, -2, -3, 41, 57, 76, 87, 88, 99), 3));
    }

    @Test
    void firstTwoThreeDigitNumbersContributeNothing() {
        assertEquals(0, solution.addElements(Arrays.asList(111, 121, 3, 4000, 5, 6), 2));
    }

    @Test
    void firstFourElementsAllHaveAtMostTwoDigits() {
        assertEquals(125, solution.addElements(Arrays.asList(11, 21, 3, 90, 5, 6, 7, 8, 9), 4));
    }

    @Test
    void mixedDigitLengthsOnlySumEligiblePrefixValues() {
        assertEquals(24, solution.addElements(Arrays.asList(111, 21, 3, 4000, 5, 6, 7, 8, 9), 4));
    }

    @Test
    void singletonPrefixReturnsThatValue() {
        assertEquals(1, solution.addElements(Arrays.asList(1), 1));
    }
}
