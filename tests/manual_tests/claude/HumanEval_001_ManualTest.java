import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class HumanEval_001_ManualTest {

    private final Solution s = new Solution();

    // V1 — empty string
    @Test @DisplayName("V1: '' -> []")
    void v1_empty() { assertEquals(List.of(), s.separateParenGroups("")); }

    // V2 — only spaces
    @Test @DisplayName("V2: '   ' -> []")
    void v2_spaces() { assertEquals(List.of(), s.separateParenGroups("   ")); }

    // V3 — single flat group
    @Test @DisplayName("V3: '()' -> ['()']")
    void v3_singleFlat() { assertEquals(List.of("()"), s.separateParenGroups("()")); }

    @Test @DisplayName("V3: '( )' -> ['()'] (space stripped)")
    void v3_singleFlatSpaced() { assertEquals(List.of("()"), s.separateParenGroups("( )")); }

    // V4 — single group with internal nesting
    @Test @DisplayName("V4: '(())' -> ['(())']")
    void v4_singleNested() { assertEquals(List.of("(())"), s.separateParenGroups("(())")); }

    @Test @DisplayName("V4: '((())) ' -> ['((()))']")
    void v4_deeplyNested() { assertEquals(List.of("((()))"), s.separateParenGroups("((()))")); }

    // V5 — multiple flat groups
    @Test @DisplayName("V5: '() ()' -> ['()','()']")
    void v5_twoFlat() { assertEquals(Arrays.asList("()", "()"), s.separateParenGroups("() ()")); }

    @Test @DisplayName("V5: '()()' -> ['()','()'] (no space)")
    void v5_twoFlatNoSpace() { assertEquals(Arrays.asList("()", "()"), s.separateParenGroups("()()")); }

    // V6 — docstring example
    @Test @DisplayName("V6: docstring example -> 3 groups")
    void v6_docstring() {
        assertEquals(Arrays.asList("()", "(())", "(()())"),
                s.separateParenGroups("( ) (( )) (( )( ))"));
    }

    // V7 — deep nesting
    @Test @DisplayName("V7: '((())) (()())' -> 2 groups")
    void v7_deepNesting() {
        assertEquals(Arrays.asList("((()))", "(()())"),
                s.separateParenGroups("((())) (()())"));
    }

    // V8 — four groups
    @Test @DisplayName("V8: four groups with varying depth")
    void v8_fourGroups() {
        assertEquals(Arrays.asList("(()())", "((()))", "()", "((())(()))"),
                s.separateParenGroups("(()()) ((())) () ((())(()))"));
    }

    // Boundary: spaces stripped from result
    @Test @DisplayName("Boundary: result strings contain no spaces")
    void boundary_noSpacesInResult() {
        List<String> result = s.separateParenGroups("( ( ) )");
        result.forEach(g -> assertFalse(g.contains(" ")));
    }

    // Boundary: adjacent vs spaced — same count
    @Test @DisplayName("Boundary: adjacent and spaced groups produce same count")
    void boundary_adjacentVsSpaced() {
        assertEquals(s.separateParenGroups("()()").size(),
                s.separateParenGroups("() ()").size());
    }
}
