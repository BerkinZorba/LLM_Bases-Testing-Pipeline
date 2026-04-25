import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit 6 port of the HumanEval_007 dataset base harness.
 * Source: tests/base_tests/original/HumanEval_007/Main.java
 * Each boolean assertion in the original Main is one named test here.
 * No assertion logic, inputs, or expected values have been changed.
 */
public class HumanEval_007_BaseTest {

    private final Solution s = new Solution();

    @Test
    void emptyList_returnsEmpty() {
        assertEquals(List.of(),
                s.filterBySubstring(new ArrayList<>(List.of()), "john"));
    }

    @Test
    void exactSubstring_xxx_matchesThreeElements() {
        assertEquals(Arrays.asList("xxx", "xxxAAA", "xxx"),
                s.filterBySubstring(
                        new ArrayList<>(Arrays.asList("xxx", "asd", "xxy", "john doe", "xxxAAA", "xxx")),
                        "xxx"));
    }

    @Test
    void broadSubstring_xx_matchesFourElements() {
        assertEquals(Arrays.asList("xxx", "aaaxxy", "xxxAAA", "xxx"),
                s.filterBySubstring(
                        new ArrayList<>(Arrays.asList("xxx", "asd", "aaaxxy", "john doe", "xxxAAA", "xxx")),
                        "xx"));
    }

    @Test
    void middleSubstring_run_matchesTwoElements() {
        assertEquals(Arrays.asList("grunt", "prune"),
                s.filterBySubstring(
                        new ArrayList<>(Arrays.asList("grunt", "trumpet", "prune", "gruesome")),
                        "run"));
    }
}
