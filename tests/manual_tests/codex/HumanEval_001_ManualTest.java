import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

/**
 * Manual black-box tests for HumanEval_001 (codex / GPT).
 * Cases derived from equivalence classes and boundary analysis in HumanEval_001_blackbox.md.
 */
public class HumanEval_001_ManualTest {

    private final Solution s = new Solution();

    // --- Valid partitions ---

    @Test @DisplayName("V1: empty string -> empty list")
    void v1_emptyString() {
        assertEquals(List.of(), s.separateParenGroups(""));
    }

    @Test @DisplayName("V2: whitespace-only -> empty list")
    void v2_whitespaceOnly() {
        assertEquals(List.of(), s.separateParenGroups("   "));
    }

    @Test @DisplayName("V3: single flat group")
    void v3_singleFlatGroup() {
        assertEquals(List.of("()"), s.separateParenGroups("()"));
    }

    @Test @DisplayName("V4: single group with one nested level")
    void v4_singleNestedGroup() {
        assertEquals(List.of("(())"), s.separateParenGroups("(())"));
    }

    @Test @DisplayName("V5: two flat groups no space")
    void v5_twoFlatNoSpace() {
        assertEquals(Arrays.asList("()", "()"), s.separateParenGroups("()()"));
    }

    @Test @DisplayName("V6: two flat groups space-separated")
    void v6_twoFlatSpaceSeparated() {
        assertEquals(Arrays.asList("()", "()"), s.separateParenGroups("() ()"));
    }

    @Test @DisplayName("V7: docstring three mixed-depth groups")
    void v7_docstringThreeGroups() {
        assertEquals(Arrays.asList("()", "(())", "(()())"),
                s.separateParenGroups("( ) (( )) (( )( ))"));
    }

    @Test @DisplayName("V8: four groups with varying nesting")
    void v8_fourGroupsVaryingNesting() {
        assertEquals(Arrays.asList("(()())", "((()))", "()", "((())(()))"),
                s.separateParenGroups("(()()) ((())) () ((())(()))"));
    }

    // --- Boundary conditions ---

    @Test @DisplayName("Boundary: deep nesting depth=4 single group")
    void boundary_deepNesting() {
        assertEquals(List.of("(((())))"), s.separateParenGroups("(((())))"));
    }

    @Test @DisplayName("Boundary: spaces stripped from result strings")
    void boundary_spacesStripped() {
        List<String> result = s.separateParenGroups("( ) ( ( ) )");
        assertTrue(result.stream().noneMatch(g -> g.contains(" ")));
    }
}
