import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

/**
 * Improved suite for HumanEval_087 (getRow) — Codex side.
 *
 * Test-smell improvements:
 *   - Assertion roulette: each case isolated.
 *   - Magic numbers: display names describe semantics.
 *   - Eager test: partitioned by concern.
 *
 * Branch-coverage targets in Solution.getRow:
 *   - Outer loop guard: empty list vs non-empty.
 *   - Inner loop guard (col scan): empty row vs non-empty.
 *   - if (row.get(j) == x): match vs no match.
 *   - For-each loop over cols: empty cols (no matches) vs non-empty.
 */
public class HumanEval_087_ImprovedTest {

    private final Solution s = new Solution();

    @Test @DisplayName("Empty list -> []")
    void emptyList() {
        assertEquals(List.of(), s.getRow(List.of(), 1));
    }

    @Test @DisplayName("Single empty row -> []")
    void singleEmptyRow() {
        assertEquals(List.of(), s.getRow(List.of(List.of()), 1));
    }

    @Test @DisplayName("x absent in all rows -> []")
    void xAbsent() {
        assertEquals(List.of(), s.getRow(
            Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4)), 9));
    }

    @Test @DisplayName("Single cell match -> [[0,0]]")
    void singleCellMatch() {
        assertEquals(List.of(Arrays.asList(0, 0)),
            s.getRow(List.of(List.of(7)), 7));
    }

    @Test @DisplayName("Single cell no match -> []")
    void singleCellNoMatch() {
        assertEquals(List.of(), s.getRow(List.of(List.of(1)), 2));
    }

    @Test @DisplayName("Two matches in row -> higher col first (reverseOrder sort)")
    void twoMatchesSameRow() {
        assertEquals(
            Arrays.asList(Arrays.asList(0, 3), Arrays.asList(0, 1)),
            s.getRow(List.of(Arrays.asList(2, 1, 2, 1)), 1)
        );
    }

    @Test @DisplayName("Match at both ends of row -> last index first")
    void matchAtBothEnds() {
        assertEquals(
            Arrays.asList(Arrays.asList(0, 4), Arrays.asList(0, 0)),
            s.getRow(List.of(Arrays.asList(5, 2, 3, 2, 5)), 5)
        );
    }

    @Test @DisplayName("One match per row — rows in ascending order")
    void oneMatchPerRow() {
        List<List<Integer>> grid = Collections.nCopies(6, Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(
            Arrays.asList(
                Arrays.asList(0, 1), Arrays.asList(1, 1), Arrays.asList(2, 1),
                Arrays.asList(3, 1), Arrays.asList(4, 1), Arrays.asList(5, 1)
            ),
            s.getRow(grid, 2)
        );
    }

    @Test @DisplayName("Docstring 3-row example")
    void docstring3rows() {
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

    @Test @DisplayName("Jagged list with empty row — only non-empty rows scanned")
    void jaggedWithEmptyRow() {
        assertEquals(
            List.of(Arrays.asList(2, 2)),
            s.getRow(Arrays.asList(List.of(), List.of(1), Arrays.asList(1, 2, 3)), 3)
        );
    }

    @Test @DisplayName("x appears in some rows but not others")
    void xInSomeRows() {
        assertEquals(
            Arrays.asList(Arrays.asList(1, 0), Arrays.asList(2, 2), Arrays.asList(2, 0)),
            s.getRow(Arrays.asList(
                List.of(2),
                List.of(1),
                Arrays.asList(1, 0, 1)
            ), 1)
        );
    }
}
