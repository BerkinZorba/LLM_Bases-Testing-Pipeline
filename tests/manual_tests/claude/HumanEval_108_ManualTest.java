import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class HumanEval_108_ManualTest {

    private final Solution s = new Solution();

    // V1 — empty list
    @Test @DisplayName("V1: [] -> 0")
    void v1_empty() { assertEquals(0, s.countNums(List.of())); }

    // V2 — zero(s)
    @Test @DisplayName("V2: [0] -> 0 (digit sum == 0)")
    void v2_zero() { assertEquals(0, s.countNums(List.of(0))); }

    @Test @DisplayName("V2: [0,0,0] -> 0")
    void v2_multipleZeros() { assertEquals(0, s.countNums(Arrays.asList(0, 0, 0))); }

    // V3 — positive single-digit
    @Test @DisplayName("V3: [1] -> 1")
    void v3_one() { assertEquals(1, s.countNums(List.of(1))); }

    @Test @DisplayName("V3: [9] -> 1")
    void v3_nine() { assertEquals(1, s.countNums(List.of(9))); }

    // V4 — positive multi-digit
    @Test @DisplayName("V4: [1,1,2] -> 3 (docstring)")
    void v4_docstring() { assertEquals(3, s.countNums(Arrays.asList(1, 1, 2))); }

    @Test @DisplayName("V4: [11] -> 1 (digit sum 1+1=2)")
    void v4_eleven() { assertEquals(1, s.countNums(List.of(11))); }

    // V5 — negative with sum < 0
    @Test @DisplayName("V5: [-1] -> 0 (sum = -1)")
    void v5_negOne() { assertEquals(0, s.countNums(List.of(-1))); }

    @Test @DisplayName("V5: [-9] -> 0 (sum = -9)")
    void v5_negNine() { assertEquals(0, s.countNums(List.of(-9))); }

    // V6 — negative with sum == 0
    @Test @DisplayName("V6: [-11] -> 0 (sum = -1+1 = 0)")
    void v6_negEleven() { assertEquals(0, s.countNums(List.of(-11))); }

    // V7 — negative with sum > 0
    @Test @DisplayName("V7: [-123] -> 1 (sum = -1+2+3 = 4)")
    void v7_neg123() { assertEquals(1, s.countNums(List.of(-123))); }

    @Test @DisplayName("V7: [-19] -> 1 (sum = -1+9 = 8)")
    void v7_neg19() { assertEquals(1, s.countNums(List.of(-19))); }

    // V8 — docstring mixed example
    @Test @DisplayName("V8: [-1, 11, -11] -> 1 (docstring)")
    void v8_docstringMixed() { assertEquals(1, s.countNums(Arrays.asList(-1, 11, -11))); }

    // I1 — Integer.MIN_VALUE (boundary)
    @Test @DisplayName("I1: [Integer.MIN_VALUE] -> 1 (safe long cast; sum = 43 > 0)")
    void i1_minValue() { assertEquals(1, s.countNums(List.of(Integer.MIN_VALUE))); }

    // Mixed
    @Test @DisplayName("Mixed: [0,-1,1] -> 1")
    void mixed_zeroNegPos() { assertEquals(1, s.countNums(Arrays.asList(0, -1, 1))); }

    @Test @DisplayName("Mixed: [-123,-11,0,5] -> 2")
    void mixed_four() { assertEquals(2, s.countNums(Arrays.asList(-123, -11, 0, 5))); }
}
