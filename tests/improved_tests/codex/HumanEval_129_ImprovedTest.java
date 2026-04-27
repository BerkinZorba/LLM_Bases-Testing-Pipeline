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
 * Codex-authored improved JUnit 6 suite for HumanEval_129.
 *
 * Design goals:
 * - split the dataset's assertion-list harness into attributable checks;
 * - cover positions where 1 is in a corner, edge, and interior;
 * - add boundary cases for k <= 0 and null/empty grids as underspecified inputs.
 */
public class HumanEval_129_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> datasetCases() {
        return Stream.of(
                Arguments.of(grid(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}), 3, Arrays.asList(1, 2, 1)),
                Arguments.of(grid(new int[][]{{5, 9, 3}, {4, 1, 6}, {7, 8, 2}}), 1, List.of(1)),
                Arguments.of(grid(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}}), 4, Arrays.asList(1, 2, 1, 2)),
                Arguments.of(grid(new int[][]{{2, 7, 4}, {3, 1, 5}, {6, 8, 9}}), 8, Arrays.asList(1, 3, 1, 3, 1, 3, 1, 3))
        );
    }

    static Stream<Arguments> extendedCases() {
        return Stream.of(
                Arguments.of(grid(new int[][]{{1, 4}, {2, 3}}), 1, List.of(1)),
                Arguments.of(grid(new int[][]{{1, 4}, {2, 3}}), 2, Arrays.asList(1, 2)),
                Arguments.of(grid(new int[][]{{1, 4}, {2, 3}}), 5, Arrays.asList(1, 2, 1, 2, 1)),
                Arguments.of(grid(new int[][]{{9, 2, 8}, {4, 1, 3}, {7, 5, 6}}), 6, Arrays.asList(1, 2, 1, 2, 1, 2)),
                Arguments.of(grid(new int[][]{{9, 1, 8}, {4, 2, 3}, {7, 5, 6}}), 4, Arrays.asList(1, 2, 1, 2)),
                Arguments.of(grid(new int[][]{{4, 3}, {1, 2}}), 6, Arrays.asList(1, 2, 1, 2, 1, 2))
        );
    }

    @ParameterizedTest(name = "k={1}, path={2}")
    @MethodSource("datasetCases")
    @DisplayName("Dataset expectations")
    void datasetExpectations(List<List<Integer>> grid, int k, List<Integer> expected) {
        assertEquals(expected, solution.minPath(grid, k));
    }

    @ParameterizedTest(name = "k={1}, path={2}")
    @MethodSource("extendedCases")
    @DisplayName("Extended position and boundary cases")
    void extendedPositionAndBoundaryCases(List<List<Integer>> grid, int k, List<Integer> expected) {
        assertEquals(expected, solution.minPath(grid, k));
    }

    @Test
    @DisplayName("Zero k is pinned to empty list")
    void zeroKReturnsEmptyList() {
        assertEquals(List.of(), solution.minPath(grid(new int[][]{{1, 2}, {3, 4}}), 0));
    }

    @Test
    @DisplayName("Negative k is pinned to empty list")
    void negativeKReturnsEmptyList() {
        assertEquals(List.of(), solution.minPath(grid(new int[][]{{1, 2}, {3, 4}}), -1));
    }

    @Test
    @DisplayName("Null grid is pinned to empty list")
    void nullGridReturnsEmptyList() {
        assertEquals(List.of(), solution.minPath(null, 3));
    }

    @Test
    @DisplayName("Empty grid is pinned to empty list")
    void emptyGridReturnsEmptyList() {
        assertEquals(List.of(), solution.minPath(List.of(), 3));
    }

    private static List<List<Integer>> grid(int[][] values) {
        return Arrays.stream(values)
                .map(row -> Arrays.stream(row).boxed().toList())
                .toList();
    }
}
