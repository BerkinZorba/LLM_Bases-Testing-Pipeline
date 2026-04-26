import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Improved JUnit 6 suite for HumanEval_018 (howManyTimes).
 *
 * Improvements over the dataset harness:
 *   - splits the original assertion roulette list into named, parameterized rows;
 *   - uses explicit (input, substring, expected) triples instead of inline boolean expressions;
 *   - adds guard-branch coverage: empty string, empty substring, substring longer than string;
 *   - adds overlapping-count verification from the prompt examples;
 *   - adds no-match, single-match, and whole-string-equals-substring cases.
 */
public class HumanEval_018_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Dataset cases — one assertion per row")
    class DatasetCases {

        static Stream<Arguments> datasetCases() {
            return Stream.of(
                    Arguments.of("empty string",         "",            "x",    0),
                    Arguments.of("alternating x",        "xyxyxyx",     "x",    4),
                    Arguments.of("overlapping cac",      "cacacacac",   "cac",  4),
                    Arguments.of("single word match",    "john doe",    "john", 1)
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("datasetCases")
        void datasetExpectations(String label, String string, String substring, int expected) {
            assertEquals(expected, s.howManyTimes(string, substring),
                    () -> label + " string=\"" + string + "\" sub=\"" + substring + "\"");
        }
    }

    @Nested
    @DisplayName("Prompt examples")
    class PromptExamples {

        @Test
        @DisplayName("howManyTimes(\"aaa\", \"a\") == 3")
        void aaa_a_returnsThree() {
            assertEquals(3, s.howManyTimes("aaa", "a"));
        }

        @Test
        @DisplayName("howManyTimes(\"aaaa\", \"aa\") == 3  (overlapping)")
        void aaaa_aa_returnsThree() {
            assertEquals(3, s.howManyTimes("aaaa", "aa"));
        }
    }

    @Nested
    @DisplayName("Guard branches")
    class GuardBranches {

        @Test
        @DisplayName("Empty string guard fires — returns 0")
        void emptyString_returnsZero() {
            assertEquals(0, s.howManyTimes("", "a"));
        }

        @Test
        @DisplayName("Empty substring guard fires — returns 0")
        void emptySubstring_returnsZero() {
            assertEquals(0, s.howManyTimes("abc", ""));
        }

        @Test
        @DisplayName("Substring longer than string guard fires — returns 0")
        void substringLongerThanString_returnsZero() {
            assertEquals(0, s.howManyTimes("a", "abc"));
        }
    }

    @Nested
    @DisplayName("Behavioral properties beyond the base suite")
    class BehavioralProperties {

        @Test
        @DisplayName("No match in non-empty string returns 0")
        void noMatch_returnsZero() {
            assertEquals(0, s.howManyTimes("abcdef", "xyz"));
        }

        @Test
        @DisplayName("Substring equals entire string returns 1")
        void substringEqualsString_returnsOne() {
            assertEquals(1, s.howManyTimes("abc", "abc"));
        }

        @Test
        @DisplayName("Single-character string and substring — match")
        void singleChar_match() {
            assertEquals(1, s.howManyTimes("a", "a"));
        }

        @Test
        @DisplayName("Consecutive identical substrings counted with overlap")
        void consecutiveOverlap() {
            assertEquals(2, s.howManyTimes("aaa", "aa"));
        }

        @Test
        @DisplayName("Substring at start only")
        void matchAtStartOnly() {
            assertEquals(1, s.howManyTimes("abcxxx", "abc"));
        }

        @Test
        @DisplayName("Substring at end only")
        void matchAtEndOnly() {
            assertEquals(1, s.howManyTimes("xxxabc", "abc"));
        }

        @Test
        @DisplayName("Multiple non-overlapping matches")
        void multipleNonOverlapping() {
            assertEquals(3, s.howManyTimes("abcabcabc", "abc"));
        }
    }
}
