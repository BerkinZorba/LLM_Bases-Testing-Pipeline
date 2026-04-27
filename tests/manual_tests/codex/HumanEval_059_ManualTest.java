import org.junit.jupiter.api.DisplayName;
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
 * Codex-authored executable manual suite derived from the black-box analysis.
 */
public class HumanEval_059_ManualTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> validClasses() {
        return Stream.of(
                Arguments.of("V1 smallest even composite", 4, 2),
                Arguments.of("V1 repeated factor two", 2048, 2),
                Arguments.of("V2 repeated odd prime", 27, 3),
                Arguments.of("V2 repeated odd prime larger power", 81, 3),
                Arguments.of("V3 mixed factors with odd tail", 15, 5),
                Arguments.of("V3 mixed factors with larger leftover prime", 255, 17),
                Arguments.of("V4 perfect square of odd prime", 49, 7),
                Arguments.of("V5 several distinct factors", 330, 11),
                Arguments.of("V5 several distinct factors larger max", 1287, 13)
        );
    }

    @ParameterizedTest(name = "{0} -> {2}")
    @MethodSource("validClasses")
    @DisplayName("Valid equivalence classes")
    void validEquivalenceClasses(String label, int input, int expected) {
        assertEquals(expected, solution.largestPrimeFactor(input), label);
    }

    @Test
    @DisplayName("Boundary odd composite nine returns three")
    void smallestOddCompositeReturnsThree() {
        assertEquals(3, solution.largestPrimeFactor(9));
    }

    @Test
    @DisplayName("Boundary leftover prime after division is returned")
    void leftoverPrimeAfterDivisionIsReturned() {
        assertEquals(23, solution.largestPrimeFactor(299));
    }

    @Test
    @DisplayName("Out-of-scope prime input currently returns itself")
    void primeInputCurrentlyReturnsItself() {
        assertEquals(13, solution.largestPrimeFactor(13));
    }

    @Test
    @DisplayName("Out-of-scope one currently returns sentinel one")
    void oneCurrentlyReturnsSentinelOne() {
        assertEquals(1, solution.largestPrimeFactor(1));
    }
}
