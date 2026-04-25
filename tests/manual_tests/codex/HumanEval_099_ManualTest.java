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
public class HumanEval_099_ManualTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> validClasses() {
        return Stream.of(
                Arguments.of("V1", "10", 10),
                Arguments.of("V2", "15.3", 15),
                Arguments.of("V3", "14.5", 15),
                Arguments.of("V4", "15.6", 16),
                Arguments.of("V5", "-14.49", -14),
                Arguments.of("V6", "-14.5", -15),
                Arguments.of("V7", "-14.51", -15),
                Arguments.of("V8", "0.5", 1),
                Arguments.of("V9", "-0.5", -1),
                Arguments.of("V10", "001.50", 2)
        );
    }

    @ParameterizedTest(name = "{0}: \"{1}\" -> {2}")
    @MethodSource("validClasses")
    @DisplayName("Valid equivalence classes")
    void validEquivalenceClasses(String id, String input, int expected) {
        assertEquals(expected, solution.closest_integer(input));
    }

    @Test
    @DisplayName("Boundary: exact zero")
    void exactZeroBoundary() {
        assertEquals(0, solution.closest_integer("0"));
    }

    @Test
    @DisplayName("Boundary: just below positive half")
    void justBelowPositiveHalfBoundary() {
        assertEquals(0, solution.closest_integer("0.49"));
    }

    @Test
    @DisplayName("Boundary: just below negative half magnitude")
    void justBelowNegativeHalfBoundary() {
        assertEquals(0, solution.closest_integer("-0.49"));
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
        assertThrows(NumberFormatException.class, () -> solution.closest_integer("not-a-number"));
    }
}
