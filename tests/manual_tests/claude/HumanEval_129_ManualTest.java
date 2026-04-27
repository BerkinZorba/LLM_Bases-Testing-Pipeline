import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Manual black-box JUnit 6 suite for HumanEval_129.
 * Cases are derived from tests/manual_tests/claude/HumanEval_129_blackbox.md.
 */
public class HumanEval_129_ManualTest {

    private final Solution s = new Solution();

    @SafeVarargs
    private static List<List<Integer>> g(List<Integer>... rows) {
        return Arrays.asList(rows);
    }

    @Nested
    @DisplayName("V1–V4: k boundary values")
    class KBoundaries {

        @Test
        @DisplayName("V1: k=1 always returns [1]")
        void v1_k1_returnsOne() {
            assertEquals(List.of(1),
                    s.minPath(g(Arrays.asList(1, 2), Arrays.asList(3, 4)), 1));
        }

        @Test
        @DisplayName("V2: k=2 returns [1, minNeighbour]")
        void v2_k2_bothParities() {
            // 1 at (0,0), neighbours 2 and 3 → min=2
            assertEquals(Arrays.asList(1, 2),
                    s.minPath(g(Arrays.asList(1, 2), Arrays.asList(3, 4)), 2));
        }

        @Test
        @DisplayName("V3: k odd (3) → last element is 1")
        void v3_kOdd_endsWithOne() {
            assertEquals(Arrays.asList(1, 2, 1),
                    s.minPath(g(Arrays.asList(1, 2), Arrays.asList(3, 4)), 3));
        }

        @Test
        @DisplayName("V4: k even (4) → last element is minNeighbour")
        void v4_kEven_endsWithMinNeighbour() {
            assertEquals(Arrays.asList(1, 2, 1, 2),
                    s.minPath(g(Arrays.asList(1, 2), Arrays.asList(3, 4)), 4));
        }
    }

    @Nested
    @DisplayName("V5–V10: position of 1 in the grid")
    class PositionOf1 {

        static Stream<Arguments> positionCases() {
            return Stream.of(
                    Arguments.of("V5 top-left corner (0,0) — 2 neighbours",
                            g(Arrays.asList(1,2), Arrays.asList(3,4)), 3,
                            Arrays.asList(1,2,1)),
                    Arguments.of("V6 top-right corner (0,n-1) — 2 neighbours",
                            g(Arrays.asList(2,1), Arrays.asList(4,3)), 3,
                            Arrays.asList(1,2,1)),
                    Arguments.of("V7 bottom-left corner (n-1,0) — 2 neighbours",
                            g(Arrays.asList(2,4), Arrays.asList(1,3)), 3,
                            Arrays.asList(1,2,1)),
                    Arguments.of("V8 bottom-right corner (n-1,n-1) — 2 neighbours",
                            g(Arrays.asList(3,4), Arrays.asList(2,1)), 3,
                            Arrays.asList(1,2,1)),
                    Arguments.of("V9 top-edge non-corner (0,1) in 3x3 — 3 neighbours",
                            g(Arrays.asList(6,1,5), Arrays.asList(3,8,9), Arrays.asList(2,7,4)), 3,
                            Arrays.asList(1,5,1)),
                    Arguments.of("V10 interior (1,1) in 3x3 — 4 neighbours",
                            g(Arrays.asList(5,6,7), Arrays.asList(4,1,8), Arrays.asList(9,3,2)), 3,
                            Arrays.asList(1,3,1))
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("positionCases")
        void positionVariants(String label, List<List<Integer>> grid, int k,
                              List<Integer> expected) {
            assertEquals(expected, s.minPath(grid, k), label);
        }
    }

    @Nested
    @DisplayName("V11–V14: grid-size and tie-breaking")
    class GridSizeAndTies {

        @Test
        @DisplayName("V11: two neighbours of 1 tied — picks the true minimum (they are equal)")
        void v11_tiedNeighbours() {
            // [[1,3],[3,2]]: neighbours of (0,0) are (0,1)=3 and (1,0)=3 → minNeighbour=3
            assertEquals(Arrays.asList(1, 3, 1, 3, 1, 3, 1, 3, 1, 3),
                    s.minPath(g(Arrays.asList(1, 3), Arrays.asList(3, 2)), 10));
        }

        @Test
        @DisplayName("V12: minimum 2x2 grid, k=10")
        void v12_minGridSize() {
            assertEquals(Arrays.asList(1, 2, 1, 2, 1, 2, 1, 2, 1, 2),
                    s.minPath(g(Arrays.asList(1, 2), Arrays.asList(3, 4)), 10));
        }

        @Test
        @DisplayName("V13: 3x3 grid, 1 at centre, k=8")
        void v13_3x3Grid() {
            assertEquals(Arrays.asList(1, 3, 1, 3, 1, 3, 1, 3),
                    s.minPath(g(Arrays.asList(2, 7, 4), Arrays.asList(3, 1, 5), Arrays.asList(6, 8, 9)), 8));
        }

        @Test
        @DisplayName("V14: 4x4 grid, 1 at interior, k=5")
        void v14_4x4Grid() {
            assertEquals(Arrays.asList(1, 7, 1, 7, 1),
                    s.minPath(g(
                            Arrays.asList(8, 14, 9, 2),
                            Arrays.asList(6, 4, 13, 15),
                            Arrays.asList(5, 7, 1, 12),
                            Arrays.asList(3, 10, 11, 16)), 5));
        }
    }

    @Nested
    @DisplayName("I1–I2: invalid / undefined-by-spec inputs (pinned)")
    class InvalidInputs {

        @Test
        @DisplayName("I1: null grid throws NullPointerException")
        void i1_nullGrid_throwsNPE() {
            assertThrows(NullPointerException.class, () -> s.minPath(null, 3));
        }

        @Test
        @DisplayName("I2: k=0 returns empty list (loop never executes)")
        void i2_k0_returnsEmpty() {
            assertEquals(Collections.emptyList(),
                    s.minPath(g(Arrays.asList(1, 2), Arrays.asList(3, 4)), 0));
        }
    }
}
