import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class HumanEval_087_ManualTest {

    private final Solution s = new Solution();

    // V1: empty outer list
    @Test
    void v1_emptyList() {
        assertEquals(List.of(), s.getRow(List.of(), 5));
    }

    // V2: all rows empty — cols never filled
    @Test
    void v2_allRowsEmpty() {
        assertEquals(List.of(),
            s.getRow(Arrays.asList(List.of(), List.of()), 1));
    }

    // V3: x not present — cols stays empty each row
    @Test
    void v3_xAbsent() {
        assertEquals(List.of(),
            s.getRow(List.of(Arrays.asList(2, 3)), 7));
    }

    // V4: single cell match — cols = [0], sorted = [0]
    @Test
    void v4_singleCellMatch() {
        assertEquals(List.of(Arrays.asList(0, 0)),
            s.getRow(List.of(List.of(9)), 9));
    }

    // V5: one match per row, multiple rows
    @Test
    void v5_oneMatchPerRow() {
        List<List<Integer>> grid = Collections.nCopies(6, Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(
            Arrays.asList(
                Arrays.asList(0, 1), Arrays.asList(1, 1), Arrays.asList(2, 1),
                Arrays.asList(3, 1), Arrays.asList(4, 1), Arrays.asList(5, 1)
            ),
            s.getRow(grid, 2)
        );
    }

    // V6: two matches in row — reverseOrder sort applies
    @Test
    void v6_twoMatchesSameRow() {
        // [3,1,3]: x=3 at cols 0 and 2 → after sort: [2,0]
        assertEquals(
            Arrays.asList(Arrays.asList(0, 2), Arrays.asList(0, 0)),
            s.getRow(List.of(Arrays.asList(3, 1, 3)), 3)
        );
    }

    // V7: mix of rows with and without x
    @Test
    void v7_mixedRows() {
        assertEquals(
            Arrays.asList(
                Arrays.asList(0, 0),
                Arrays.asList(2, 2), Arrays.asList(2, 0)
            ),
            s.getRow(Arrays.asList(
                List.of(1),
                List.of(2),
                Arrays.asList(1, 2, 1)
            ), 1)
        );
    }

    // V8: jagged rows
    @Test
    void v8_jaggedRows() {
        assertEquals(
            List.of(Arrays.asList(2, 2)),
            s.getRow(Arrays.asList(List.of(), List.of(1), Arrays.asList(1, 2, 3)), 3)
        );
    }

    // Boundary: single-element row with no match → cols empty
    @Test
    void boundary_singleElementNoMatch() {
        assertEquals(List.of(), s.getRow(List.of(List.of(1)), 2));
    }

    // Boundary: docstring 3-row example
    @Test
    void boundary_docstringExample() {
        assertEquals(
            Arrays.asList(
                Arrays.asList(0, 0),
                Arrays.asList(1, 4), Arrays.asList(1, 0),
                Arrays.asList(2, 5), Arrays.asList(2, 0)
            ),
            s.getRow(Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(1, 2, 3, 4, 1, 6),
                Arrays.asList(1, 2, 3, 4, 5, 1)
            ), 1)
        );
    }
}
