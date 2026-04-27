import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
 * Improved JUnit 6 suite for HumanEval_019 (sortNumbers).
 *
 * Improvements over the dataset harness:
 *   - splits the original assertion roulette into named, parameterized rows;
 *   - covers the null guard branch missing from the base suite;
 *   - adds whitespace-only input, all-ten-words, reverse-sorted, and duplicate-word cases;
 *   - verifies that the sort is stable with respect to equal-value (duplicate) words.
 */
public class HumanEval_019_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Dataset cases — one assertion per row")
    class DatasetCases {

        static Stream<Arguments> datasetCases() {
            return Stream.of(
                    Arguments.of("empty string",          "", ""),
                    Arguments.of("single word",           "three", "three"),
                    Arguments.of("already sorted",        "three five nine", "three five nine"),
                    Arguments.of("unsorted six words",
                            "five zero four seven nine eight",
                            "zero four five seven eight nine")
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("datasetCases")
        void datasetExpectations(String label, String input, String expected) {
            assertEquals(expected, s.sortNumbers(input), label);
        }
    }

    @Nested
    @DisplayName("Guard branches")
    class GuardBranches {

        @Test
        @DisplayName("null input returns empty string (null branch)")
        void nullInput_returnsEmpty() {
            assertEquals("", s.sortNumbers(null));
        }

        @Test
        @DisplayName("Whitespace-only input trims to empty, returns empty string")
        void whitespaceOnly_returnsEmpty() {
            assertEquals("", s.sortNumbers("   "));
        }
    }

    @Nested
    @DisplayName("Sorting correctness")
    class SortingCorrectness {

        @Test
        @DisplayName("All ten words in reverse order sorted ascending")
        void allTenWordsReverse() {
            assertEquals("zero one two three four five six seven eight nine",
                    s.sortNumbers("nine eight seven six five four three two one zero"));
        }

        @Test
        @DisplayName("All ten words already in order")
        void allTenWordsForward() {
            assertEquals("zero one two three four five six seven eight nine",
                    s.sortNumbers("zero one two three four five six seven eight nine"));
        }

        @Test
        @DisplayName("Duplicate words are preserved and sorted by value")
        void duplicateWords() {
            assertEquals("one one three five five", s.sortNumbers("five one three one five"));
        }

        @Test
        @DisplayName("Two words — smaller first")
        void twoWords_smallerFirst() {
            assertEquals("two nine", s.sortNumbers("nine two"));
        }

        @Test
        @DisplayName("Two equal words")
        void twoEqualWords() {
            assertEquals("four four", s.sortNumbers("four four"));
        }

        @Test
        @DisplayName("Prompt example: three one five")
        void promptExample() {
            assertEquals("one three five", s.sortNumbers("three one five"));
        }
    }
}
