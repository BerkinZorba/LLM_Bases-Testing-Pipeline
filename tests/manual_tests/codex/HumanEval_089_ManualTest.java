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
public class HumanEval_089_ManualTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> validClasses() {
        return Stream.of(
                Arguments.of("V1", "", ""),
                Arguments.of("V2", "a", "e"),
                Arguments.of("V3", "z", "d"),
                Arguments.of("V4", "hi", "lm"),
                Arguments.of("V5", "abcxyz", "efgbcd"),
                Arguments.of("V6", "abcdefghijklmnopqrstuvwxyz", "efghijklmnopqrstuvwxyzabcd"),
                Arguments.of("V7", "zzzz", "dddd"),
                Arguments.of("V8", "hellomyfriend", "lippsqcjvmirh")
        );
    }

    @ParameterizedTest(name = "{0}: \"{1}\" -> \"{2}\"")
    @MethodSource("validClasses")
    @DisplayName("Valid equivalence classes")
    void validEquivalenceClasses(String id, String input, String expected) {
        assertEquals(expected, solution.encrypt(input));
    }

    @Test
    @DisplayName("Boundary: null reference")
    void nullReferenceReturnsEmptyString() {
        assertEquals("", solution.encrypt(null));
    }

    @Test
    @DisplayName("Boundary: first wrap block")
    void firstWrapBlockBoundary() {
        assertEquals("abcd", solution.encrypt("wxyz"));
    }

    @Test
    @DisplayName("Uppercase letter is pinned to implementation behavior")
    void uppercaseLetterPinned() {
        assertEquals("_", solution.encrypt("A"));
    }

    @Test
    @DisplayName("Space character is pinned to implementation behavior")
    void spaceCharacterPinned() {
        assertEquals("X", solution.encrypt(" "));
    }

    @Test
    @DisplayName("Digit character is pinned to implementation behavior")
    void digitCharacterPinned() {
        assertEquals("O", solution.encrypt("1"));
    }

    @Test
    @DisplayName("Output length matches non-null input length")
    void outputLengthMatchesInputLength() {
        String input = "azbycx";

        assertEquals(input.length(), solution.encrypt(input).length());
    }
}
