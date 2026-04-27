import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class HumanEval_028_ManualTest {

    private final Solution s = new Solution();

    // V1 — empty list
    @Test @DisplayName("V1: [] -> ''")
    void v1_emptyList() { assertEquals("", s.concatenate(List.of())); }

    // V2 — single non-empty element
    @Test @DisplayName("V2: ['x'] -> 'x'")
    void v2_singleElement() { assertEquals("x", s.concatenate(List.of("x"))); }

    // V3 — single empty string element
    @Test @DisplayName("V3: [''] -> ''")
    void v3_singleEmptyElement() { assertEquals("", s.concatenate(List.of(""))); }

    // V4 — multiple elements
    @Test @DisplayName("V4: ['a','b','c'] -> 'abc' (docstring)")
    void v4_docstring() { assertEquals("abc", s.concatenate(Arrays.asList("a", "b", "c"))); }

    @Test @DisplayName("V4: ['hello','world'] -> 'helloworld'")
    void v4_twoWords() { assertEquals("helloworld", s.concatenate(Arrays.asList("hello", "world"))); }

    // V5 — elements with spaces
    @Test @DisplayName("V5: ['hello',' ','world'] -> 'hello world'")
    void v5_withSpaces() {
        assertEquals("hello world", s.concatenate(Arrays.asList("hello", " ", "world")));
    }

    // V6 — mix of empty and non-empty
    @Test @DisplayName("V6: ['a','','b'] -> 'ab'")
    void v6_mixedEmpty() { assertEquals("ab", s.concatenate(Arrays.asList("a", "", "b"))); }

    // V7 — all empty
    @Test @DisplayName("V7: ['','',''] -> ''")
    void v7_allEmpty() { assertEquals("", s.concatenate(Arrays.asList("", "", ""))); }

    // Invariants
    @Test @DisplayName("Invariant: result length == sum of element lengths")
    void invariant_length() {
        List<String> lst = Arrays.asList("foo", "bar", "baz");
        int expected = lst.stream().mapToInt(String::length).sum();
        assertEquals(expected, s.concatenate(lst).length());
    }

    @Test @DisplayName("Invariant: single element -> result equals that element")
    void invariant_singleIdentity() {
        assertEquals("hello", s.concatenate(List.of("hello")));
    }
}
