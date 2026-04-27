import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HumanEval_040_BaseTest {

    private final Solution s = new Solution();

    @Test
    void noTripleWithZeroPresent() {
        assertFalse(s.triplesSumToZero(Arrays.asList(1, 3, 5, 0)));
    }

    @Test
    void noTripleWithNegativeButNoComplement() {
        assertFalse(s.triplesSumToZero(Arrays.asList(1, 3, 5, -1)));
    }

    @Test
    void promptPositiveCaseWithDuplicateOne() {
        assertTrue(s.triplesSumToZero(Arrays.asList(1, 3, -2, 1)));
    }

    @Test
    void allPositiveValuesReturnFalse() {
        assertFalse(s.triplesSumToZero(Arrays.asList(1, 2, 3, 7)));
    }

    @Test
    void allPositiveDifferentValuesReturnFalse() {
        assertFalse(s.triplesSumToZero(Arrays.asList(1, 2, 5, 7)));
    }

    @Test
    void promptPositiveCaseAcrossUnsortedList() {
        assertTrue(s.triplesSumToZero(Arrays.asList(2, 4, -5, 3, 9, 7)));
    }

    @Test
    void singletonReturnsFalse() {
        assertFalse(s.triplesSumToZero(Arrays.asList(1)));
    }

    @Test
    void largeNegativeWithoutComplementReturnsFalse() {
        assertFalse(s.triplesSumToZero(Arrays.asList(1, 3, 5, -100)));
    }

    @Test
    void largeOppositePairWithoutThirdZeroReturnsFalse() {
        assertFalse(s.triplesSumToZero(Arrays.asList(100, 3, 5, -100)));
    }
}
