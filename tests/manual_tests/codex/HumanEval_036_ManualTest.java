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
public class HumanEval_036_ManualTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> validClasses() {
        return Stream.of(
                Arguments.of("V1", 0, 0),
                Arguments.of("V2", 11, 0),
                Arguments.of("V3", 14, 0),
                Arguments.of("V4", 77, 0),
                Arguments.of("V5", 78, 2),
                Arguments.of("V6", 79, 3),
                Arguments.of("V7", 118, 4),
                Arguments.of("V8", 200, 6),
                Arguments.of("V9", 1000, 47)
        );
    }

    @ParameterizedTest(name = "{0}: n={1} -> {2}")
    @MethodSource("validClasses")
    @DisplayName("Valid equivalence classes")
    void validEquivalenceClasses(String id, int n, int expected) {
        assertEquals(expected, solution.fizzBuzz(n));
    }

    @Test
    @DisplayName("Negative bounds return zero in current implementation")
    void negativeBoundsReturnZero() {
        assertEquals(0, solution.fizzBuzz(-5));
        assertEquals(0, solution.fizzBuzz(Integer.MIN_VALUE));
    }

    @Test
    @DisplayName("Boundary crossing 78 includes 77 with two sevens")
    void boundaryCrossingSeventyEight() {
        assertEquals(2, solution.fizzBuzz(78) - solution.fizzBuzz(77));
    }

    @Test
    @DisplayName("Boundary crossing 79 includes 78 with one seven")
    void boundaryCrossingSeventyNine() {
        assertEquals(1, solution.fizzBuzz(79) - solution.fizzBuzz(78));
    }

    @Test
    @DisplayName("Boundary crossing 177 includes 176 once")
    void boundaryCrossingOneSeventySeven() {
        assertEquals(1, solution.fizzBuzz(177) - solution.fizzBuzz(176));
    }

    @Test
    @DisplayName("Divisor overlap does not create an extra count at 143")
    void overlapAtOneFortyThreeDoesNotChangeTotal() {
        assertEquals(0, solution.fizzBuzz(144) - solution.fizzBuzz(143));
    }
}
