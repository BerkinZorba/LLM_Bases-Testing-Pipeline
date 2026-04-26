import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Codex-authored executable manual suite derived from the black-box analysis.
 */
public class HumanEval_093_ManualTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> validClasses() {
        return Stream.of(
                Arguments.of("V1", "bcdfg", "BCDFG"),
                Arguments.of("V2", "BCDFG", "bcdfg"),
                Arguments.of("V3", "aeiou", "CGKQW"),
                Arguments.of("V4", "AEIOU", "cgkqw"),
                Arguments.of("V5", "test", "TGST"),
                Arguments.of("V6", "Mudasir", "mWDCSKR"),
                Arguments.of("V7", "aA", "Cc"),
                Arguments.of("V8", "zZ", "Zz")
        );
    }

    @ParameterizedTest(name = "{0}: \"{1}\" -> \"{2}\"")
    @MethodSource("validClasses")
    @DisplayName("Valid equivalence classes")
    void validEquivalenceClasses(String id, String input, String expected) {
        assertEquals(expected, solution.encode(input));
    }

    @Test
    @DisplayName("Boundary: null reference")
    void nullReferenceReturnsEmptyString() {
        assertEquals("", solution.encode(null));
    }

    @Test
    @DisplayName("Boundary: empty string")
    void emptyStringReturnsEmptyString() {
        assertEquals("", solution.encode(""));
    }

    @Test
    @DisplayName("Boundary: single lowercase vowel")
    void singleLowercaseVowel() {
        assertEquals("C", solution.encode("a"));
    }

    @Test
    @DisplayName("Boundary: single uppercase vowel")
    void singleUppercaseVowel() {
        assertEquals("c", solution.encode("A"));
    }

    @Test
    @DisplayName("Boundary: single lowercase consonant")
    void singleLowercaseConsonant() {
        assertEquals("B", solution.encode("b"));
    }

    @Test
    @DisplayName("Boundary: single uppercase consonant")
    void singleUppercaseConsonant() {
        assertEquals("b", solution.encode("B"));
    }

    @Test
    @DisplayName("Spaces, digits, and punctuation are pinned as unchanged")
    void nonLettersRemainUnchanged() {
        assertEquals("C 1!", solution.encode("a 1!"));
    }
}
