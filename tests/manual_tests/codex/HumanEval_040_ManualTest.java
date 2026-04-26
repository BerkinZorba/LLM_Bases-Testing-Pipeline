import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Codex-authored executable manual suite derived from the black-box analysis.
 */
public class HumanEval_040_ManualTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> validClasses() {
        return Stream.of(
                Arguments.of("V1", Arrays.asList(1, 2), false),
                Arguments.of("V2", Arrays.asList(-1, 0, 1), true),
                Arguments.of("V3", Arrays.asList(1, 2, 4), false),
                Arguments.of("V4", Arrays.asList(1, 2, 3, 7), false),
                Arguments.of("V5", Arrays.asList(-5, -4, -1), false),
                Arguments.of("V6", Arrays.asList(-2, -2, 4, 8), true),
                Arguments.of("V7", Arrays.asList(0, 0, 0), true),
                Arguments.of("V8", Arrays.asList(0, 1, 2), false),
                Arguments.of("V9", Arrays.asList(2, 4, -5, 3, 9, 7), true),
                Arguments.of("V10", Arrays.asList(100, 3, 5, -100), false)
        );
    }

    @ParameterizedTest(name = "{0}: {1} -> {2}")
    @MethodSource("validClasses")
    @DisplayName("Valid equivalence classes")
    void validEquivalenceClasses(String id, List<Integer> input, boolean expected) {
        if (expected) {
            assertTrue(solution.triplesSumToZero(input));
        } else {
            assertFalse(solution.triplesSumToZero(input));
        }
    }

    @Test
    @DisplayName("Boundary: length zero")
    void lengthZeroReturnsFalse() {
        assertFalse(solution.triplesSumToZero(List.of()));
    }

    @Test
    @DisplayName("Boundary: length one")
    void lengthOneReturnsFalse() {
        assertFalse(solution.triplesSumToZero(List.of(1)));
    }

    @Test
    @DisplayName("Boundary: length two")
    void lengthTwoReturnsFalse() {
        assertFalse(solution.triplesSumToZero(Arrays.asList(1, -1)));
    }

    @Test
    @DisplayName("Boundary: exactly three values summing to zero")
    void lengthThreeTrueBoundary() {
        assertTrue(solution.triplesSumToZero(Arrays.asList(-1, 0, 1)));
    }

    @Test
    @DisplayName("Boundary: duplicate values as distinct elements")
    void duplicateValuesAsDistinctElements() {
        assertTrue(solution.triplesSumToZero(Arrays.asList(-2, -2, 4)));
    }

    @Test
    @DisplayName("Null list is pinned to false")
    void nullListReturnsFalse() {
        assertFalse(solution.triplesSumToZero(null));
    }

    @Test
    @DisplayName("Original input list order remains unchanged")
    void originalInputOrderRemainsUnchanged() {
        List<Integer> input = Arrays.asList(3, -2, 1, 1);

        assertTrue(solution.triplesSumToZero(input));
        assertTrue(input.equals(Arrays.asList(3, -2, 1, 1)));
    }
}
