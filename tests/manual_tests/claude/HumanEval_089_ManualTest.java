import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Manual black-box JUnit 6 suite for HumanEval_089.
 * Cases are derived from tests/manual_tests/claude/HumanEval_089_blackbox.md.
 */
public class HumanEval_089_ManualTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Valid equivalence classes — spec-defined (V1-V7)")
    class SpecDefinedClasses {

        static Stream<Arguments> specCases() {
            return Stream.of(
                    Arguments.of("V1  empty string",           "",              ""),
                    Arguments.of("V2  single mid-alphabet",    "a",             "e"),
                    Arguments.of("V3  single near-end (wrap)", "z",             "d"),
                    Arguments.of("V4  lowercase no wrap",      "hi",            "lm"),
                    Arguments.of("V5  lowercase with wrap",    "wxyz",          "abcd"),
                    Arguments.of("V6  longer string",          "hellomyfriend", "lippsqcjvmirh"),
                    Arguments.of("V7  repeated chars",         "aaa",           "eee")
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("specCases")
        void specDefinedExpectations(String classId, String input, String expected) {
            assertEquals(expected, s.encrypt(input), classId);
        }
    }

    @Nested
    @DisplayName("Extended classes — implementation-defined pinned behavior (V8-V13)")
    class ExtendedClasses {

        static Stream<Arguments> extendedCases() {
            return Stream.of(
                    Arguments.of("V8  uppercase no wrap",      "AB",    "EF"),
                    Arguments.of("V9  uppercase wrap",         "WXYZ",  "ABCD"),
                    Arguments.of("V10 mixed lower/upper",      "aAbB",  "eEfF"),
                    Arguments.of("V11 digits pass through",    "a1e",   "e1i"),
                    Arguments.of("V12 spaces pass through",    "hi no", "lm rs"),
                    Arguments.of("V13 punctuation pass through","a!e.", "e!i.")
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("extendedCases")
        void extendedExpectations(String classId, String input, String expected) {
            assertEquals(expected, s.encrypt(input), classId);
        }
    }

    @Nested
    @DisplayName("Boundary analysis")
    class Boundaries {

        @Test
        @DisplayName("String length: 0 → 1 → many")
        void stringLengthBoundary() {
            assertEquals("", s.encrypt(""));
            assertEquals("e", s.encrypt("a"));
            assertEquals("lm", s.encrypt("hi"));
        }

        @Test
        @DisplayName("Lowercase wrap boundary: v→z (no wrap), w→a (first wrap)")
        void lowercaseWrapBoundary() {
            assertEquals("z", s.encrypt("v"));
            assertEquals("a", s.encrypt("w"));
            assertEquals("b", s.encrypt("x"));
            assertEquals("c", s.encrypt("y"));
            assertEquals("d", s.encrypt("z"));
        }

        @Test
        @DisplayName("Uppercase wrap boundary: V→Z (no wrap), W→A (first wrap)")
        void uppercaseWrapBoundary() {
            assertEquals("Z", s.encrypt("V"));
            assertEquals("A", s.encrypt("W"));
            assertEquals("D", s.encrypt("Z"));
        }

        @Test
        @DisplayName("All 26 lowercase letters produce correct +4 rotation")
        void allLowercaseLetters() {
            assertEquals("efghijklmnopqrstuvwxyzabcd", s.encrypt("abcdefghijklmnopqrstuvwxyz"));
        }
    }

    @Nested
    @DisplayName("Invalid / undefined-by-spec inputs (pinned behavior)")
    class InvalidOrPinned {

        @Test
        @DisplayName("I1: null input returns empty string")
        void nullInput_returnsEmpty() {
            assertEquals("", s.encrypt(null));
        }
    }
}
