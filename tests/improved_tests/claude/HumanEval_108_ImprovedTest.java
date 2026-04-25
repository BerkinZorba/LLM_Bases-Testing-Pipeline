import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Improved suite for HumanEval_108 (countNums).
 *
 * Test-smell improvements:
 *   - Assertion roulette: each case is a separate @Test or parameterized row.
 *   - Magic numbers: display names document signed-digit-sum semantics.
 *   - Eager test: cases partitioned by concern (empty, positive, negative,
 *     zero, boundary integers, sign-sum edge cases).
 *
 * Branch-coverage targets in Solution.countNums / signedDigitSum:
 *   - countNums loop: empty list (never entered) vs non-empty.
 *   - signedDigitSum: n == 0 early return; n < 0 with sign on first digit;
 *     n > 0 (all digits positive); multi-digit vs single-digit.
 *   - Count > 0 gate: sum positive (count++) vs sum <= 0 (skip).
 */
public class HumanEval_108_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Empty list")
    class EmptyList {
        @Test @DisplayName("[] -> 0")
        void empty() { assertEquals(0, s.countNums(List.of())); }
    }

    @Nested
    @DisplayName("Positive numbers — all have digit sum > 0")
    class PositiveNumbers {
        @ParameterizedTest(name = "countNums([{0}]) == 1")
        @CsvSource({"1", "9", "10", "11", "123", "100"})
        void singlePositive(int v) {
            assertEquals(1, s.countNums(List.of(v)));
        }

        @Test @DisplayName("[1,1,2] -> 3 (docstring example)")
        void docstringExample() { assertEquals(3, s.countNums(Arrays.asList(1, 1, 2))); }
    }

    @Nested
    @DisplayName("Zero — digit sum == 0, not counted")
    class Zero {
        @Test @DisplayName("[0] -> 0")
        void zero() { assertEquals(0, s.countNums(List.of(0))); }

        @Test @DisplayName("[0, 0, 0] -> 0")
        void multipleZeros() { assertEquals(0, s.countNums(Arrays.asList(0, 0, 0))); }
    }

    @Nested
    @DisplayName("Negative numbers — first digit negated")
    class NegativeNumbers {
        @Test @DisplayName("[-1] -> 0 (digit sum = -1)")
        void negOne() { assertEquals(0, s.countNums(List.of(-1))); }

        @Test @DisplayName("[-11] -> 0 (digit sum = -1+1 = 0)")
        void negEleven() { assertEquals(0, s.countNums(List.of(-11))); }

        @Test @DisplayName("[-123] -> 1 (digit sum = -1+2+3 = 4 > 0)")
        void neg123() { assertEquals(1, s.countNums(List.of(-123))); }

        @Test @DisplayName("[-1, 11, -11] -> 1 (docstring example)")
        void docstringMixed() { assertEquals(1, s.countNums(Arrays.asList(-1, 11, -11))); }

        @Test @DisplayName("[-9] -> 0 (digit sum = -9)")
        void negNine() { assertEquals(0, s.countNums(List.of(-9))); }

        @Test @DisplayName("[-19] -> 0 (digit sum = -1+9 = 8? no: -1+9=8 > 0 -> 1)")
        void neg19() { assertEquals(1, s.countNums(List.of(-19))); }
    }

    @Nested
    @DisplayName("Boundary: Integer.MIN_VALUE")
    class Boundary {
        @Test @DisplayName("Integer.MIN_VALUE: signedDigitSum handled safely (no overflow)")
        void minValue() {
            // MIN_VALUE = -2147483648; signed digits: -2,1,4,7,4,8,3,6,4,8 -> sum=43 > 0
            assertEquals(1, s.countNums(List.of(Integer.MIN_VALUE)));
        }
    }

    @Nested
    @DisplayName("Mixed lists")
    class MixedLists {
        @Test @DisplayName("[0, -1, 1] -> 1 (only 1 counts)")
        void zeroNegPos() { assertEquals(1, s.countNums(Arrays.asList(0, -1, 1))); }

        @Test @DisplayName("[-123, -11, 0, 5] -> 2")
        void fourMixed() { assertEquals(2, s.countNums(Arrays.asList(-123, -11, 0, 5))); }
    }
}
