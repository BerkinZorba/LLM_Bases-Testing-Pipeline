import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Codex-authored executable manual suite derived from the black-box analysis.
 */
public class HumanEval_018_ManualTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> validClasses() {
        return Stream.of(
                Arguments.of("V1", "", "a", 0),
                Arguments.of("V2", "aaa", "a", 3),
                Arguments.of("V3", "aaaa", "aa", 3),
                Arguments.of("V4", "abc", "abc", 1),
                Arguments.of("V5", "abc", "abcd", 0),
                Arguments.of("V6", "abcdef", "gh", 0),
                Arguments.of("V7", "john doe", "john", 1),
                Arguments.of("V8", "hello.java", ".java", 1),
                Arguments.of("V9", "cat dog cat", "cat", 2),
                Arguments.of("V10", "a b a b", "a b", 2),
                Arguments.of("V11", "!!!", "!!", 2),
                Arguments.of("V12", "CaseCASEcase", "case", 1)
        );
    }

    @ParameterizedTest(name = "{0}: \"{1}\" contains \"{2}\" {3} times")
    @MethodSource("validClasses")
    @DisplayName("Valid equivalence classes")
    void validEquivalenceClasses(String id, String string, String substring, int expected) {
        assertEquals(expected, solution.howManyTimes(string, substring));
    }

    @Test
    @DisplayName("Boundary: source length zero")
    void sourceLengthZeroBoundary() {
        assertEquals(0, solution.howManyTimes("", "x"));
    }

    @Test
    @DisplayName("Boundary: substring length one")
    void substringLengthOneBoundary() {
        assertEquals(4, solution.howManyTimes("xyxyxyx", "x"));
    }

    @Test
    @DisplayName("Boundary: first possible overlapping pair")
    void firstPossibleOverlappingPairBoundary() {
        assertEquals(1, solution.howManyTimes("aa", "aa"));
    }

    @Test
    @DisplayName("Boundary: one more overlapping window")
    void oneMoreOverlappingWindowBoundary() {
        assertEquals(2, solution.howManyTimes("aaa", "aa"));
    }

    @Test
    @DisplayName("Null source is pinned to zero")
    void nullSourceReturnsZero() {
        assertEquals(0, solution.howManyTimes(null, "a"));
    }

    @Test
    @DisplayName("Null substring is pinned to zero")
    void nullSubstringReturnsZero() {
        assertEquals(0, solution.howManyTimes("abc", null));
    }

    @Test
    @DisplayName("Empty substring is pinned to zero")
    void emptySubstringReturnsZero() {
        assertEquals(0, solution.howManyTimes("abc", ""));
    }
}
