import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

/**
 * Improved suite for HumanEval_087 (getRow) — Claude side.
 *
 * Test-smell improvements:
 *   - Assertion roulette: each case is a separate @Test.
 *   - Magic numbers: display names describe row/column semantics.
 *   - Eager test: cases partitioned by concern.
 *
 * Branch-coverage targets in Solution.getRow:
 *   - Outer loop guard: empty list (not entered) vs non-empty.
 *   - Inner loop guard: empty row (not entered) vs non-empty row.
 *   - if (r.get(col) == x): match vs no match.
 */
public class HumanEval_087_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Empty inputs")
    class EmptyInputs {
        @Test @DisplayName("Empty outer list -> []")
        void emptyList() {
            assertEquals(List.of(), s.getRow(List.of(), 1));
        }

        @Test @DisplayName("List of one empty row -> []")
        void singleEmptyRow() {
            assertEquals(List.of(), s.getRow(List.of(List.of()), 1));
        }

        @Test @DisplayName("All rows empty -> []")
        void allEmptyRows() {
            assertEquals(List.of(), s.getRow(Arrays.asList(List.of(), List.of(), List.of()), 5));
        }
    }

    @Nested
    @DisplayName("No matches")
    class NoMatches {
        @Test @DisplayName("x not present anywhere -> []")
        void xAbsent() {
            assertEquals(List.of(), s.getRow(
                Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4)), 9));
        }

        @Test @DisplayName("Single cell, value differs -> []")
        void singleCellNoMatch() {
            assertEquals(List.of(), s.getRow(List.of(List.of(1)), 2));
        }
    }

    @Nested
    @DisplayName("Single match")
    class SingleMatch {
        @Test @DisplayName("Match in first row, only column -> [[0,0]]")
        void singleMatchFirstRow() {
            assertEquals(List.of(Arrays.asList(0, 0)),
                s.getRow(List.of(List.of(7)), 7));
        }

        @Test @DisplayName("Match in last row only -> correct row index")
        void matchInLastRow() {
            assertEquals(List.of(Arrays.asList(2, 2)),
                s.getRow(Arrays.asList(List.of(), List.of(1), Arrays.asList(1, 2, 3)), 3));
        }
    }

    @Nested
    @DisplayName("Multiple matches — descending column order within row")
    class MultipleMatchesInRow {
        @Test @DisplayName("Two matches in one row -> higher col first")
        void twoMatchesSameRow() {
            assertEquals(
                Arrays.asList(Arrays.asList(0, 3), Arrays.asList(0, 1)),
                s.getRow(List.of(Arrays.asList(2, 1, 2, 1)), 1)
            );
        }

        @Test @DisplayName("Match at first and last column -> last col first")
        void matchAtBothEnds() {
            assertEquals(
                Arrays.asList(Arrays.asList(0, 4), Arrays.asList(0, 0)),
                s.getRow(List.of(Arrays.asList(5, 2, 3, 2, 5)), 5)
            );
        }

        @Test @DisplayName("Three matches in one row -> descending order")
        void threeMatchesSameRow() {
            assertEquals(
                Arrays.asList(Arrays.asList(0, 4), Arrays.asList(0, 2), Arrays.asList(0, 0)),
                s.getRow(List.of(Arrays.asList(1, 0, 1, 0, 1)), 1)
            );
        }
    }

    @Nested
    @DisplayName("Multi-row cases")
    class MultiRow {
        @Test @DisplayName("One match per row -> ascending row order")
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

        @Test @DisplayName("Jagged rows — different lengths, x in some rows only")
        void jaggedRows() {
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
}
