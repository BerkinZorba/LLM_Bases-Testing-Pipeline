import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit 6 port of the HumanEval_106 dataset base harness.
 * Source: tests/base_tests/original/HumanEval_106/Main.java
 * Each equals assertion in the original Main is one named test here.
 * No assertion logic, inputs, or expected values have been changed.
 * Compatibility fix: added missing java.util imports (original harness lacked them).
 */
public class HumanEval_106_BaseTest {

    private final Solution s = new Solution();

    @Test
    void f5_returns1_2_6_24_15() {
        assertEquals(Arrays.asList(1, 2, 6, 24, 15), s.f(5));
    }

    @Test
    void f7_returns1_2_6_24_15_720_28() {
        assertEquals(Arrays.asList(1, 2, 6, 24, 15, 720, 28), s.f(7));
    }

    @Test
    void f1_returns1() {
        assertEquals(List.of(1), s.f(1));
    }

    @Test
    void f3_returns1_2_6() {
        assertEquals(Arrays.asList(1, 2, 6), s.f(3));
    }
}
