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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Manual black-box JUnit 6 suite for HumanEval_007.
 * Cases are derived from tests/manual_tests/claude/HumanEval_007_blackbox.md.
 */
public class HumanEval_007_ManualTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Valid equivalence classes (V1-V12)")
    class ValidClasses {

        static Stream<Arguments> validClasses() {
            return Stream.of(
                    Arguments.of("V1 empty list", List.of(), "john", List.of()),
                    Arguments.of("V2 no matches", Arrays.asList("abc", "def"), "x", List.of()),
                    Arguments.of("V3 single exact match", List.of("abc"), "abc", List.of("abc")),
                    Arguments.of("V4 prefix match", Arrays.asList("abc", "zab"), "ab", Arrays.asList("abc", "zab")),
                    Arguments.of("V5 middle match", Arrays.asList("grunt", "trumpet", "prune", "gruesome"), "run",
                            Arrays.asList("grunt", "prune")),
                    Arguments.of("V6 suffix match", Arrays.asList("hello", "shell", "hero"), "ell",
                            Arrays.asList("hello", "shell")),
                    Arguments.of("V7 duplicates", Arrays.asList("aa", "b", "aa"), "aa", Arrays.asList("aa", "aa")),
                    Arguments.of("V8 empty substring", Arrays.asList("", "a"), "", Arrays.asList("", "a")),
                    Arguments.of("V9 empty element excluded", Arrays.asList("", "abc"), "a", List.of("abc")),
                    Arguments.of("V10 case-sensitive", Arrays.asList("Alpha", "alpha", "ALPHA"), "A",
                            Arrays.asList("Alpha", "ALPHA")),
                    Arguments.of("V11 whitespace punctuation", Arrays.asList("a b", "ab", "a-b"), "a ", List.of("a b")),
                    Arguments.of("V12 repeated suffix matches", Arrays.asList("cafe", "cafe", "cafe"), "fe",
                            Arrays.asList("cafe", "cafe", "cafe"))
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("validClasses")
        void allValidClasses(String classId, List<String> input, String substring, List<String> expected) {
            assertEquals(expected, s.filterBySubstring(new ArrayList<>(input), substring),
                    () -> classId + " substring=\"" + substring + "\"");
        }
    }

    @Nested
    @DisplayName("Boundary analysis")
    class Boundaries {

        @Test
        @DisplayName("List size zero / one / many")
        void listSizeBoundary() {
            assertEquals(List.of(), s.filterBySubstring(new ArrayList<>(List.of()), "a"));
            assertEquals(List.of("abc"), s.filterBySubstring(new ArrayList<>(List.of("abc")), "a"));
            assertEquals(Arrays.asList("abc", "cab"),
                    s.filterBySubstring(new ArrayList<>(Arrays.asList("abc", "bbb", "cab")), "a"));
        }

        @Test
        @DisplayName("Substring length zero vs one")
        void substringLengthBoundary() {
            assertEquals(Arrays.asList("x", "y"),
                    s.filterBySubstring(new ArrayList<>(Arrays.asList("x", "y")), ""));
            assertEquals(List.of("x"),
                    s.filterBySubstring(new ArrayList<>(Arrays.asList("x", "y")), "x"));
        }

        @Test
        @DisplayName("Whole-string, prefix, middle, suffix matches")
        void matchPositionBoundary() {
            assertEquals(Arrays.asList("abc", "abcd", "zabc", "zzabcyy"),
                    s.filterBySubstring(new ArrayList<>(Arrays.asList("abc", "abcd", "zabc", "zzabcyy", "ab")), "abc"));
        }
    }

    @Nested
    @DisplayName("Invalid / undefined-by-spec inputs (pinned behavior)")
    class InvalidOrPinned {

        @Test
        @DisplayName("I1: null list throws NullPointerException")
        void nullListThrowsNpe() {
            assertThrows(NullPointerException.class,
                    () -> s.filterBySubstring(null, "a"));
        }

        @Test
        @DisplayName("I2: null substring throws NullPointerException")
        void nullSubstringThrowsNpe() {
            assertThrows(NullPointerException.class,
                    () -> s.filterBySubstring(new ArrayList<>(List.of("abc")), null));
        }

        @Test
        @DisplayName("I3: null element throws NullPointerException")
        void nullElementThrowsNpe() {
            assertThrows(NullPointerException.class,
                    () -> s.filterBySubstring(new ArrayList<>(Arrays.asList("abc", null)), "a"));
        }
    }
}
