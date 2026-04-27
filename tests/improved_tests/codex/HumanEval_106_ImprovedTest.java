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
 * Codex-authored improved JUnit 6 suite for HumanEval_106.
 *
 * Design goals:
 * - split the dataset's assertion-list harness into attributable checks;
 * - cover empty/negative-size behavior and early boundaries;
 * - exercise larger even factorial and odd triangular-number positions.
 */
public class HumanEval_106_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> datasetCases() {
        return Stream.of(
                Arguments.of(5, Arrays.asList(1, 2, 6, 24, 15)),
                Arguments.of(7, Arrays.asList(1, 2, 6, 24, 15, 720, 28)),
                Arguments.of(1, List.of(1)),
                Arguments.of(3, Arrays.asList(1, 2, 6))
        );
    }

    static Stream<Arguments> extendedCases() {
        return Stream.of(
                Arguments.of(0, List.of()),
                Arguments.of(2, Arrays.asList(1, 2)),
                Arguments.of(4, Arrays.asList(1, 2, 6, 24)),
                Arguments.of(6, Arrays.asList(1, 2, 6, 24, 15, 720)),
                Arguments.of(8, Arrays.asList(1, 2, 6, 24, 15, 720, 28, 40320))
        );
    }

    @ParameterizedTest(name = "f({0}) -> {1}")
    @MethodSource("datasetCases")
    @DisplayName("Dataset expectations")
    void datasetExpectations(int n, List<Integer> expected) {
        assertEquals(expected, solution.f(n));
    }

    @ParameterizedTest(name = "f({0}) -> {1}")
    @MethodSource("extendedCases")
    @DisplayName("Extended boundary and parity cases")
    void extendedBoundaryAndParityCases(int n, List<Integer> expected) {
        assertEquals(expected, solution.f(n));
    }

    @Test
    @DisplayName("Negative n is pinned to empty list")
    void negativeNReturnsEmptyList() {
        assertEquals(List.of(), solution.f(-3));
    }

    @Test
    @DisplayName("Repeated calls are stable")
    void repeatedCallsAreStable() {
        List<Integer> expected = Arrays.asList(1, 2, 6, 24, 15);

        assertEquals(expected, solution.f(5));
        assertEquals(expected, solution.f(5));
    }
}
