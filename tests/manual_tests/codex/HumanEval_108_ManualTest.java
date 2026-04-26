import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;

public class HumanEval_108_ManualTest {

    private final Solution sol = new Solution();

    // V1: empty list — outer loop not entered
    @Test
    void v1_emptyList() {
        assertEquals(0, sol.countNums(Arrays.asList()));
    }

    // V2: all zeros — digit sum == 0 for each
    @Test
    void v2_allZeros() {
        assertEquals(0, sol.countNums(Arrays.asList(0, 0)));
    }

    // V3: single positive digit — straightforward count
    @Test
    void v3_singlePositiveDigit() {
        assertEquals(1, sol.countNums(Arrays.asList(7)));
    }

    // V4: multi-digit positive
    @Test
    void v4_multiDigitPositive() {
        assertEquals(1, sol.countNums(Arrays.asList(99)));
    }

    // V5: negative — signed sum < 0 (not counted)
    @Test
    void v5_negativeSignedSumNegative() {
        // -5: sum=5, firstDigit=5, correction -> 5-10 = -5
        assertEquals(0, sol.countNums(Arrays.asList(-5)));
    }

    // V6: negative — signed sum == 0 (not counted)
    @Test
    void v6_negativeSignedSumZero() {
        // -11: sum=2, firstDigit=1, correction -> 2-2 = 0
        assertEquals(0, sol.countNums(Arrays.asList(-11)));
    }

    // V7: negative — signed sum > 0 (counted)
    @Test
    void v7_negativeSignedSumPositive() {
        // -123: sum=6, firstDigit=1, correction -> 6-2 = 4
        assertEquals(1, sol.countNums(Arrays.asList(-123)));
    }

    // V8: mixed list — only positive element counts
    @Test
    void v8_mixedPosNegZero() {
        // 1 counted, -1 not counted, 0 not counted
        assertEquals(1, sol.countNums(Arrays.asList(1, -1, 0)));
    }

    // V9: mixed — two elements with signed sum > 0
    @Test
    void v9_mixedWithTwoCounted() {
        // -19: sum=10, fd=1, correction -> 8 (counted)
        // -9:  sum=9,  fd=9, correction -> -9 (not counted)
        // 5:   sum=5  (counted)
        assertEquals(2, sol.countNums(Arrays.asList(-19, -9, 5)));
    }

    // Boundary: single-element list with value 10 (leading 1, trailing 0)
    @Test
    void boundary_ten() {
        // sum=1, not negative -> counted
        assertEquals(1, sol.countNums(Arrays.asList(10)));
    }
}
