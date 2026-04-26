import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
 * Improved JUnit 6 suite for HumanEval_007 (filterBySubstring).
 *
 * Improvements over the dataset harness:
 *   - splits the original assertion roulette list into named, parameterized rows;
 *   - uses explicit expected lists instead of inline boolean expressions;
 *   - checks order preservation, duplicate preservation, case sensitivity,
 *     empty-substring behavior, empty-string elements, and no hidden state.
 */
public class HumanEval_007_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Dataset cases - one assertion per row")
    class DatasetCases {

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
        void datasetExpectations(String label, List<String> input, String substring, List<String> expected) {
            assertEquals(expected, s.filterBySubstring(new ArrayList<>(input), substring),
                    () -> label + " substring=\"" + substring + "\"");
        }
    }

    @Nested
    @DisplayName("Behavioral properties beyond the base suite")
    class BehavioralProperties {

        @Test
        @DisplayName("Preserves original order rather than sorting matches")
        void preservesOriginalOrder() {
            assertEquals(Arrays.asList("cab", "abc", "bca"),
                    s.filterBySubstring(new ArrayList<>(Arrays.asList("cab", "zzz", "abc", "bca")), "a"));
        }

        @Test
        @DisplayName("Preserves duplicate matching elements")
        void preservesDuplicates() {
            assertEquals(Arrays.asList("aa", "aa", "aaa"),
                    s.filterBySubstring(new ArrayList<>(Arrays.asList("aa", "b", "aa", "aaa")), "aa"));
        }

        @Test
        @DisplayName("Case-sensitive matching")
        void caseSensitiveMatching() {
            assertEquals(Arrays.asList("Alpha", "ALPHA"),
                    s.filterBySubstring(new ArrayList<>(Arrays.asList("Alpha", "alpha", "ALPHA")), "A"));
        }

        @Test
        @DisplayName("Empty substring matches every string, including empty strings")
        void emptySubstringMatchesAllElements() {
            List<String> input = Arrays.asList("", "a", "xyz");
            assertEquals(input, s.filterBySubstring(new ArrayList<>(input), ""));
        }

        @Test
        @DisplayName("Empty string element only matches empty substring")
        void emptyStringElementDoesNotMatchNonEmptySubstring() {
            assertEquals(List.of("abc"),
                    s.filterBySubstring(new ArrayList<>(Arrays.asList("", "abc")), "a"));
        }

        @Test
        @DisplayName("Returned list is a new list object")
        void returnsNewListObject() {
            ArrayList<String> input = new ArrayList<>(Arrays.asList("alpha", "beta"));
            List<String> result = s.filterBySubstring(input, "a");
            assertNotSame(input, result);
            assertEquals(Arrays.asList("alpha", "beta"), result);
        }

        @Test
        @DisplayName("Repeated calls do not share result state")
        void repeatedCallsDoNotShareState() {
            ArrayList<String> input = new ArrayList<>(Arrays.asList("a", "b", "ab"));
            List<String> first = s.filterBySubstring(input, "a");
            first.add("manual mutation");

            assertEquals(Arrays.asList("a", "ab"), s.filterBySubstring(input, "a"));
        }
    }
}
