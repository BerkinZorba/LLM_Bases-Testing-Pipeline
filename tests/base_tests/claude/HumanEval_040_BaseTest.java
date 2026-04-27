import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit 6 port of the HumanEval_040 dataset base harness.
 * Source: tests/base_tests/original/HumanEval_040/Main.java
 * Tested against generated_code/claude/HumanEval_040_v2.java (initial was truncated/non-compilable).
 * Each boolean assertion in the original Main is one named test here.
 * No assertion logic, inputs, or expected values have been changed.
 */
public class HumanEval_040_BaseTest {

    private final Solution s = new Solution();

    @Test
    void noTriple_oneFiveZero() {
        assertFalse(s.triplesSumToZero(new ArrayList<>(Arrays.asList(1, 3, 5, 0))));
    }

    @Test
    void noTriple_oneFiveMinusOne() {
        assertFalse(s.triplesSumToZero(new ArrayList<>(Arrays.asList(1, 3, 5, -1))));
    }

    @Test
    void hasTriple_oneThreeMinusTwoOne() {
        assertTrue(s.triplesSumToZero(new ArrayList<>(Arrays.asList(1, 3, -2, 1))));
    }

    @Test
    void noTriple_oneTwoThreeSeven() {
        assertFalse(s.triplesSumToZero(new ArrayList<>(Arrays.asList(1, 2, 3, 7))));
    }

    @Test
    void noTriple_oneTwoFiveSeven() {
        assertFalse(s.triplesSumToZero(new ArrayList<>(Arrays.asList(1, 2, 5, 7))));
    }

    @Test
    void hasTriple_twoFourMinusFiveThreeNineSeven() {
        assertTrue(s.triplesSumToZero(new ArrayList<>(Arrays.asList(2, 4, -5, 3, 9, 7))));
    }

    @Test
    void singleElement_returnsFalse() {
        assertFalse(s.triplesSumToZero(new ArrayList<>(Arrays.asList(1))));
    }

    @Test
    void noTriple_oneThreeFiveMinusHundred() {
        assertFalse(s.triplesSumToZero(new ArrayList<>(Arrays.asList(1, 3, 5, -100))));
    }

    @Test
    void noTriple_hundredThreeFiveMinusHundred() {
        assertFalse(s.triplesSumToZero(new ArrayList<>(Arrays.asList(100, 3, 5, -100))));
    }
}
