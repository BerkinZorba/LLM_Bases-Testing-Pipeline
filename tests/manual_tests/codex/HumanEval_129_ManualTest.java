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
public class HumanEval_129_ManualTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> validClasses() {
        return Stream.of(
                Arguments.of("V1", grid(new int[][]{{5, 9, 3}, {4, 1, 6}, {7, 8, 2}}), 1, List.of(1)),
                Arguments.of("V2", grid(new int[][]{{1, 2}, {3, 4}}), 5, Arrays.asList(1, 2, 1, 2, 1)),
                Arguments.of("V3", grid(new int[][]{{4, 3}, {1, 2}}), 6, Arrays.asList(1, 2, 1, 2, 1, 2)),
                Arguments.of("V4", grid(new int[][]{{9, 1, 8}, {4, 2, 3}, {7, 5, 6}}), 4, Arrays.asList(1, 2, 1, 2)),
                Arguments.of("V5", grid(new int[][]{{9, 2, 8}, {4, 1, 3}, {7, 5, 6}}), 6, Arrays.asList(1, 2, 1, 2, 1, 2)),
                Arguments.of("V6", grid(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}), 3, Arrays.asList(1, 2, 1)),
                Arguments.of("V7", grid(new int[][]{{12, 13, 10, 1}, {9, 3, 15, 6}, {5, 16, 14, 4}, {11, 8, 7, 2}}), 12,
                        Arrays.asList(1, 6, 1, 6, 1, 6, 1, 6, 1, 6, 1, 6))
        );
    }

    @ParameterizedTest(name = "{0}: k={2}, path={3}")
    @MethodSource("validClasses")
    @DisplayName("Valid equivalence classes")
    void validEquivalenceClasses(String id, List<List<Integer>> grid, int k, List<Integer> expected) {
        assertEquals(expected, solution.minPath(grid, k));
    }

    @Test
    @DisplayName("Boundary: first move chooses smallest neighbor")
    void firstMoveChoosesSmallestNeighbor() {
        assertEquals(Arrays.asList(1, 2), solution.minPath(grid(new int[][]{{1, 4}, {2, 3}}), 2));
    }

    @Test
    @DisplayName("Boundary: zero k")
    void zeroKReturnsEmptyList() {
        assertEquals(List.of(), solution.minPath(grid(new int[][]{{1, 2}, {3, 4}}), 0));
    }

    @Test
    @DisplayName("Boundary: negative k")
    void negativeKReturnsEmptyList() {
        assertEquals(List.of(), solution.minPath(grid(new int[][]{{1, 2}, {3, 4}}), -1));
    }

    @Test
    @DisplayName("Null grid returns empty list")
    void nullGridReturnsEmptyList() {
        assertEquals(List.of(), solution.minPath(null, 4));
    }

    @Test
    @DisplayName("Empty grid returns empty list")
    void emptyGridReturnsEmptyList() {
        assertEquals(List.of(), solution.minPath(List.of(), 4));
    }

    private static List<List<Integer>> grid(int[][] values) {
        return Arrays.stream(values)
                .map(row -> Arrays.stream(row).boxed().toList())
                .toList();
    }
}
