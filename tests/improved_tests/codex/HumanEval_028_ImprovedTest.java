import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Improved suite for HumanEval_028 (codex / GPT).
 *
 * Smells addressed:
 *   - Assertion roulette: each case is a separate @Test with a display name.
 *   - Eager test: cases grouped by concern via @Nested.
 *
 * Branch targets:
 *   - Loop not entered (empty list).
 *   - Loop entered (non-empty list).
 */
public class HumanEval_028_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Empty list")
    class EmptyList {
        @Test @DisplayName("empty list -> empty string")
        void empty() {
            assertEquals("", s.concatenate(List.of()));
        }
    }

    @Nested
    @DisplayName("Single element")
    class SingleElement {
        @Test @DisplayName("single non-empty string")
        void single() {
            assertEquals("hello", s.concatenate(List.of("hello")));
        }

        @Test @DisplayName("single empty string")
        void singleEmpty() {
            assertEquals("", s.concatenate(List.of("")));
        }
    }

    @Nested
    @DisplayName("Multiple elements")
    class MultipleElements {
        @Test @DisplayName("docstring: a,b,c -> abc")
        void docstring() {
            assertEquals("abc", s.concatenate(Arrays.asList("a", "b", "c")));
        }

        @Test @DisplayName("words with space element in between")
        void withSpaceElement() {
            assertEquals("hello world", s.concatenate(Arrays.asList("hello", " ", "world")));
        }

        @Test @DisplayName("two elements concatenated directly")
        void twoElements() {
            assertEquals("foobar", s.concatenate(Arrays.asList("foo", "bar")));
        }

        @Test @DisplayName("list containing empty strings contributes nothing")
        void withEmptyElements() {
            assertEquals("ac", s.concatenate(Arrays.asList("a", "", "c")));
        }

        @Test @DisplayName("all empty strings -> empty result")
        void allEmpty() {
            assertEquals("", s.concatenate(Arrays.asList("", "", "")));
        }
    }

    @Nested
    @DisplayName("Order preservation")
    class OrderPreservation {
        @Test @DisplayName("result preserves input order")
        void orderPreserved() {
            assertEquals("123", s.concatenate(Arrays.asList("1", "2", "3")));
            assertNotEquals("321", s.concatenate(Arrays.asList("1", "2", "3")));
        }
    }
}
