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
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Manual black-box JUnit 6 suite for HumanEval_018.
 * Cases are derived from tests/manual_tests/claude/HumanEval_018_blackbox.md.
 */
public class HumanEval_018_ManualTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Valid equivalence classes (V1-V15)")
    class ValidClasses {

        static Stream<Arguments> validClasses() {
            return Stream.of(
                    Arguments.of("V1  empty string",                    "",            "x",    0),
                    Arguments.of("V2  empty substring",                 "abc",         "",     0),
                    Arguments.of("V3  substring longer than string",    "a",           "abc",  0),
                    Arguments.of("V4  substring equals entire string",  "abc",         "abc",  1),
                    Arguments.of("V5  no match",                        "abcdef",      "xyz",  0),
                    Arguments.of("V6  single match — prefix",           "abcxyz",      "abc",  1),
                    Arguments.of("V7  single match — suffix",           "xyzabc",      "abc",  1),
                    Arguments.of("V8  single match — middle",           "xabcz",       "abc",  1),
                    Arguments.of("V9  multiple non-overlapping",        "abcabcabc",   "abc",  3),
                    Arguments.of("V10 overlapping two chars",           "aaa",         "aa",   2),
                    Arguments.of("V11 overlapping prompt example",      "aaaa",        "aa",   3),
                    Arguments.of("V12 overlapping three chars",         "cacacacac",   "cac",  4),
                    Arguments.of("V13 single-char match",               "a",           "a",    1),
                    Arguments.of("V14 alternating single char",         "xyxyxyx",     "x",    4),
                    Arguments.of("V15 all same single char",            "aaa",         "a",    3)
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("validClasses")
        void allValidClasses(String classId, String string, String substring, int expected) {
            assertEquals(expected, s.howManyTimes(string, substring),
                    () -> classId + " string=\"" + string + "\" sub=\"" + substring + "\"");
        }
    }

    @Nested
    @DisplayName("Boundary analysis")
    class Boundaries {

        @Test
        @DisplayName("String length: 0 → 1 → many")
        void stringLengthBoundary() {
            assertEquals(0, s.howManyTimes("", "a"));
            assertEquals(1, s.howManyTimes("a", "a"));
            assertEquals(3, s.howManyTimes("aaa", "a"));
        }

        @Test
        @DisplayName("Substring length: 0 → 1 → equals string → exceeds string")
        void substringLengthBoundary() {
            assertEquals(0, s.howManyTimes("abc", ""));
            assertEquals(1, s.howManyTimes("abc", "a"));
            assertEquals(1, s.howManyTimes("abc", "abc"));
            assertEquals(0, s.howManyTimes("abc", "abcd"));
        }

        @Test
        @DisplayName("Match at first position only")
        void matchAtFirstPositionOnly() {
            assertEquals(1, s.howManyTimes("abcxxx", "abc"));
        }

        @Test
        @DisplayName("Match at last valid position only")
        void matchAtLastPositionOnly() {
            assertEquals(1, s.howManyTimes("xxxabc", "abc"));
        }

        @Test
        @DisplayName("Overlap boundary: aa in aaa vs aa in aa")
        void overlapBoundary() {
            assertEquals(1, s.howManyTimes("aa", "aa"));
            assertEquals(2, s.howManyTimes("aaa", "aa"));
        }
    }

    @Nested
    @DisplayName("Invalid / undefined-by-spec inputs (pinned behavior)")
    class InvalidOrPinned {

        @Test
        @DisplayName("I1: null string throws NullPointerException")
        void nullStringThrowsNpe() {
            assertThrows(NullPointerException.class,
                    () -> s.howManyTimes(null, "a"));
        }

        @Test
        @DisplayName("I2: null substring throws NullPointerException")
        void nullSubstringThrowsNpe() {
            assertThrows(NullPointerException.class,
                    () -> s.howManyTimes("abc", null));
        }
    }
}
