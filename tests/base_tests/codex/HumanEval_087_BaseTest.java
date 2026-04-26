import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class HumanEval_087_BaseTest {

    private final Solution s = new Solution();

    @Test
    void docstringExample_3rows_x1() {
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

    @Test
    void uniform6rows_x2_onePerRow() {
        List<List<Integer>> grid = Collections.nCopies(6, Arrays.asList(1, 2, 3, 4, 5, 6));
        assertEquals(
            Arrays.asList(
                Arrays.asList(0, 1), Arrays.asList(1, 1), Arrays.asList(2, 1),
                Arrays.asList(3, 1), Arrays.asList(4, 1), Arrays.asList(5, 1)
            ),
            s.getRow(grid, 2)
        );
    }

    @Test
    void diagonal7rows_x1_twoPerRow() {
        assertEquals(
            Arrays.asList(
                Arrays.asList(0, 0),
                Arrays.asList(1, 0),
                Arrays.asList(2, 1), Arrays.asList(2, 0),
                Arrays.asList(3, 2), Arrays.asList(3, 0),
                Arrays.asList(4, 3), Arrays.asList(4, 0),
                Arrays.asList(5, 4), Arrays.asList(5, 0),
                Arrays.asList(6, 5), Arrays.asList(6, 0)
            ),
            s.getRow(Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(1, 1, 3, 4, 5, 6),
                Arrays.asList(1, 2, 1, 4, 5, 6),
                Arrays.asList(1, 2, 3, 1, 5, 6),
                Arrays.asList(1, 2, 3, 4, 1, 6),
                Arrays.asList(1, 2, 3, 4, 5, 1)
            ), 1)
        );
    }

    @Test
    void emptyList_returnsEmpty() {
        assertEquals(List.of(), s.getRow(List.of(), 1));
    }

    @Test
    void singleCellNoMatch_returnsEmpty() {
        assertEquals(List.of(), s.getRow(List.of(List.of(1)), 2));
    }

    @Test
    void jaggedWithEmptyRow_xInLastRow() {
        assertEquals(
            List.of(Arrays.asList(2, 2)),
            s.getRow(Arrays.asList(List.of(), List.of(1), Arrays.asList(1, 2, 3)), 3)
        );
    }
}
