import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class HumanEval_090_ManualTest {

    private final Solution s = new Solution();

    // V_E1 — empty list
    @Test @DisplayName("V_E1: [] -> Optional.empty")
    void ve1_empty() { assertEquals(Optional.empty(), s.nextSmallest(List.of())); }

    // V_E2 — single element
    @Test @DisplayName("V_E2: [5] -> Optional.empty")
    void ve2_single() { assertEquals(Optional.empty(), s.nextSmallest(List.of(5))); }

    // V_E3 — all identical (two)
    @Test @DisplayName("V_E3: [1,1] -> Optional.empty")
    void ve3_allSameTwo() { assertEquals(Optional.empty(), s.nextSmallest(Arrays.asList(1, 1))); }

    // V_E4 — all identical (many)
    @Test @DisplayName("V_E4: [7,7,7,7] -> Optional.empty")
    void ve4_allSameMany() { assertEquals(Optional.empty(), s.nextSmallest(Arrays.asList(7, 7, 7, 7))); }

    // V_R1 — two distinct ascending
    @Test @DisplayName("V_R1: [1,2] -> Optional[2]")
    void vr1_ascending() { assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(1, 2))); }

    // V_R2 — two distinct descending
    @Test @DisplayName("V_R2: [3,1] -> Optional[3]")
    void vr2_descending() { assertEquals(Optional.of(3), s.nextSmallest(Arrays.asList(3, 1))); }

    // V_R3 — many distinct sorted
    @Test @DisplayName("V_R3: [1,2,3,4,5] -> Optional[2] (docstring)")
    void vr3_sorted() { assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(1, 2, 3, 4, 5))); }

    // V_R4 — many distinct unsorted
    @Test @DisplayName("V_R4: [5,1,4,3,2] -> Optional[2] (docstring)")
    void vr4_unsorted() { assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(5, 1, 4, 3, 2))); }

    // V_R5 — duplicates of smallest
    @Test @DisplayName("V_R5: [1,1,1,1,0] -> Optional[1]")
    void vr5_duplicates() { assertEquals(Optional.of(1), s.nextSmallest(Arrays.asList(1, 1, 1, 1, 0))); }

    // V_R6 — all negative
    @Test @DisplayName("V_R6: [-5,-3,-1] -> Optional[-3]")
    void vr6_allNegative() { assertEquals(Optional.of(-3), s.nextSmallest(Arrays.asList(-5, -3, -1))); }

    // V_R7 — mixed negative and positive
    @Test @DisplayName("V_R7: [-1,0,1] -> Optional[0]")
    void vr7_mixed() { assertEquals(Optional.of(0), s.nextSmallest(Arrays.asList(-1, 0, 1))); }

    // Boundary: exactly two distinct after dedup
    @Test @DisplayName("Boundary: [2,2,3] -> Optional[3]")
    void boundary_twoDistinctAfterDedup() {
        assertEquals(Optional.of(3), s.nextSmallest(Arrays.asList(2, 2, 3)));
    }
}
