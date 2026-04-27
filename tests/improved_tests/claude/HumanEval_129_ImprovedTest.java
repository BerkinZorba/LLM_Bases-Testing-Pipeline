import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Improved JUnit 6 suite for HumanEval_129 (minPath).
 *
 * Improvements over the dataset harness:
 *   - splits 11-assert roulette into named, individually attributed tests;
 *   - groups tests by the position of the cell with value 1 (corner, edge, interior)
 *     to make it clear which structural branch is being exercised;
 *   - adds k=1 as an explicit boundary (result list has exactly one entry, always 1);
 *   - adds k=2 as the minimal both-parity case;
 *   - verifies that 1 at all four corners of a 3x3 grid produces the correct min-neighbor;
 *   - verifies a 2x2 minimum grid (smallest allowed N).
 */
public class HumanEval_129_ImprovedTest {

    private final Solution s = new Solution();

    @SafeVarargs
    private static List<List<Integer>> g(List<Integer>... rows) {
        return Arrays.asList(rows);
    }

    @Nested
    @DisplayName("Dataset cases — one assertion per test")
    class DatasetCases {

        @Test void ds01_3x3_cornerTL_k3()    { assertEquals(Arrays.asList(1,2,1),  s.minPath(g(Arrays.asList(1,2,3),Arrays.asList(4,5,6),Arrays.asList(7,8,9)),3)); }
        @Test void ds02_3x3_interior_k1()    { assertEquals(List.of(1),             s.minPath(g(Arrays.asList(5,9,3),Arrays.asList(4,1,6),Arrays.asList(7,8,2)),1)); }
        @Test void ds03_4x4_cornerTL_k4()    { assertEquals(Arrays.asList(1,2,1,2), s.minPath(g(Arrays.asList(1,2,3,4),Arrays.asList(5,6,7,8),Arrays.asList(9,10,11,12),Arrays.asList(13,14,15,16)),4)); }
        @Test void ds04_4x4_edgeRight_k7()   { assertEquals(Arrays.asList(1,10,1,10,1,10,1), s.minPath(g(Arrays.asList(6,4,13,10),Arrays.asList(5,7,12,1),Arrays.asList(3,16,11,15),Arrays.asList(8,14,9,2)),7)); }
        @Test void ds05_4x4_interior_k5()    { assertEquals(Arrays.asList(1,7,1,7,1), s.minPath(g(Arrays.asList(8,14,9,2),Arrays.asList(6,4,13,15),Arrays.asList(5,7,1,12),Arrays.asList(3,10,11,16)),5)); }
        @Test void ds06_4x4_cornerBR_k9()    { assertEquals(Arrays.asList(1,6,1,6,1,6,1,6,1), s.minPath(g(Arrays.asList(11,8,7,2),Arrays.asList(5,16,14,4),Arrays.asList(9,3,15,6),Arrays.asList(12,13,10,1)),9)); }
        @Test void ds07_4x4_cornerTR_k12()   { assertEquals(Arrays.asList(1,6,1,6,1,6,1,6,1,6,1,6), s.minPath(g(Arrays.asList(12,13,10,1),Arrays.asList(9,3,15,6),Arrays.asList(5,16,14,4),Arrays.asList(11,8,7,2)),12)); }
        @Test void ds08_3x3_midRow_k8_nb3()  { assertEquals(Arrays.asList(1,3,1,3,1,3,1,3), s.minPath(g(Arrays.asList(2,7,4),Arrays.asList(3,1,5),Arrays.asList(6,8,9)),8)); }
        @Test void ds09_3x3_topMid_k8_nb5()  { assertEquals(Arrays.asList(1,5,1,5,1,5,1,5), s.minPath(g(Arrays.asList(6,1,5),Arrays.asList(3,8,9),Arrays.asList(2,7,4)),8)); }
        @Test void ds10_2x2_cornerTL_k10()   { assertEquals(Arrays.asList(1,2,1,2,1,2,1,2,1,2), s.minPath(g(Arrays.asList(1,2),Arrays.asList(3,4)),10)); }
        @Test void ds11_2x2_tiedNeighbors_k10() { assertEquals(Arrays.asList(1,3,1,3,1,3,1,3,1,3), s.minPath(g(Arrays.asList(1,3),Arrays.asList(3,2)),10)); }
    }

    @Nested
    @DisplayName("k boundary values")
    class KBoundaries {

        @Test
        @DisplayName("k=1: result is always [1]")
        void k1_singleStep() {
            // 1 at top-left corner
            assertEquals(List.of(1),
                    s.minPath(g(Arrays.asList(1, 2), Arrays.asList(3, 4)), 1));
        }

        @Test
        @DisplayName("k=2: both parities — [1, minNeighbor]")
        void k2_bothParities() {
            // 1 at top-left, neighbors are 2 (right) and 3 (down); minNeighbor=2
            assertEquals(Arrays.asList(1, 2),
                    s.minPath(g(Arrays.asList(1, 2), Arrays.asList(3, 4)), 2));
        }
    }

    @Nested
    @DisplayName("Position of 1 in grid — covers all 4 corners and interior")
    class PositionOf1 {

        @Test
        @DisplayName("1 at top-left corner (0,0): only right and down neighbors")
        void oneAtTopLeft() {
            // neighbors of (0,0): (0,1)=2, (1,0)=3 → minNeighbor=2
            assertEquals(Arrays.asList(1, 2, 1),
                    s.minPath(g(Arrays.asList(1, 2), Arrays.asList(3, 4)), 3));
        }

        @Test
        @DisplayName("1 at top-right corner (0,n-1): only left and down neighbors")
        void oneAtTopRight() {
            // [[2,1],[3,4]]: neighbors of (0,1)=(0,0)=2 and (1,1)=4 → minNeighbor=2
            assertEquals(Arrays.asList(1, 2, 1),
                    s.minPath(g(Arrays.asList(2, 1), Arrays.asList(3, 4)), 3));
        }

        @Test
        @DisplayName("1 at bottom-left corner (n-1,0): only right and up neighbors")
        void oneAtBottomLeft() {
            // [[2,3],[1,4]]: neighbors of (1,0)=(0,0)=2 and (1,1)=4 → minNeighbor=2
            assertEquals(Arrays.asList(1, 2, 1),
                    s.minPath(g(Arrays.asList(2, 3), Arrays.asList(1, 4)), 3));
        }

        @Test
        @DisplayName("1 at bottom-right corner (n-1,n-1): only left and up neighbors")
        void oneAtBottomRight() {
            // [[2,3],[4,1]]: neighbors of (1,1)=(0,1)=3 and (1,0)=4 → minNeighbor=3
            assertEquals(Arrays.asList(1, 3, 1),
                    s.minPath(g(Arrays.asList(2, 3), Arrays.asList(4, 1)), 3));
        }

        @Test
        @DisplayName("1 at interior of 3x3: four neighbors available")
        void oneAtInterior3x3() {
            // [[5,6,7],[4,1,8],[9,3,2]]: neighbors of (1,1)=5,6,8... no wait
            // (1,1)=1; neighbors: (0,1)=6, (2,1)=3, (1,0)=4, (1,2)=8 → minNeighbor=3
            assertEquals(Arrays.asList(1, 3, 1),
                    s.minPath(g(Arrays.asList(5, 6, 7), Arrays.asList(4, 1, 8), Arrays.asList(9, 3, 2)), 3));
        }
    }
}
