import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Manual black-box JUnit 6 suite for HumanEval_093.
 * Cases are derived from tests/manual_tests/claude/HumanEval_093_blackbox.md.
 */
public class HumanEval_093_ManualTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Valid equivalence classes — spec-defined (V1-V12)")
    class SpecDefinedClasses {

        static Stream<Arguments> specCases() {
            return Stream.of(
                    Arguments.of("V1  empty string",               "",            ""),
                    Arguments.of("V2  single lowercase vowel a",   "a",           "C"),
                    Arguments.of("V3  single uppercase vowel E",   "E",           "g"),
                    Arguments.of("V4  single lowercase consonant", "b",           "B"),
                    Arguments.of("V5  single uppercase consonant", "T",           "t"),
                    Arguments.of("V6  all lowercase vowels",       "aeiou",       "CGKQW"),
                    Arguments.of("V7  all uppercase vowels",       "AEIOU",       "cgkqw"),
                    Arguments.of("V8  mixed consonants",           "bTsT",        "BtSt"),
                    Arguments.of("V9  lowercase vowels+consonants","message",     "MGSSCGG"),
                    Arguments.of("V10 uppercase vowels+consonants","YES",         "ygs"),
                    Arguments.of("V11 full mixed case+spaces",     "This is a message", "tHKS KS C MGSSCGG"),
                    Arguments.of("V12 y/Y are not vowels",         "yYy",         "YyY")
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("specCases")
        void specDefinedExpectations(String classId, String input, String expected) {
            assertEquals(expected, s.encode(input), classId);
        }
    }

    @Nested
    @DisplayName("Extended classes — space pass-through (V13-V14)")
    class ExtendedClasses {

        @Test
        @DisplayName("V13: single space passes through")
        void singleSpace() {
            assertEquals(" ", s.encode(" "));
        }

        @Test
        @DisplayName("V14: leading and trailing spaces preserved, letters encoded")
        void leadingTrailingSpaces() {
            assertEquals(" C ", s.encode(" a "));
        }
    }

    @Nested
    @DisplayName("Boundary conditions")
    class Boundaries {

        @Test
        @DisplayName("String length 0 → 1 (consonant) → 1 (vowel)")
        void stringLengthBoundary() {
            assertEquals("", s.encode(""));
            assertEquals("B", s.encode("b"));
            assertEquals("C", s.encode("a"));
        }

        @Test
        @DisplayName("Last vowel: u → W (u swapped to U, U+2=W)")
        void lastVowelU() {
            assertEquals("W", s.encode("u"));
            assertEquals("w", s.encode("U"));
        }

        @Test
        @DisplayName("Non-vowel y and Y: only swap, no +2 shift")
        void yBoundary() {
            assertEquals("Y", s.encode("y"));
            assertEquals("y", s.encode("Y"));
        }

        @Test
        @DisplayName("Each individual vowel isolated: a,e,i,o,u and A,E,I,O,U")
        void eachVowelIsolated() {
            assertEquals("C", s.encode("a"));
            assertEquals("G", s.encode("e"));
            assertEquals("K", s.encode("i"));
            assertEquals("Q", s.encode("o"));
            assertEquals("W", s.encode("u"));
            assertEquals("c", s.encode("A"));
            assertEquals("g", s.encode("E"));
            assertEquals("k", s.encode("I"));
            assertEquals("q", s.encode("O"));
            assertEquals("w", s.encode("U"));
        }
    }

    @Nested
    @DisplayName("Invalid / undefined-by-spec inputs (I1 — pinned)")
    class InvalidOrPinned {

        @Test
        @DisplayName("I1: null input returns empty string")
        void nullInput_returnsEmpty() {
            assertEquals("", s.encode(null));
        }
    }
}
