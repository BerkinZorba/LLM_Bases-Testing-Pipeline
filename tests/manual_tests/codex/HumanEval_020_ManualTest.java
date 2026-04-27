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

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Codex-authored executable manual suite derived from the black-box analysis.
 */
public class HumanEval_020_ManualTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> validClasses() {
        return Stream.of(
                Arguments.of("V1", Arrays.asList(8.0, 1.0), Arrays.asList(1.0, 8.0)),
                Arguments.of("V2", Arrays.asList(1.0, 1.1, 5.0), Arrays.asList(1.0, 1.1)),
                Arguments.of("V3", Arrays.asList(1.1, 2.2, 3.1, 4.1, 5.1), Arrays.asList(2.2, 3.1)),
                Arguments.of("V4", Arrays.asList(1.0, 4.0, 5.0, 5.9), Arrays.asList(5.0, 5.9)),
                Arguments.of("V5", Arrays.asList(1.0, 2.0, 2.0, 5.0), Arrays.asList(2.0, 2.0)),
                Arguments.of("V6", Arrays.asList(-5.0, -2.0, -2.5, 4.0), Arrays.asList(-2.5, -2.0)),
                Arguments.of("V7", Arrays.asList(-0.1, 0.0, 10.0, 9.75), Arrays.asList(-0.1, 0.0)),
                Arguments.of("V8", Arrays.asList(3.0, 1.0, 2.0), Arrays.asList(1.0, 2.0)),
                Arguments.of("V9", Arrays.asList(1.0, 2.0, 3.0), Arrays.asList(1.0, 2.0))
        );
    }

    @ParameterizedTest(name = "{0}: {1} -> {2}")
    @MethodSource("validClasses")
    @DisplayName("Valid equivalence classes")
    void validEquivalenceClasses(String id, List<Double> input, List<Double> expected) {
        assertEquals(expected, solution.findClosestElements(input));
    }

    @Test
    @DisplayName("Boundary: length zero")
    void lengthZeroReturnsEmptyList() {
        assertEquals(List.of(), solution.findClosestElements(List.of()));
    }

    @Test
    @DisplayName("Boundary: length one")
    void lengthOneReturnsEmptyList() {
        assertEquals(List.of(), solution.findClosestElements(List.of(42.0)));
    }

    @Test
    @DisplayName("Boundary: length two")
    void lengthTwoReturnsSortedPair() {
        assertEquals(Arrays.asList(1.0, 8.0), solution.findClosestElements(Arrays.asList(8.0, 1.0)));
    }

    @Test
    @DisplayName("Boundary: zero distance from duplicate values")
    void duplicateValuesHaveZeroDistance() {
        assertEquals(Arrays.asList(4.4, 4.4), solution.findClosestElements(Arrays.asList(4.4, 4.4, 1.0)));
    }

    @Test
    @DisplayName("Null list is pinned to empty list")
    void nullListReturnsEmptyList() {
        assertEquals(List.of(), solution.findClosestElements(null));
    }

    @Test
    @DisplayName("Original input list order remains unchanged")
    void originalInputOrderRemainsUnchanged() {
        List<Double> input = Arrays.asList(5.0, 1.0, 1.5);

        assertEquals(Arrays.asList(1.0, 1.5), solution.findClosestElements(input));
        assertEquals(Arrays.asList(5.0, 1.0, 1.5), input);
    }
}
