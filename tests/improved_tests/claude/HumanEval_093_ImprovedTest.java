import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Improved JUnit 6 suite for HumanEval_093 (encode).
 *
 * Improvements over the dataset harness:
 *   - splits assertion-roulette block into individually named / parameterized rows;
 *   - covers the 2 branches missed by the base suite: null guard and empty-string guard;
 *   - adds focused single-character tests for every vowel in both cases;
 *   - adds focused single-character tests for consonants (upper and lower);
 *   - adds a space-only string to exercise the space pass-through without letter processing;
 *   - adds wrap-awareness verification (y is NOT a vowel; 'x' vowel shift would yield 'Z').
 */
public class HumanEval_093_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Dataset cases — one assertion per row")
    class DatasetCases {

        static Stream<Arguments> datasetCases() {
            return Stream.of(
                    Arguments.of("TEST",                           "tgst"),
                    Arguments.of("Mudasir",                        "mWDCSKR"),
                    Arguments.of("YES",                            "ygs"),
                    Arguments.of("This is a message",              "tHKS KS C MGSSCGG"),
                    Arguments.of("I DoNt KnOw WhAt tO WrItE",      "k dQnT kNqW wHcT Tq wRkTg")
            );
        }

        @ParameterizedTest(name = "encode(\"{0}\") = \"{1}\"")
        @MethodSource("datasetCases")
        void datasetExpectations(String input, String expected) {
            assertEquals(expected, s.encode(input));
        }
    }

    @Nested
    @DisplayName("Guard branches")
    class GuardBranches {

        @Test
        @DisplayName("null input returns empty string")
        void nullInput_returnsEmpty() {
            assertEquals("", s.encode(null));
        }

        @Test
        @DisplayName("Empty string returns empty string")
        void emptyString_returnsEmpty() {
            assertEquals("", s.encode(""));
        }
    }

    @Nested
    @DisplayName("Lowercase vowels: swap case then shift +2")
    class LowercaseVowels {

        static Stream<Arguments> lowercaseVowelCases() {
            return Stream.of(
                    // lowercase vowel → uppercase + 2 in ASCII
                    Arguments.of("a", "C"),   // a→A+2=C
                    Arguments.of("e", "G"),   // e→E+2=G
                    Arguments.of("i", "K"),   // i→I+2=K
                    Arguments.of("o", "Q"),   // o→O+2=Q
                    Arguments.of("u", "W")    // u→U+2=W
            );
        }

        @ParameterizedTest(name = "encode(\"{0}\") = \"{1}\"")
        @MethodSource("lowercaseVowelCases")
        void lowercaseVowelShift(String input, String expected) {
            assertEquals(expected, s.encode(input));
        }
    }

    @Nested
    @DisplayName("Uppercase vowels: swap case then shift +2")
    class UppercaseVowels {

        static Stream<Arguments> uppercaseVowelCases() {
            return Stream.of(
                    // uppercase vowel → lowercase + 2 in ASCII
                    Arguments.of("A", "c"),   // A→a+2=c
                    Arguments.of("E", "g"),   // E→e+2=g
                    Arguments.of("I", "k"),   // I→i+2=k
                    Arguments.of("O", "q"),   // O→o+2=q
                    Arguments.of("U", "w")    // U→u+2=w
            );
        }

        @ParameterizedTest(name = "encode(\"{0}\") = \"{1}\"")
        @MethodSource("uppercaseVowelCases")
        void uppercaseVowelShift(String input, String expected) {
            assertEquals(expected, s.encode(input));
        }
    }

    @Nested
    @DisplayName("Consonants: swap case only, no shift")
    class Consonants {

        @Test
        @DisplayName("Lowercase consonant → uppercase (no shift)")
        void lowercaseConsonant_swapOnly() {
            assertEquals("B", s.encode("b"));
            assertEquals("T", s.encode("t"));
            assertEquals("S", s.encode("s"));
        }

        @Test
        @DisplayName("Uppercase consonant → lowercase (no shift)")
        void uppercaseConsonant_swapOnly() {
            assertEquals("b", s.encode("B"));
            assertEquals("t", s.encode("T"));
            assertEquals("s", s.encode("S"));
        }

        @Test
        @DisplayName("Y and y are not vowels — swapped but not shifted")
        void yIsNotAVowel() {
            assertEquals("Y", s.encode("y"));
            assertEquals("y", s.encode("Y"));
        }
    }

    @Nested
    @DisplayName("Space pass-through")
    class SpacePassThrough {

        @Test
        @DisplayName("Single space passes through unchanged")
        void singleSpace() {
            assertEquals(" ", s.encode(" "));
        }

        @Test
        @DisplayName("Space-only string passes through unchanged")
        void spacesOnly() {
            assertEquals("   ", s.encode("   "));
        }
    }
}
