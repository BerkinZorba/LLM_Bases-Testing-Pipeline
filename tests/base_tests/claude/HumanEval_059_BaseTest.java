import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit 6 port of the dataset base test for HumanEval_059.
 * Mirrors the inputs and expected values of
 * tests/base_tests/adjusted/HumanEval_059/Main.java exactly.
 */
public class HumanEval_059_BaseTest {

    private final Solution s = new Solution();

    @Test
    void largestPrimeFactor_15_is_5() {
        assertEquals(5, s.largestPrimeFactor(15));
    }

    @Test
    void largestPrimeFactor_27_is_3() {
        assertEquals(3, s.largestPrimeFactor(27));
    }

    @Test
    void largestPrimeFactor_63_is_7() {
        assertEquals(7, s.largestPrimeFactor(63));
    }

    @Test
    void largestPrimeFactor_330_is_11() {
        assertEquals(11, s.largestPrimeFactor(330));
    }

    @Test
    void largestPrimeFactor_13195_is_29() {
        assertEquals(29, s.largestPrimeFactor(13195));
    }
}
