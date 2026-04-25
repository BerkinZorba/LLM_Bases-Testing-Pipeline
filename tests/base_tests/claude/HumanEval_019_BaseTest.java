import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit 6 port of the HumanEval_019 dataset base harness.
 * Source: tests/base_tests/original/HumanEval_019/Main.java
 * Each boolean assertion in the original Main is one named test here.
 * No assertion logic, inputs, or expected values have been changed.
 */
public class HumanEval_019_BaseTest {

    private final Solution s = new Solution();

    @Test
    void emptyString_returnsEmpty() {
        assertEquals("", s.sortNumbers(""));
    }

    @Test
    void singleWord_returnsItself() {
        assertEquals("three", s.sortNumbers("three"));
    }

    @Test
    void alreadySorted_returnsUnchanged() {
        assertEquals("three five nine", s.sortNumbers("three five nine"));
    }

    @Test
    void unsortedSixWords_returnsSortedOrder() {
        assertEquals("zero four five seven eight nine",
                s.sortNumbers("five zero four seven nine eight"));
    }
}
