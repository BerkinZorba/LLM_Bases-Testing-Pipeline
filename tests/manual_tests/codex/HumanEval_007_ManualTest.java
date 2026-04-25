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
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Codex-authored manual suite derived from tests/manual_tests/codex/HumanEval_007_blackbox.md.
 */
public class HumanEval_007_ManualTest {

    private final Solution solution = new Solution();

    @Nested
    @DisplayName("Valid equivalence classes")
    class ValidClasses {

        static Stream<Arguments> validClasses() {
            return Stream.of(
                    Arguments.of("V1", List.of(), "x", List.of()),
                    Arguments.of("V2", Arrays.asList("red", "blue"), "z", List.of()),
                    Arguments.of("V3", List.of("same"), "same", List.of("same")),
                    Arguments.of("V4", Arrays.asList("start", "branch"), "sta", List.of("start")),
                    Arguments.of("V5", Arrays.asList("planet", "lane", "plain"), "lan", Arrays.asList("planet", "lane")),
                    Arguments.of("V6", Arrays.asList("ending", "pending", "end"), "ing", Arrays.asList("ending", "pending")),
                    Arguments.of("V7", Arrays.asList("aa", "bb", "aa"), "aa", Arrays.asList("aa", "aa")),
                    Arguments.of("V8", Arrays.asList("", "abc"), "", Arrays.asList("", "abc")),
                    Arguments.of("V9", Arrays.asList("", "abc"), "a", List.of("abc")),
                    Arguments.of("V10", Arrays.asList("Dog", "dog", "hotDog"), "Dog", Arrays.asList("Dog", "hotDog")),
                    Arguments.of("V11", Arrays.asList("a b", "a-b", "ab"), "a-", List.of("a-b")),
                    Arguments.of("V12", Arrays.asList("ışık", "isik", "güz"), "ış", List.of("ışık"))
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("validClasses")
        void validEquivalenceClasses(String id, List<String> input, String substring, List<String> expected) {
            assertEquals(expected, solution.filterBySubstring(new ArrayList<>(input), substring),
                    () -> id + " substring=\"" + substring + "\"");
        }
    }

    @Nested
    @DisplayName("Boundaries")
    class Boundaries {

        @Test
        @DisplayName("List length around zero and one")
        void listLengthBoundary() {
            assertEquals(List.of(), solution.filterBySubstring(new ArrayList<>(List.of()), "a"));
            assertEquals(List.of("a"), solution.filterBySubstring(new ArrayList<>(List.of("a")), "a"));
            assertEquals(Arrays.asList("a", "cab"),
                    solution.filterBySubstring(new ArrayList<>(Arrays.asList("a", "b", "cab")), "a"));
        }

        @Test
        @DisplayName("Substring length empty, one, many")
        void substringLengthBoundary() {
            assertEquals(Arrays.asList("", "a"), solution.filterBySubstring(new ArrayList<>(Arrays.asList("", "a")), ""));
            assertEquals(List.of("a"), solution.filterBySubstring(new ArrayList<>(Arrays.asList("", "a")), "a"));
            assertEquals(List.of("abc"), solution.filterBySubstring(new ArrayList<>(Arrays.asList("ab", "abc")), "abc"));
        }

        @Test
        @DisplayName("Whole, prefix, middle, suffix, and absent positions")
        void matchPositionBoundary() {
            assertEquals(Arrays.asList("abc", "abcd", "zabc", "zzabcyy"),
                    solution.filterBySubstring(new ArrayList<>(Arrays.asList("abc", "abcd", "zabc", "zzabcyy", "ab")), "abc"));
        }
    }

    @Nested
    @DisplayName("Invalid or underspecified inputs")
    class InvalidOrPinned {

        @Test
        @DisplayName("Null list throws")
        void nullListThrows() {
            assertThrows(NullPointerException.class, () -> solution.filterBySubstring(null, "x"));
        }

        @Test
        @DisplayName("Null substring throws")
        void nullSubstringThrows() {
            assertThrows(NullPointerException.class,
                    () -> solution.filterBySubstring(new ArrayList<>(List.of("abc")), null));
        }

        @Test
        @DisplayName("Null element throws once inspected")
        void nullElementThrows() {
            assertThrows(NullPointerException.class,
                    () -> solution.filterBySubstring(new ArrayList<>(Arrays.asList("abc", null)), "a"));
        }
    }
}
