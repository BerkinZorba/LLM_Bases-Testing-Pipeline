import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

/**
 * Improved suite for HumanEval_023 (strlen).
 *
 * Test-smell improvements:
 *   - Assertion roulette: each case is a separate @Test or parameterized row.
 *   - Magic numbers: lengths are conveyed via display names.
 *   - Eager test: partitioned into nested classes by input category.
 *
 * Branch-coverage targets in Solution.strlen:
 *   - The single-line delegation `string.length()` has no in-method branches.
 *   - Behavioral coverage: empty string (0), single char (1), multi-char, spaces.
 */
public class HumanEval_023_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Empty string")
    class EmptyString {
        @Test @DisplayName("strlen('') == 0")
        void empty() { assertEquals(0, s.strlen("")); }
    }

    @Nested
    @DisplayName("Single character")
    class SingleChar {
        @Test @DisplayName("strlen('a') == 1")
        void oneChar() { assertEquals(1, s.strlen("a")); }

        @Test @DisplayName("strlen(' ') == 1 (space is a character)")
        void space() { assertEquals(1, s.strlen(" ")); }
    }

    @Nested
    @DisplayName("Multi-character strings")
    class MultiChar {
        @ParameterizedTest(name = "strlen({0}) == {1}")
        @CsvSource({
                "abc,       3",
                "hello,     5",
                "hello world, 11"
        })
        void multiChar(String input, int expected) {
            assertEquals(expected, s.strlen(input));
        }
    }

    @Nested
    @DisplayName("Invariants")
    class Invariants {
        @Test @DisplayName("Result is non-negative")
        void nonNegative() {
            assertTrue(s.strlen("") >= 0);
            assertTrue(s.strlen("abc") >= 0);
        }

        @Test @DisplayName("strlen(a+b) == strlen(a) + strlen(b)")
        void concatenationLengthAdditive() {
            String a = "hello", b = "world";
            assertEquals(s.strlen(a) + s.strlen(b), s.strlen(a + b));
        }
    }
}
