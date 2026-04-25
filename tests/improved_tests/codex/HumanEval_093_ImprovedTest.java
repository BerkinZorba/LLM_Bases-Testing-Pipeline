import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Codex-authored improved JUnit 6 suite for HumanEval_093.
 *
 * Design goals:
 * - split the dataset's assertion-list harness into attributable checks;
 * - cover swap-case behavior separately from vowel replacement;
 * - pin null, empty, and non-letter behavior that is outside the prompt's
 *   "Assume only letters" domain.
 */
public class HumanEval_093_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> datasetCases() {
        return Stream.of(
                Arguments.of("TEST", "tgst"),
                Arguments.of("Mudasir", "mWDCSKR"),
                Arguments.of("YES", "ygs"),
                Arguments.of("This is a message", "tHKS KS C MGSSCGG"),
                Arguments.of("I DoNt KnOw WhAt tO WrItE", "k dQnT kNqW wHcT Tq wRkTg")
        );
    }

    static Stream<Arguments> extendedValidCases() {
        return Stream.of(
                Arguments.of("test", "TGST"),
                Arguments.of("aeiou", "CGKQW"),
                Arguments.of("AEIOU", "cgkqw"),
                Arguments.of("bcdfg", "BCDFG"),
                Arguments.of("BCDFG", "bcdfg"),
                Arguments.of("aA", "Cc"),
                Arguments.of("zZ", "Zz")
        );
    }

    @ParameterizedTest(name = "\"{0}\" -> \"{1}\"")
    @MethodSource("datasetCases")
    @DisplayName("Dataset expectations")
    void datasetExpectations(String input, String expected) {
        assertEquals(expected, solution.encode(input));
    }

    @ParameterizedTest(name = "\"{0}\" -> \"{1}\"")
    @MethodSource("extendedValidCases")
    @DisplayName("Extended valid letter cases")
    void extendedValidLetterCases(String input, String expected) {
        assertEquals(expected, solution.encode(input));
    }

    @Test
    @DisplayName("Empty input returns empty string")
    void emptyInputReturnsEmptyString() {
        assertEquals("", solution.encode(""));
    }

    @Test
    @DisplayName("Null input is pinned to empty string")
    void nullInputReturnsEmptyString() {
        assertEquals("", solution.encode(null));
    }

    @Test
    @DisplayName("Non-letters are pinned as unchanged")
    void nonLettersRemainUnchanged() {
        assertEquals("C 1!", solution.encode("a 1!"));
    }
}
