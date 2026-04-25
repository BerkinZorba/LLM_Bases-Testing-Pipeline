import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Codex-authored executable manual suite derived from the black-box analysis.
 */
public class HumanEval_016_ManualTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> validClasses() {
        return Stream.of(
                Arguments.of("V1", "", 0),
                Arguments.of("V2", "Q", 1),
                Arguments.of("V3", "abcde", 5),
                Arguments.of("V4", "xXxXX", 1),
                Arguments.of("V5", "Aa aA", 2),
                Arguments.of("V6", "001122", 3),
                Arguments.of("V7", "!?.,;", 5),
                Arguments.of("V8", "A1a1B2", 4),
                Arguments.of("V9", " \n\t", 3),
                Arguments.of("V10", "ÇçĞğ", 2)
        );
    }

    @ParameterizedTest(name = "{0} -> {2}")
    @MethodSource("validClasses")
    @DisplayName("Valid equivalence classes")
    void validEquivalenceClasses(String id, String input, int expected) {
        assertEquals(expected, solution.countDistinctCharacters(input));
    }

    @Test
    @DisplayName("Boundary: first duplicate reduces growth")
    void firstDuplicateBoundary() {
        assertEquals(3, solution.countDistinctCharacters("abca"));
    }

    @Test
    @DisplayName("Boundary: case duplicate does not increase distinct count")
    void caseDuplicateBoundary() {
        assertEquals(4, solution.countDistinctCharacters("abcABCd"));
    }

    @Test
    @DisplayName("Boundary: full digit set")
    void fullDigitSetBoundary() {
        assertEquals(10, solution.countDistinctCharacters("0123456789"));
    }

    @Test
    @DisplayName("Whitespace forms are distinct")
    void whitespaceFormsAreDistinct() {
        assertEquals(3, solution.countDistinctCharacters(" \n\t"));
    }

    @Test
    @DisplayName("Null input throws NullPointerException")
    void nullInputThrows() {
        assertThrows(NullPointerException.class, () -> solution.countDistinctCharacters(null));
    }

    @Test
    @DisplayName("Single emoji is counted as two UTF-16 code units")
    void emojiIsTwoUtf16Units() {
        assertEquals(2, solution.countDistinctCharacters("😀"));
    }

    @Test
    @DisplayName("Turkish I family pinned to current implementation behavior")
    void turkishIFamilyPinned() {
        assertEquals(3, solution.countDistinctCharacters("İIıi"));
    }
}
