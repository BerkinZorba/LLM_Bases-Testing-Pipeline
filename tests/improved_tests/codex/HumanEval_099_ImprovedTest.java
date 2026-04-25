import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Codex-authored improved JUnit 6 suite for HumanEval_099.
 *
 * Design goals:
 * - split the dataset's assertion-list harness into attributable checks;
 * - cover positive and negative rounding on both sides of the .5 boundary;
 * - pin null, empty, and invalid numeric strings separately from valid inputs.
 */
public class HumanEval_099_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> datasetCases() {
        return Stream.of(
                Arguments.of("10", 10),
                Arguments.of("14.5", 15),
                Arguments.of("-15.5", -16),
                Arguments.of("15.3", 15),
                Arguments.of("0", 0)
        );
    }

    static Stream<Arguments> extendedValidCases() {
        return Stream.of(
                Arguments.of("15.6", 16),
                Arguments.of("15.49", 15),
                Arguments.of("-14.5", -15),
                Arguments.of("-14.49", -14),
                Arguments.of("-14.51", -15),
                Arguments.of("0.5", 1),
                Arguments.of("-0.5", -1),
                Arguments.of("0.49", 0),
                Arguments.of("-0.49", 0),
                Arguments.of("001.50", 2)
        );
    }

    @ParameterizedTest(name = "\"{0}\" -> {1}")
    @MethodSource("datasetCases")
    @DisplayName("Dataset expectations")
    void datasetExpectations(String input, int expected) {
        assertEquals(expected, solution.closest_integer(input));
    }

    @ParameterizedTest(name = "\"{0}\" -> {1}")
    @MethodSource("extendedValidCases")
    @DisplayName("Extended rounding cases")
    void extendedRoundingCases(String input, int expected) {
        assertEquals(expected, solution.closest_integer(input));
    }

    @Test
    @DisplayName("Null input is pinned to zero")
    void nullInputReturnsZero() {
        assertEquals(0, solution.closest_integer(null));
    }

    @Test
    @DisplayName("Empty input is pinned to zero")
    void emptyInputReturnsZero() {
        assertEquals(0, solution.closest_integer(""));
    }

    @Test
    @DisplayName("Invalid numeric string throws NumberFormatException")
    void invalidNumberThrows() {
        assertThrows(NumberFormatException.class, () -> solution.closest_integer("abc"));
    }
}
