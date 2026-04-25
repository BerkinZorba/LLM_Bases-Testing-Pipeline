import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit 6 port of the HumanEval_018 dataset base harness.
 * Source: tests/base_tests/original/HumanEval_018/Main.java
 * Each boolean assertion in the original Main is one named test here.
 * No assertion logic, inputs, or expected values have been changed.
 */
public class HumanEval_018_BaseTest {

    private final Solution s = new Solution();

    @Test
    void emptyString_returnsZero() {
        assertEquals(0, s.howManyTimes("", "x"));
    }

    @Test
    void alternatingPattern_singleChar_countsFour() {
        assertEquals(4, s.howManyTimes("xyxyxyx", "x"));
    }

    @Test
    void overlappingThreeChar_countsFour() {
        assertEquals(4, s.howManyTimes("cacacacac", "cac"));
    }

    @Test
    void singleOccurrence_word() {
        assertEquals(1, s.howManyTimes("john doe", "john"));
    }
}
