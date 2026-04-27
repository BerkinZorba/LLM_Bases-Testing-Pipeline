import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_129_BaseTest {

    private final Solution s = new Solution();

    @Test
    void threeByThreePromptExampleLengthThree() {
        assertEquals(Arrays.asList(1, 2, 1),
                s.minPath(Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(4, 5, 6),
                        Arrays.asList(7, 8, 9)), 3));
    }

    @Test
    void threeByThreePromptExampleLengthOne() {
        assertEquals(List.of(1),
                s.minPath(Arrays.asList(
                        Arrays.asList(5, 9, 3),
                        Arrays.asList(4, 1, 6),
                        Arrays.asList(7, 8, 2)), 1));
    }

    @Test
    void fourByFourCornerOne() {
        assertEquals(Arrays.asList(1, 2, 1, 2),
                s.minPath(Arrays.asList(
                        Arrays.asList(1, 2, 3, 4),
                        Arrays.asList(5, 6, 7, 8),
                        Arrays.asList(9, 10, 11, 12),
                        Arrays.asList(13, 14, 15, 16)), 4));
    }

    @Test
    void fourByFourEdgeNeighborTen() {
        assertEquals(Arrays.asList(1, 10, 1, 10, 1, 10, 1),
                s.minPath(Arrays.asList(
                        Arrays.asList(6, 4, 13, 10),
                        Arrays.asList(5, 7, 12, 1),
                        Arrays.asList(3, 16, 11, 15),
                        Arrays.asList(8, 14, 9, 2)), 7));
    }

    @Test
    void fourByFourInteriorNeighborSeven() {
        assertEquals(Arrays.asList(1, 7, 1, 7, 1),
                s.minPath(Arrays.asList(
                        Arrays.asList(8, 14, 9, 2),
                        Arrays.asList(6, 4, 13, 15),
                        Arrays.asList(5, 7, 1, 12),
                        Arrays.asList(3, 10, 11, 16)), 5));
    }

    @Test
    void fourByFourCornerNeighborSixLengthNine() {
        assertEquals(Arrays.asList(1, 6, 1, 6, 1, 6, 1, 6, 1),
                s.minPath(Arrays.asList(
                        Arrays.asList(11, 8, 7, 2),
                        Arrays.asList(5, 16, 14, 4),
                        Arrays.asList(9, 3, 15, 6),
                        Arrays.asList(12, 13, 10, 1)), 9));
    }

    @Test
    void fourByFourEdgeNeighborSixLengthTwelve() {
        assertEquals(Arrays.asList(1, 6, 1, 6, 1, 6, 1, 6, 1, 6, 1, 6),
                s.minPath(Arrays.asList(
                        Arrays.asList(12, 13, 10, 1),
                        Arrays.asList(9, 3, 15, 6),
                        Arrays.asList(5, 16, 14, 4),
                        Arrays.asList(11, 8, 7, 2)), 12));
    }

    @Test
    void threeByThreeInteriorNeighborThree() {
        assertEquals(Arrays.asList(1, 3, 1, 3, 1, 3, 1, 3),
                s.minPath(Arrays.asList(
                        Arrays.asList(2, 7, 4),
                        Arrays.asList(3, 1, 5),
                        Arrays.asList(6, 8, 9)), 8));
    }

    @Test
    void threeByThreeEdgeNeighborFive() {
        assertEquals(Arrays.asList(1, 5, 1, 5, 1, 5, 1, 5),
                s.minPath(Arrays.asList(
                        Arrays.asList(6, 1, 5),
                        Arrays.asList(3, 8, 9),
                        Arrays.asList(2, 7, 4)), 8));
    }

    @Test
    void twoByTwoLengthTen() {
        assertEquals(Arrays.asList(1, 2, 1, 2, 1, 2, 1, 2, 1, 2),
                s.minPath(Arrays.asList(
                        Arrays.asList(1, 2),
                        Arrays.asList(3, 4)), 10));
    }
}
