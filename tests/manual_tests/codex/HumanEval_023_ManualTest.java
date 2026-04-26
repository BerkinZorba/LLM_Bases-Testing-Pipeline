import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manual black-box tests for HumanEval_023 (codex / GPT).
 * Cases derived from HumanEval_023_blackbox.md.
 */
public class HumanEval_023_ManualTest {

    private final Solution s = new Solution();

    @Test @DisplayName("V1: empty string -> 0")
    void v1_empty() {
        assertEquals(0, s.strlen(""));
    }

    @Test @DisplayName("V2: single character -> 1")
    void v2_singleChar() {
        assertEquals(1, s.strlen("a"));
    }

    @Test @DisplayName("V3: 'abc' -> 3")
    void v3_abc() {
        assertEquals(3, s.strlen("abc"));
    }

    @Test @DisplayName("V4: string with spaces -> counts spaces")
    void v4_withSpaces() {
        assertEquals(11, s.strlen("hello world"));
    }

    @Test @DisplayName("V5: all spaces -> counts each space")
    void v5_allSpaces() {
        assertEquals(3, s.strlen("   "));
    }

    @Test @DisplayName("V6: digits-only string")
    void v6_digitsOnly() {
        assertEquals(5, s.strlen("12345"));
    }

    @Test @DisplayName("V7: special characters")
    void v7_specialChars() {
        assertEquals(5, s.strlen("!@#$%"));
    }

    @Test @DisplayName("V8: longer string")
    void v8_longerString() {
        assertEquals(10, s.strlen("abcdefghij"));
    }

    @Test @DisplayName("Boundary: single space is length 1")
    void boundary_singleSpace() {
        assertEquals(1, s.strlen(" "));
    }
}
