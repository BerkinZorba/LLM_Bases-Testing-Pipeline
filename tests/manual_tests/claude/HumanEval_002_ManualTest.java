import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HumanEval_002_ManualTest {

    private final Solution s = new Solution();

    // V1 — exact integers, fraction == 0
    @Test @DisplayName("V1: truncateNumber(1.0) == 0.0")
    void v1_one() { assertEquals(0.0, s.truncateNumber(1.0), 1e-9); }

    @Test @DisplayName("V1: truncateNumber(100.0) == 0.0")
    void v1_hundred() { assertEquals(0.0, s.truncateNumber(100.0), 1e-9); }

    // V2 — small fraction
    @Test @DisplayName("V2: truncateNumber(3.5) == 0.5 (docstring)")
    void v2_docstring() { assertEquals(0.5, s.truncateNumber(3.5), 1e-9); }

    @Test @DisplayName("V2: truncateNumber(1.33) == 0.33")
    void v2_oneThreeThree() { assertEquals(0.33, s.truncateNumber(1.33), 1e-9); }

    @Test @DisplayName("V2: truncateNumber(123.456) == 0.456")
    void v2_large() { assertEquals(0.456, s.truncateNumber(123.456), 1e-9); }

    // V3 — large fraction
    @Test @DisplayName("V3: truncateNumber(0.5) == 0.5")
    void v3_zeroPointFive() { assertEquals(0.5, s.truncateNumber(0.5), 1e-9); }

    @Test @DisplayName("V3: truncateNumber(7.9) == 0.9")
    void v3_sevenPointNine() { assertEquals(0.9, s.truncateNumber(7.9), 1e-9); }

    // V4 — number < 1 (integer part 0)
    @Test @DisplayName("V4: truncateNumber(0.5) == 0.5 (input < 1)")
    void v4_lessThanOne() { assertEquals(0.5, s.truncateNumber(0.5), 1e-9); }

    // Invariants
    @Test @DisplayName("Invariant: result in [0, 1)")
    void invariant_resultRange() {
        double[] inputs = {1.0, 3.5, 0.5, 99.99, 0.001};
        for (double v : inputs) {
            double r = s.truncateNumber(v);
            assertTrue(r >= 0.0 && r < 1.0, "Expected [0,1) for input " + v + " but got " + r);
        }
    }

    @Test @DisplayName("Invariant: floor(n) + result == n")
    void invariant_addBack() {
        double[] inputs = {3.5, 1.33, 123.456, 7.9};
        for (double v : inputs)
            assertEquals(v, Math.floor(v) + s.truncateNumber(v), 1e-9);
    }
}
