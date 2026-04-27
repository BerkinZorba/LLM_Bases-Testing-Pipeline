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
 * Manual black-box JUnit 6 suite for HumanEval_019.
 * Cases are derived from tests/manual_tests/claude/HumanEval_019_blackbox.md.
 */
public class HumanEval_019_ManualTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Valid equivalence classes (V1-V11)")
    class ValidClasses {

        static Stream<Arguments> validClasses() {
            return Stream.of(
                    Arguments.of("V1  empty string",                    "",
                            ""),
                    Arguments.of("V2  single word",                     "seven",
                            "seven"),
                    Arguments.of("V3  two words already in order",      "two nine",
                            "two nine"),
                    Arguments.of("V4  two words reversed",              "nine two",
                            "two nine"),
                    Arguments.of("V5  three words random order",        "three one five",
                            "one three five"),
                    Arguments.of("V6  all ten forward",
                            "zero one two three four five six seven eight nine",
                            "zero one two three four five six seven eight nine"),
                    Arguments.of("V7  all ten reverse",
                            "nine eight seven six five four three two one zero",
                            "zero one two three four five six seven eight nine"),
                    Arguments.of("V8  duplicate words",                 "five one three one five",
                            "one one three five five"),
                    Arguments.of("V9  all same word",                   "three three three",
                            "three three three"),
                    Arguments.of("V10 six-word unsorted",
                            "five zero four seven nine eight",
                            "zero four five seven eight nine"),
                    Arguments.of("V11 whitespace-only",                 "   ",
                            "")
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("validClasses")
        void allValidClasses(String classId, String input, String expected) {
            assertEquals(expected, s.sortNumbers(input), classId);
        }
    }

    @Nested
    @DisplayName("Boundary analysis")
    class Boundaries {

        @Test
        @DisplayName("Word count: 0 → 1 → 2")
        void wordCountBoundary() {
            assertEquals("", s.sortNumbers(""));
            assertEquals("one", s.sortNumbers("one"));
            assertEquals("one two", s.sortNumbers("two one"));
        }

        @Test
        @DisplayName("Boundary words: zero (min) and nine (max)")
        void minMaxWordsBoundary() {
            assertEquals("zero", s.sortNumbers("zero"));
            assertEquals("nine", s.sortNumbers("nine"));
            assertEquals("zero nine", s.sortNumbers("nine zero"));
        }

        @Test
        @DisplayName("Duplicate boundary: none → one pair → all same")
        void duplicateBoundary() {
            assertEquals("one two", s.sortNumbers("two one"));
            assertEquals("one one", s.sortNumbers("one one"));
            assertEquals("one one one", s.sortNumbers("one one one"));
        }
    }

    @Nested
    @DisplayName("Invalid / undefined-by-spec inputs (pinned behavior)")
    class InvalidOrPinned {

        @Test
        @DisplayName("I1: null input returns empty string")
        void nullInput_returnsEmpty() {
            assertEquals("", s.sortNumbers(null));
        }

        @Test
        @DisplayName("I2: unrecognised word causes NullPointerException")
        void unknownWord_throwsNpe() {
            assertThrows(NullPointerException.class,
                    () -> s.sortNumbers("one ten two"));
        }
    }
}
