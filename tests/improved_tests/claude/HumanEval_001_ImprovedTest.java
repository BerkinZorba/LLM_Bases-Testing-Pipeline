import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Improved suite for HumanEval_001 (separateParenGroups).
 *
 * Test-smell improvements:
 *   - Assertion roulette: each case is a separate @Test with a descriptive name.
 *   - Magic numbers: group counts and nesting depths are conveyed via display names.
 *   - Eager test: cases partitioned into nested classes by concern
 *     (whitespace handling, depth tracking, multi-group splitting, empty input).
 *
 * Branch-coverage targets in Solution.separateParenGroups:
 *   - Space-skip branch: input with and without embedded spaces.
 *   - '(' branch: increments depth.
 *   - ')' branch / depth-reset branch: depth reaches 0 (group emitted) vs stays > 0.
 *   - Outer loop: empty string (loop never entered) vs non-empty.
 */
public class HumanEval_001_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Empty and whitespace-only input")
    class EmptyInput {
        @Test @DisplayName("Empty string -> empty list")
        void empty() { assertEquals(List.of(), s.separateParenGroups("")); }

        @Test @DisplayName("Only spaces -> empty list")
        void onlySpaces() { assertEquals(List.of(), s.separateParenGroups("   ")); }
    }

    @Nested
    @DisplayName("Single group")
    class SingleGroup {
        @Test @DisplayName("'()' -> ['()']")
        void flat() { assertEquals(List.of("()"), s.separateParenGroups("()")); }

        @Test @DisplayName("'(())' -> ['(())']")
        void oneNested() { assertEquals(List.of("(())"), s.separateParenGroups("(())")); }

        @Test @DisplayName("'( )' with spaces -> ['()']")
        void withSpaces() { assertEquals(List.of("()"), s.separateParenGroups("( )")); }

        @Test @DisplayName("Deeply nested single group")
        void deeplyNested() { assertEquals(List.of("(((())))"), s.separateParenGroups("(((())))")); }
    }

    @Nested
    @DisplayName("Multiple groups")
    class MultipleGroups {
        @Test @DisplayName("Docstring example: three groups")
        void docstringExample() {
            assertEquals(Arrays.asList("()", "(())", "(()())"),
                    s.separateParenGroups("( ) (( )) (( )( ))"));
        }

        @Test @DisplayName("Two flat groups")
        void twoFlat() {
            assertEquals(Arrays.asList("()", "()"), s.separateParenGroups("() ()"));
        }

        @Test @DisplayName("Four groups with varying depths")
        void fourGroups() {
            assertEquals(Arrays.asList("(()())", "((()))", "()", "((())(()))"),
                    s.separateParenGroups("(()()) ((())) () ((())(()))"));
        }
    }

    @Nested
    @DisplayName("Space-handling invariants")
    class SpaceHandling {
        @Test @DisplayName("Spaces between groups are stripped from result strings")
        void spacesNotInResult() {
            List<String> result = s.separateParenGroups("( ( ) )");
            assertFalse(result.stream().anyMatch(g -> g.contains(" ")));
        }

        @Test @DisplayName("Group count is space-independent")
        void groupCountIgnoresSpaces() {
            assertEquals(s.separateParenGroups("()()").size(),
                    s.separateParenGroups("() ()").size());
        }
    }
}
