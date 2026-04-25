import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Codex-authored executable manual suite derived from the black-box analysis.
 */
public class HumanEval_106_ManualTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> validClasses() {
        return Stream.of(
                Arguments.of("V1", 1, List.of(1)),
                Arguments.of("V2", 2, Arrays.asList(1, 2)),
                Arguments.of("V3", 3, Arrays.asList(1, 2, 6)),
                Arguments.of("V4", 5, Arrays.asList(1, 2, 6, 24, 15)),
                Arguments.of("V5", 7, Arrays.asList(1, 2, 6, 24, 15, 720, 28)),
                Arguments.of("V6", 8, Arrays.asList(1, 2, 6, 24, 15, 720, 28, 40320))
        );
    }

    @ParameterizedTest(name = "{0}: f({1}) -> {2}")
    @MethodSource("validClasses")
    @DisplayName("Valid equivalence classes")
    void validEquivalenceClasses(String id, int n, List<Integer> expected) {
        assertEquals(expected, solution.f(n));
    }

    @Test
    @DisplayName("Boundary: negative n")
    void negativeNReturnsEmptyList() {
        assertEquals(List.of(), solution.f(-1));
    }

    @Test
    @DisplayName("Boundary: zero")
    void zeroReturnsEmptyList() {
        assertEquals(List.of(), solution.f(0));
    }

    @Test
    @DisplayName("Result length equals n for positive n")
    void resultLengthEqualsN() {
        assertEquals(6, solution.f(6).size());
    }

    @Test
    @DisplayName("Odd positions are triangular sums")
    void oddPositionsAreTriangularSums() {
        List<Integer> result = solution.f(7);

        assertEquals(1, result.get(0));
        assertEquals(6, result.get(2));
        assertEquals(15, result.get(4));
        assertEquals(28, result.get(6));
    }

    @Test
    @DisplayName("Even positions are factorials")
    void evenPositionsAreFactorials() {
        List<Integer> result = solution.f(8);

        assertEquals(2, result.get(1));
        assertEquals(24, result.get(3));
        assertEquals(720, result.get(5));
        assertEquals(40320, result.get(7));
    }
}
