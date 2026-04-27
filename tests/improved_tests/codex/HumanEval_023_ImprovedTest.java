import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

/**
 * Improved suite for HumanEval_023 (codex / GPT).
 *
 * Smells addressed:
 *   - Assertion roulette: each case is a separate @Test with a display name.
 *   - Magic numbers: lengths described in display names.
 *
 * No branches in the implementation; tests verify correctness across
 * representative string classes.
 */
public class HumanEval_023_ImprovedTest {

    private final Solution s = new Solution();

    @Test @DisplayName("empty string -> 0")
    void empty() {
        assertEquals(0, s.strlen(""));
    }

    @Test @DisplayName("single character -> 1")
    void singleChar() {
        assertEquals(1, s.strlen("a"));
    }

    @Test @DisplayName("docstring: 'abc' -> 3")
    void docstringAbc() {
        assertEquals(3, s.strlen("abc"));
    }

    @Test @DisplayName("string with spaces counted")
    void withSpaces() {
        assertEquals(11, s.strlen("hello world"));
    }

    @Test @DisplayName("longer string -> correct length")
    void longerString() {
        assertEquals(10, s.strlen("abcdefghij"));
    }

    @Test @DisplayName("string of spaces -> length equals space count")
    void allSpaces() {
        assertEquals(5, s.strlen("     "));
    }

    @Test @DisplayName("string with special characters")
    void specialChars() {
        assertEquals(5, s.strlen("a!@#b"));
    }

    @Test @DisplayName("result matches string.length() invariant")
    void matchesJavaLength() {
        String[] inputs = {"", "x", "hello", "hello world", "12345"};
        for (String input : inputs) {
            assertEquals(input.length(), s.strlen(input));
        }
    }
}
