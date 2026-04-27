import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class HumanEval_023_ManualTest {

    private final Solution s = new Solution();

    // V1 — empty string
    @Test @DisplayName("V1: strlen('') == 0")
    void v1_empty() { assertEquals(0, s.strlen("")); }

    // V2 — single character
    @Test @DisplayName("V2: strlen('a') == 1")
    void v2_singleLetter() { assertEquals(1, s.strlen("a")); }

    @Test @DisplayName("V2: strlen(' ') == 1 (space is a character)")
    void v2_space() { assertEquals(1, s.strlen(" ")); }

    // V3 — multi-character ASCII
    @Test @DisplayName("V3: strlen('abc') == 3 (docstring)")
    void v3_docstring() { assertEquals(3, s.strlen("abc")); }

    @Test @DisplayName("V3: strlen('hello') == 5")
    void v3_hello() { assertEquals(5, s.strlen("hello")); }

    // V4 — string with spaces
    @Test @DisplayName("V4: strlen('hello world') == 11")
    void v4_withSpace() { assertEquals(11, s.strlen("hello world")); }

    // V5 — longer string
    @Test @DisplayName("V5: strlen('abcdefghij') == 10")
    void v5_ten() { assertEquals(10, s.strlen("abcdefghij")); }

    // Invariants
    @Test @DisplayName("Invariant: result >= 0")
    void invariant_nonNegative() {
        assertTrue(s.strlen("") >= 0);
        assertTrue(s.strlen("xyz") >= 0);
    }

    @Test @DisplayName("Invariant: strlen(a+b) == strlen(a) + strlen(b)")
    void invariant_additive() {
        assertEquals(s.strlen("hello") + s.strlen("world"), s.strlen("helloworld"));
    }
}
