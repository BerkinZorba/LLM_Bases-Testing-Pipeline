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
 * Codex-authored improved JUnit 6 suite for HumanEval_020.
 *
 * Design goals:
 * - split the dataset's assertion-list harness into attributable checks;
 * - exercise sorted-copy behavior without depending on input ordering;
 * - add coverage for two-element, duplicate, negative, tie, and invalid-size cases.
 */
public class HumanEval_020_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> datasetCases() {
        return Stream.of(
                Arguments.of(Arrays.asList(1.0, 2.0, 3.9, 4.0, 5.0, 2.2), Arrays.asList(3.9, 4.0)),
                Arguments.of(Arrays.asList(1.0, 2.0, 5.9, 4.0, 5.0), Arrays.asList(5.0, 5.9)),
                Arguments.of(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.2), Arrays.asList(2.0, 2.2)),
                Arguments.of(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.0), Arrays.asList(2.0, 2.0)),
                Arguments.of(Arrays.asList(1.1, 2.2, 3.1, 4.1, 5.1), Arrays.asList(2.2, 3.1))
        );
    }

    static Stream<Arguments> extendedCases() {
        return Stream.of(
                Arguments.of(Arrays.asList(8.0, 1.0), Arrays.asList(1.0, 8.0)),
                Arguments.of(Arrays.asList(-5.0, -2.0, -2.5, 4.0), Arrays.asList(-2.5, -2.0)),
                Arguments.of(Arrays.asList(0.0, -0.1, 10.0, 9.75), Arrays.asList(-0.1, 0.0)),
                Arguments.of(Arrays.asList(4.4, 4.4, 1.0), Arrays.asList(4.4, 4.4)),
                Arguments.of(Arrays.asList(100.0, 1.0, 50.0, 51.0), Arrays.asList(50.0, 51.0)),
                Arguments.of(Arrays.asList(1.0, 2.0, 3.0), Arrays.asList(1.0, 2.0))
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("datasetCases")
    @DisplayName("Dataset expectations")
    void datasetExpectations(List<Double> input, List<Double> expected) {
        assertEquals(expected, solution.findClosestElements(input));
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("extendedCases")
    @DisplayName("Extended branch and boundary cases")
    void extendedBranchAndBoundaryCases(List<Double> input, List<Double> expected) {
        assertEquals(expected, solution.findClosestElements(input));
    }

    @Test
    @DisplayName("Input order is not mutated")
    void inputOrderIsNotMutated() {
        List<Double> input = Arrays.asList(3.0, 1.0, 2.0);

        assertEquals(Arrays.asList(3.0, 1.0, 2.0), input);
        assertEquals(Arrays.asList(1.0, 2.0), solution.findClosestElements(input));
        assertEquals(Arrays.asList(3.0, 1.0, 2.0), input);
    }

    @Test
    @DisplayName("Null input is pinned to empty list")
    void nullInputReturnsEmptyList() {
        assertEquals(List.of(), solution.findClosestElements(null));
    }

    @Test
    @DisplayName("Singleton input is pinned to empty list")
    void singletonInputReturnsEmptyList() {
        assertEquals(List.of(), solution.findClosestElements(List.of(42.0)));
    }
}
