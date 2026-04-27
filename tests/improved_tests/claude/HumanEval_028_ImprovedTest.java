import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

/**
 * Improved suite for HumanEval_028 (concatenate).
 *
 * Test-smell improvements:
 *   - Assertion roulette: each case is a separate @Test with a name.
 *   - Magic numbers: list sizes and expected values are self-documenting.
 *   - Eager test: cases partitioned by list size and string content.
 *
 * Branch-coverage targets in Solution.concatenate:
 *   - Loop entry: empty list (loop never entered) vs non-empty.
 *   - Loop body: single iteration vs multiple iterations.
 */
public class HumanEval_028_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Empty list")
    class EmptyList {
        @Test @DisplayName("Empty list -> empty string")
        void empty() { assertEquals("", s.concatenate(List.of())); }
    }

    @Nested
    @DisplayName("Single element")
    class SingleElement {
        @Test @DisplayName("Single non-empty string")
        void single() { assertEquals("hello", s.concatenate(List.of("hello"))); }

        @Test @DisplayName("Single empty string")
        void singleEmpty() { assertEquals("", s.concatenate(List.of(""))); }
    }

    @Nested
    @DisplayName("Multiple elements")
    class MultipleElements {
        @Test @DisplayName("Docstring example: ['a','b','c'] -> 'abc'")
        void docstringExample() {
            assertEquals("abc", s.concatenate(Arrays.asList("a", "b", "c")));
        }

        @Test @DisplayName("Words with spaces: preserves order")
        void wordsPreserveOrder() {
            assertEquals("hello world", s.concatenate(Arrays.asList("hello", " ", "world")));
        }

        @Test @DisplayName("Mix of empty and non-empty strings")
        void mixedEmpty() {
            assertEquals("ab", s.concatenate(Arrays.asList("a", "", "b")));
        }

        @Test @DisplayName("All empty strings -> empty result")
        void allEmpty() {
            assertEquals("", s.concatenate(Arrays.asList("", "", "")));
        }
    }

    @Nested
    @DisplayName("Invariants")
    class Invariants {
        @Test @DisplayName("Result length equals sum of element lengths")
        void lengthIsSum() {
            List<String> lst = Arrays.asList("foo", "bar", "baz");
            int expectedLen = lst.stream().mapToInt(String::length).sum();
            assertEquals(expectedLen, s.concatenate(lst).length());
        }

        @Test @DisplayName("Single-element list: result equals that element")
        void singleIdentity() {
            assertEquals("xyz", s.concatenate(List.of("xyz")));
        }
    }
}
