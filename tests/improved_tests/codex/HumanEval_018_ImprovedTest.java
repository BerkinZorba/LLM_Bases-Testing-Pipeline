import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Codex-authored improved JUnit 6 suite for HumanEval_018.
 *
 * Design goals:
 * - split the dataset's assertion-list harness into attributable checks;
 * - exercise match and non-match branches for different substring lengths;
 * - add boundary cases around empty inputs, longer substrings, exact matches,
 *   overlapping windows, and case-sensitive matching.
 */
public class HumanEval_018_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> datasetCases() {
        return Stream.of(
                Arguments.of("", "x", 0),
                Arguments.of("xyxyxyx", "x", 4),
                Arguments.of("cacacacac", "cac", 4),
                Arguments.of("john doe", "john", 1)
        );
    }

    static Stream<Arguments> extendedCases() {
        return Stream.of(
                Arguments.of("aaa", "a", 3),
                Arguments.of("aaaa", "aa", 3),
                Arguments.of("banana", "ana", 2),
                Arguments.of("abc", "abc", 1),
                Arguments.of("abc", "abcd", 0),
                Arguments.of("abcabc", "bc", 2),
                Arguments.of("mississippi", "issi", 2),
                Arguments.of("CaseCASEcase", "case", 1),
                Arguments.of("aaaaa", "aaa", 3),
                Arguments.of("a b a b", "a b", 2)
        );
    }

    @ParameterizedTest(name = "\"{0}\" contains \"{1}\" {2} times")
    @MethodSource("datasetCases")
    @DisplayName("Dataset expectations")
    void datasetExpectations(String string, String substring, int expected) {
        assertEquals(expected, solution.howManyTimes(string, substring));
    }

    @ParameterizedTest(name = "\"{0}\" contains \"{1}\" {2} times")
    @MethodSource("extendedCases")
    @DisplayName("Extended overlapping and boundary cases")
    void extendedOverlappingAndBoundaryCases(String string, String substring, int expected) {
        assertEquals(expected, solution.howManyTimes(string, substring));
    }

    @Test
    @DisplayName("Absent substring returns zero after scanning all windows")
    void absentSubstringReturnsZero() {
        assertEquals(0, solution.howManyTimes("abcdef", "gh"));
    }

    @Test
    @DisplayName("Empty substring is pinned to zero")
    void emptySubstringReturnsZero() {
        assertEquals(0, solution.howManyTimes("abc", ""));
    }

    @Test
    @DisplayName("Repeated calls are stable")
    void repeatedCallsAreStable() {
        assertEquals(3, solution.howManyTimes("aaaa", "aa"));
        assertEquals(3, solution.howManyTimes("aaaa", "aa"));
    }
}
