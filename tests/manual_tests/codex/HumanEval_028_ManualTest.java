import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

/**
 * Manual black-box tests for HumanEval_028 (codex / GPT).
 * Cases derived from HumanEval_028_blackbox.md.
 */
public class HumanEval_028_ManualTest {

    private final Solution s = new Solution();

    @Test @DisplayName("V1: empty list -> empty string")
    void v1_emptyList() {
        assertEquals("", s.concatenate(List.of()));
    }

    @Test @DisplayName("V2: single non-empty element")
    void v2_singleElement() {
        assertEquals("hello", s.concatenate(List.of("hello")));
    }

    @Test @DisplayName("V3: single empty-string element -> empty string")
    void v3_singleEmptyElement() {
        assertEquals("", s.concatenate(List.of("")));
    }

    @Test @DisplayName("V4: multiple single-char elements")
    void v4_singleCharElements() {
        assertEquals("abc", s.concatenate(Arrays.asList("a", "b", "c")));
    }

    @Test @DisplayName("V5: elements with spaces")
    void v5_withSpaceElement() {
        assertEquals("hello world", s.concatenate(Arrays.asList("hello", " ", "world")));
    }

    @Test @DisplayName("V6: two multi-char elements")
    void v6_twoMultiChar() {
        assertEquals("foobar", s.concatenate(Arrays.asList("foo", "bar")));
    }

    @Test @DisplayName("V7: empty string mixed in list")
    void v7_emptyMixed() {
        assertEquals("ac", s.concatenate(Arrays.asList("a", "", "c")));
    }

    @Test @DisplayName("V8: all elements empty -> empty string")
    void v8_allEmpty() {
        assertEquals("", s.concatenate(Arrays.asList("", "", "")));
    }

    @Test @DisplayName("Boundary: order is preserved left-to-right")
    void boundary_orderPreserved() {
        assertEquals("xyz", s.concatenate(Arrays.asList("x", "y", "z")));
    }

    @Test @DisplayName("Boundary: single element loop entered once")
    void boundary_singleLoop() {
        assertEquals("only", s.concatenate(List.of("only")));
    }
}
