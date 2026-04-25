import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Codex-authored improved JUnit 6 suite for HumanEval_089.
 *
 * Design goals:
 * - split the dataset's assertion-list harness into attributable checks;
 * - add boundary coverage for empty, null, alphabet wraparound, and full alphabet;
 * - pin underspecified non-lowercase behavior separately from prompt-valid inputs.
 */
public class HumanEval_089_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> datasetCases() {
        return Stream.of(
                Arguments.of("hi", "lm"),
                Arguments.of("asdfghjkl", "ewhjklnop"),
                Arguments.of("gf", "kj"),
                Arguments.of("et", "ix"),
                Arguments.of("faewfawefaewg", "jeiajeaijeiak"),
                Arguments.of("hellomyfriend", "lippsqcjvmirh"),
                Arguments.of("dxzdlmnilfuhmilufhlihufnmlimnufhlimnufhfucufh",
                        "hbdhpqrmpjylqmpyjlpmlyjrqpmqryjlpmqryjljygyjl"),
                Arguments.of("a", "e")
        );
    }

    static Stream<Arguments> extendedValidCases() {
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of("z", "d"),
                Arguments.of("wxyz", "abcd"),
                Arguments.of("abcxyz", "efgbcd"),
                Arguments.of("abcdefghijklmnopqrstuvwxyz", "efghijklmnopqrstuvwxyzabcd"),
                Arguments.of("zzzz", "dddd")
        );
    }

    @ParameterizedTest(name = "\"{0}\" -> \"{1}\"")
    @MethodSource("datasetCases")
    @DisplayName("Dataset expectations")
    void datasetExpectations(String input, String expected) {
        assertEquals(expected, solution.encrypt(input));
    }

    @ParameterizedTest(name = "\"{0}\" -> \"{1}\"")
    @MethodSource("extendedValidCases")
    @DisplayName("Extended valid lowercase cases")
    void extendedValidLowercaseCases(String input, String expected) {
        assertEquals(expected, solution.encrypt(input));
    }

    @Test
    @DisplayName("Null input is pinned to empty string")
    void nullInputReturnsEmptyString() {
        assertEquals("", solution.encrypt(null));
    }

    @Test
    @DisplayName("Uppercase input is pinned to implementation behavior")
    void uppercaseInputPinned() {
        assertEquals("_", solution.encrypt("A"));
    }

    @Test
    @DisplayName("Space input is pinned to implementation behavior")
    void spaceInputPinned() {
        assertEquals("X", solution.encrypt(" "));
    }
}
