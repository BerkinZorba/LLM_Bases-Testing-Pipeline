import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HumanEval_057_BaseTest {

    private final Solution solution = new Solution();

    @Test
    void increasingSequence_isMonotonic() {
        assertTrue(solution.monotonic(new ArrayList<>(Arrays.asList(1, 2, 4, 10))));
    }

    @Test
    void increasingSequenceWithLargerTail_isMonotonic() {
        assertTrue(solution.monotonic(new ArrayList<>(Arrays.asList(1, 2, 4, 20))));
    }

    @Test
    void mixedSequence_isNotMonotonic() {
        assertFalse(solution.monotonic(new ArrayList<>(Arrays.asList(1, 20, 4, 10))));
    }

    @Test
    void decreasingSequence_isMonotonic() {
        assertTrue(solution.monotonic(new ArrayList<>(Arrays.asList(4, 1, 0, -10))));
    }

    @Test
    void decreasingSequenceWithPlateau_isMonotonic() {
        assertTrue(solution.monotonic(new ArrayList<>(Arrays.asList(4, 1, 1, 0))));
    }

    @Test
    void sequenceWithDipThenRise_isNotMonotonic() {
        assertFalse(solution.monotonic(new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5, 60))));
    }

    @Test
    void longerIncreasingSequence_isMonotonic() {
        assertTrue(solution.monotonic(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 60))));
    }

    @Test
    void constantSequence_isMonotonic() {
        assertTrue(solution.monotonic(new ArrayList<>(Arrays.asList(9, 9, 9, 9))));
    }
}
