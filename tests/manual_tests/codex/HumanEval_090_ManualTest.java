import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Manual black-box tests for HumanEval_090 (codex / GPT).
 * Cases derived from HumanEval_090_blackbox.md.
 */
public class HumanEval_090_ManualTest {

    private final Solution s = new Solution();

    @Test @DisplayName("V_E1: empty list -> Optional.empty")
    void ve1_emptyList() {
        assertEquals(Optional.empty(), s.nextSmallest(List.of()));
    }

    @Test @DisplayName("V_E2: single element -> Optional.empty")
    void ve2_singleElement() {
        assertEquals(Optional.empty(), s.nextSmallest(List.of(5)));
    }

    @Test @DisplayName("V_E3: all same -> Optional.empty")
    void ve3_allSame() {
        assertEquals(Optional.empty(), s.nextSmallest(Arrays.asList(3, 3, 3)));
    }

    @Test @DisplayName("V_E4: two identical -> Optional.empty")
    void ve4_twoIdentical() {
        assertEquals(Optional.empty(), s.nextSmallest(Arrays.asList(7, 7)));
    }

    @Test @DisplayName("V_R1: [1,2] ascending pair -> Optional[2]")
    void vr1_ascendingPair() {
        assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(1, 2)));
    }

    @Test @DisplayName("V_R2: [2,1] descending pair -> Optional[2]")
    void vr2_descendingPair() {
        assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(2, 1)));
    }

    @Test @DisplayName("V_R3: ascending list -> Optional[2]")
    void vr3_ascending() {
        assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test @DisplayName("V_R4: unsorted list -> Optional[2]")
    void vr4_unsorted() {
        assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(5, 1, 4, 3, 2)));
    }

    @Test @DisplayName("V_R5: descending list -> Optional[2]")
    void vr5_descending() {
        assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(5, 4, 3, 2, 1)));
    }

    @Test @DisplayName("V_R6: min repeated, one lower -> Optional[1]")
    void vr6_minRepeated() {
        assertEquals(Optional.of(1), s.nextSmallest(Arrays.asList(1, 1, 1, 1, 0)));
    }

    @Test @DisplayName("V_R7: negative numbers -> Optional[-1]")
    void vr7_negatives() {
        assertEquals(Optional.of(-1), s.nextSmallest(Arrays.asList(-2, -1, 0, 1)));
    }

    @Test @DisplayName("Boundary: exactly two distinct after dedup [1,1,2] -> Optional[2]")
    void boundary_twoPlusDedup() {
        assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(1, 1, 2)));
    }
}
