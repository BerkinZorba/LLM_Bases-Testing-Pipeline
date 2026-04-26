import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class HumanEval_087_ManualTest {

    private final Solution s = new Solution();

    // V1: empty outer list
    @Test
    void v1_emptyList() {
        assertEquals(List.of(), s.getRow(List.of(), 1));
    }

    // V2: all rows empty
    @Test
    void v2_allRowsEmpty() {
        assertEquals(List.of(),
            s.getRow(Arrays.asList(List.of(), List.of()), 1));
    }

    // V3: x not present
    @Test
    void v3_xAbsent() {
        assertEquals(List.of(),
            s.getRow(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4)), 9));
    }

    // V4: single cell match
    @Test
    void v4_singleCellMatch() {
        assertEquals(List.of(Arrays.asList(0, 0)),
            s.getRow(List.of(List.of(7)), 7));
    }

    // V5: one match per row across all rows
    @Test
    void v5_oneMatchPerRow() {
        assertEquals(
            Arrays.asList(Arrays.asList(0, 0), Arrays.asList(1, 0)),
            s.getRow(Arrays.asList(Arrays.asList(2, 3), Arrays.asList(2, 3)), 2)
        );
    }

    // V6: multiple matches in one row — descending column order
    @Test
    void v6_multipleMatchesSameRow() {
        // [1,2,1]: x=1 at col 0 and col 2 → [[0,2],[0,0]]
        assertEquals(
            Arrays.asList(Arrays.asList(0, 2), Arrays.asList(0, 0)),
            s.getRow(List.of(Arrays.asList(1, 2, 1)), 1)
        );
    }

    // V7: jagged rows, x only in longer row
    @Test
    void v7_jaggedXInLongerRow() {
        assertEquals(
            List.of(Arrays.asList(2, 2)),
            s.getRow(Arrays.asList(List.of(), List.of(1), Arrays.asList(1, 2, 3)), 3)
        );
    }

    // V8: multi-row multi-match (docstring example)
    @Test
    void v8_docstringExample() {
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

    // V9: x at first and last column
    @Test
    void v9_xAtBothEnds() {
        assertEquals(
            Arrays.asList(Arrays.asList(0, 2), Arrays.asList(0, 0)),
            s.getRow(List.of(Arrays.asList(5, 2, 5)), 5)
        );
    }

    // Boundary: single cell, no match
    @Test
    void boundary_singleCellNoMatch() {
        assertEquals(List.of(), s.getRow(List.of(List.of(1)), 2));
    }

    // Boundary: row index preserved correctly for middle row
    @Test
    void boundary_middleRowMatch() {
        assertEquals(
            List.of(Arrays.asList(1, 1)),
            s.getRow(Arrays.asList(
                Arrays.asList(1, 2), Arrays.asList(3, 9), Arrays.asList(5, 6)
            ), 9)
        );
    }
}
