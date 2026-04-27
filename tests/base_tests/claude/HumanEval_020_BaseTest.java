import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit 6 port of the HumanEval_020 dataset base harness.
 * Source: tests/base_tests/original/HumanEval_020/Main.java
 * Each boolean assertion in the original Main is one named test here.
 * No assertion logic, inputs, or expected values have been changed.
 */
public class HumanEval_020_BaseTest {

    private final Solution s = new Solution();

    @Test
    void closestPairNearMiddle_returnsThreePointNineFour() {
        assertEquals(Arrays.asList(3.9, 4.0),
                s.findClosestElements(new ArrayList<>(Arrays.asList(1.0, 2.0, 3.9, 4.0, 5.0, 2.2))));
    }

    @Test
    void closestPairAtEnd_returnsFivePointZeroFivePointNine() {
        assertEquals(Arrays.asList(5.0, 5.9),
                s.findClosestElements(new ArrayList<>(Arrays.asList(1.0, 2.0, 5.9, 4.0, 5.0))));
    }

    @Test
    void promptExample_twoPointZeroTwoPointTwo() {
        assertEquals(Arrays.asList(2.0, 2.2),
                s.findClosestElements(new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.2))));
    }

    @Test
    void promptExample_duplicateElement_twoPointZeroTwoPointZero() {
        assertEquals(Arrays.asList(2.0, 2.0),
                s.findClosestElements(new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.0))));
    }

    @Test
    void unevenSpacing_twoPointTwoThreePointOne() {
        assertEquals(Arrays.asList(2.2, 3.1),
                s.findClosestElements(new ArrayList<>(Arrays.asList(1.1, 2.2, 3.1, 4.1, 5.1))));
    }
}
