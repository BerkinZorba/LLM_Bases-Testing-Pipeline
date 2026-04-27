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
 * Improved suite for HumanEval_001 (codex / GPT).
 *
 * Smells addressed:
 *   - Assertion roulette: each assertion is its own @Test with a display name.
 *   - Magic numbers: depths and group counts described in display names.
 *   - Eager test: grouped by concern via @Nested.
 *
 * Branch targets in Solution.separateParenGroups:
 *   - Loop body entered (non-empty) vs not entered (empty string).
 *   - Space-skip (c == ' ') true vs false.
 *   - '(' branch: balance++ taken vs not taken.
 *   - ')' branch: balance-- taken (also triggers flush when balance hits 0).
 *   - Flush condition (balance == 0 && current.length() > 0): true vs false path
 *     (false path hit mid-group when balance > 0).
 */
public class HumanEval_001_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Empty / whitespace inputs")
    class EmptyLike {
        @Test @DisplayName("empty string returns empty list")
        void empty() {
            assertEquals(List.of(), s.separateParenGroups(""));
        }

        @Test @DisplayName("all spaces returns empty list")
        void allSpaces() {
            assertEquals(List.of(), s.separateParenGroups("     "));
        }
    }

    @Nested
    @DisplayName("Single group")
    class SingleGroup {
        @Test @DisplayName("minimal flat group '()'")
        void flat() {
            assertEquals(List.of("()"), s.separateParenGroups("()"));
        }

        @Test @DisplayName("single group with one level of nesting '(())'")
        void oneNested() {
            assertEquals(List.of("(())"), s.separateParenGroups("(())"));
        }

        @Test @DisplayName("spaces inside group are stripped '( )'")
        void spacesInside() {
            assertEquals(List.of("()"), s.separateParenGroups("( )"));
        }

        @Test @DisplayName("deep nesting depth=4")
        void deepNesting() {
            assertEquals(List.of("(((())))"), s.separateParenGroups("(((())))"));
        }
    }

    @Nested
    @DisplayName("Multiple groups")
    class MultipleGroups {
        @Test @DisplayName("docstring example: three mixed-depth groups")
        void docstring() {
            assertEquals(Arrays.asList("()", "(())", "(()())"),
                    s.separateParenGroups("( ) (( )) (( )( ))"));
        }

        @Test @DisplayName("two flat adjacent groups no space")
        void twoAdjacentNoSpace() {
            assertEquals(Arrays.asList("()", "()"), s.separateParenGroups("()()"));
        }

        @Test @DisplayName("two flat groups space-separated")
        void twoSpaceSeparated() {
            assertEquals(Arrays.asList("()", "()"), s.separateParenGroups("() ()"));
        }

        @Test @DisplayName("four groups with varying nesting")
        void fourGroups() {
            assertEquals(Arrays.asList("(()())", "((()))", "()", "((())(()))"),
                    s.separateParenGroups("(()()) ((())) () ((())(()))"));
        }
    }

    @Nested
    @DisplayName("Space-stripping invariants")
    class SpaceStripping {
        @Test @DisplayName("result strings never contain spaces")
        void noSpacesInResult() {
            List<String> result = s.separateParenGroups("( ( ) ) ( ( ( ) ) )");
            assertTrue(result.stream().noneMatch(g -> g.contains(" ")));
        }

        @Test @DisplayName("same groups regardless of surrounding spaces")
        void groupCountSpaceIndependent() {
            assertEquals(s.separateParenGroups("()()()"),
                    s.separateParenGroups("() () ()"));
        }
    }
}
