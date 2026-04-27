import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_019_BaseTest {

    private final Solution s = new Solution();

    @Test
    void emptyInputReturnsEmptyString() {
        assertEquals("", s.sortNumbers(""));
    }

    @Test
    void singleNumberRemainsUnchanged() {
        assertEquals("three", s.sortNumbers("three"));
    }

    @Test
    void alreadySortedWordsRemainSorted() {
        assertEquals("three five nine", s.sortNumbers("three five nine"));
    }

    @Test
    void unsortedWordsAreSortedAscending() {
        assertEquals("zero four five seven eight nine",
                s.sortNumbers("five zero four seven nine eight"));
    }
}
