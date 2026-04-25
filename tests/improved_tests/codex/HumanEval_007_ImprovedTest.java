import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * Codex-authored improved JUnit 6 suite for HumanEval_007.
 *
 * Design goals:
 * - make each dataset expectation independently attributable;
 * - add coverage for ordering, duplicate retention, case sensitivity,
 *   empty-substring semantics, and result-list independence;
 * - keep the suite executable against only Solution plus this test class.
 */
public class HumanEval_007_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> datasetCases() {
        return Stream.of(
                Arguments.of("empty input", List.of(), "john", List.of()),
                Arguments.of("exact xxx", Arrays.asList("xxx", "asd", "xxy", "john doe", "xxxAAA", "xxx"),
                        "xxx", Arrays.asList("xxx", "xxxAAA", "xxx")),
                Arguments.of("broader xx", Arrays.asList("xxx", "asd", "aaaxxy", "john doe", "xxxAAA", "xxx"),
                        "xx", Arrays.asList("xxx", "aaaxxy", "xxxAAA", "xxx")),
                Arguments.of("middle run", Arrays.asList("grunt", "trumpet", "prune", "gruesome"),
                        "run", Arrays.asList("grunt", "prune"))
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("datasetCases")
    @DisplayName("Dataset cases")
    void datasetExpectations(String label, List<String> input, String substring, List<String> expected) {
        assertEquals(expected, solution.filterBySubstring(new ArrayList<>(input), substring),
                () -> label + " substring=\"" + substring + "\"");
    }

    @Test
    @DisplayName("Order of matches follows input order")
    void preservesInputOrder() {
        assertEquals(Arrays.asList("beta", "alphabet"),
                solution.filterBySubstring(new ArrayList<>(Arrays.asList("beta", "alpha", "alphabet", "table")), "bet"));
    }

    @Test
    @DisplayName("Duplicate matching strings are retained")
    void retainsDuplicateMatches() {
        assertEquals(Arrays.asList("needle", "needle", "hayneedle"),
                solution.filterBySubstring(new ArrayList<>(Arrays.asList("needle", "hay", "needle", "hayneedle")), "needle"));
    }

    @Test
    @DisplayName("Matching is case-sensitive")
    void matchingIsCaseSensitive() {
        assertEquals(List.of("Cat"),
                solution.filterBySubstring(new ArrayList<>(Arrays.asList("Cat", "cat", "Concatenate")), "Cat"));
    }

    @Test
    @DisplayName("Empty substring selects every element")
    void emptySubstringSelectsEveryElement() {
        List<String> input = Arrays.asList("", "plain", " ");
        assertEquals(input, solution.filterBySubstring(new ArrayList<>(input), ""));
    }

    @Test
    @DisplayName("Empty string element does not match non-empty substring")
    void emptyElementOnlyMatchesEmptySubstring() {
        assertEquals(List.of("a"),
                solution.filterBySubstring(new ArrayList<>(Arrays.asList("", "a", "b")), "a"));
    }

    @Test
    @DisplayName("Result list is independent from the input list object")
    void resultListIsIndependentObject() {
        ArrayList<String> input = new ArrayList<>(Arrays.asList("aa", "bb", "aba"));
        List<String> result = solution.filterBySubstring(input, "a");

        assertNotSame(input, result);
        assertEquals(Arrays.asList("aa", "aba"), result);
    }

    @Test
    @DisplayName("Mutating one result does not affect later calls")
    void resultStateDoesNotLeakAcrossCalls() {
        ArrayList<String> input = new ArrayList<>(Arrays.asList("ab", "bc", "abc"));
        List<String> first = solution.filterBySubstring(input, "a");
        first.clear();

        assertEquals(Arrays.asList("ab", "abc"), solution.filterBySubstring(input, "a"));
    }
}
