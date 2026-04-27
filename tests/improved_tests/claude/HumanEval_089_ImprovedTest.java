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
 * Improved JUnit 6 suite for HumanEval_089 (encrypt).
 *
 * Improvements over the dataset harness:
 *   - splits the original assertion roulette into named, parameterized rows;
 *   - covers the 5 branches missed by the base suite:
 *       null guard, empty-string guard, uppercase rotation, non-alphabetic pass-through;
 *   - adds wrap-around at end of alphabet (letters w-z),
 *     single-character cases, and mixed-content strings.
 */
public class HumanEval_089_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Dataset cases — one assertion per row")
    class DatasetCases {

        static Stream<Arguments> datasetCases() {
            return Stream.of(
                    Arguments.of("hi",            "lm"),
                    Arguments.of("asdfghjkl",     "ewhjklnop"),
                    Arguments.of("gf",            "kj"),
                    Arguments.of("et",            "ix"),
                    Arguments.of("faewfawefaewg", "jeiajeaijeiak"),
                    Arguments.of("hellomyfriend", "lippsqcjvmirh"),
                    Arguments.of("dxzdlmnilfuhmilufhlihufnmlimnufhlimnufhfucufh",
                                 "hbdhpqrmpjylqmpyjlpmlyjrqpmqryjlpmqryjljygyjl"),
                    Arguments.of("a",             "e")
            );
        }

        @ParameterizedTest(name = "encrypt(\"{0}\") = \"{1}\"")
        @MethodSource("datasetCases")
        void datasetExpectations(String input, String expected) {
            assertEquals(expected, s.encrypt(input));
        }
    }

    @Nested
    @DisplayName("Guard branches")
    class GuardBranches {

        @Test
        @DisplayName("null input returns empty string")
        void nullInput_returnsEmpty() {
            assertEquals("", s.encrypt(null));
        }

        @Test
        @DisplayName("Empty string returns empty string")
        void emptyString_returnsEmpty() {
            assertEquals("", s.encrypt(""));
        }
    }

    @Nested
    @DisplayName("Uppercase letter rotation")
    class UppercaseRotation {

        @Test
        @DisplayName("Uppercase A→E, B→F")
        void uppercaseAB() {
            assertEquals("EF", s.encrypt("AB"));
        }

        @Test
        @DisplayName("Uppercase wrap-around W→A, X→B, Y→C, Z→D")
        void uppercaseWrapAround() {
            assertEquals("ABCD", s.encrypt("WXYZ"));
        }

        @Test
        @DisplayName("Mixed lower and upper case — each rotated independently")
        void mixedCase() {
            // a→e, A→E, b→f, B→F
            assertEquals("eEfF", s.encrypt("aAbB"));
        }
    }

    @Nested
    @DisplayName("Non-alphabetic characters")
    class NonAlphabeticPassThrough {

        @Test
        @DisplayName("Digits pass through unchanged")
        void digitsUnchanged() {
            assertEquals("e1i", s.encrypt("a1e"));
        }

        @Test
        @DisplayName("Spaces pass through unchanged")
        void spacesUnchanged() {
            assertEquals("lm rs", s.encrypt("hi no"));
        }

        @Test
        @DisplayName("Punctuation passes through unchanged")
        void punctuationUnchanged() {
            assertEquals("e!i.", s.encrypt("a!e."));
        }
    }

    @Nested
    @DisplayName("Wrap-around at end of lowercase alphabet")
    class LowercaseWrapAround {

        @Test
        @DisplayName("w→a, x→b, y→c, z→d")
        void wxyzWrap() {
            assertEquals("abcd", s.encrypt("wxyz"));
        }

        @Test
        @DisplayName("Letters just before wrap: s→w, t→x, u→y, v→z")
        void justBeforeWrap() {
            assertEquals("wxyz", s.encrypt("stuv"));
        }
    }
}
