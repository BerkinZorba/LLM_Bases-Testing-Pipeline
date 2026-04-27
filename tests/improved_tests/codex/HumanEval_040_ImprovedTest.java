import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Codex-authored improved JUnit 6 suite for HumanEval_040.
 *
 * Design goals:
 * - split the dataset's assertion-list harness into attributable checks;
 * - cover the two-pointer branches for equal, negative, and positive sums;
 * - add boundary cases for null, short lists, duplicates, all-zero triples,
 *   and input order preservation.
 */
public class HumanEval_040_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> datasetCases() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 3, 5, 0), false),
                Arguments.of(Arrays.asList(1, 3, 5, -1), false),
                Arguments.of(Arrays.asList(1, 3, -2, 1), true),
                Arguments.of(Arrays.asList(1, 2, 3, 7), false),
                Arguments.of(Arrays.asList(1, 2, 5, 7), false),
                Arguments.of(Arrays.asList(2, 4, -5, 3, 9, 7), true),
                Arguments.of(Arrays.asList(1), false),
                Arguments.of(Arrays.asList(1, 3, 5, -100), false),
                Arguments.of(Arrays.asList(100, 3, 5, -100), false)
        );
    }

    static Stream<Arguments> trueCases() {
        return Stream.of(
                Arguments.of(Arrays.asList(-1, 0, 1)),
                Arguments.of(Arrays.asList(0, 0, 0)),
                Arguments.of(Arrays.asList(-4, 1, 2, 3, 9)),
                Arguments.of(Arrays.asList(5, -2, -3, 10)),
                Arguments.of(Arrays.asList(-2, -2, 4, 8)),
                Arguments.of(Arrays.asList(-7, 2, 5, 9, -1))
        );
    }

    static Stream<Arguments> falseCases() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2)),
                Arguments.of(Arrays.asList()),
                Arguments.of(Arrays.asList(1, 2, 4)),
                Arguments.of(Arrays.asList(-5, -4, -1)),
                Arguments.of(Arrays.asList(0, 1, 2)),
                Arguments.of(Arrays.asList(-10, 1, 2, 3))
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("datasetCases")
    @DisplayName("Dataset expectations")
    void datasetExpectations(List<Integer> input, boolean expected) {
        if (expected) {
            assertTrue(solution.triplesSumToZero(input));
        } else {
            assertFalse(solution.triplesSumToZero(input));
        }
    }

    @ParameterizedTest(name = "{0} has a zero-sum triple")
    @MethodSource("trueCases")
    @DisplayName("Extended true cases")
    void extendedTrueCases(List<Integer> input) {
        assertTrue(solution.triplesSumToZero(input));
    }

    @ParameterizedTest(name = "{0} has no zero-sum triple")
    @MethodSource("falseCases")
    @DisplayName("Extended false cases")
    void extendedFalseCases(List<Integer> input) {
        assertFalse(solution.triplesSumToZero(input));
    }

    @Test
    @DisplayName("Null input is pinned to false")
    void nullInputReturnsFalse() {
        assertFalse(solution.triplesSumToZero(null));
    }

    @Test
    @DisplayName("Input list order is not mutated")
    void inputOrderIsNotMutated() {
        List<Integer> input = Arrays.asList(3, -2, 1, 1);

        assertTrue(solution.triplesSumToZero(input));
        assertTrue(input.equals(Arrays.asList(3, -2, 1, 1)));
    }
}
