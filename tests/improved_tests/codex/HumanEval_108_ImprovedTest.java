import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Improved suite for HumanEval_108 (countNums) — Codex side.
 *
 * Test-smell improvements:
 *   - Assertion roulette: each case is an isolated @Test or parametrized row.
 *   - Magic numbers: display names document signed-digit-sum semantics.
 *   - Eager test: cases partitioned by concern.
 *
 * Branch-coverage targets in Solution.countNums:
 *   - Outer for-loop guard: empty list (not entered) vs non-empty.
 *   - First while (temp > 0): num==0 → not entered; num!=0 → entered.
 *   - if (num < 0): negative path vs non-negative path.
 *   - Second while (firstDigit >= 10): single-magnitude-digit → not entered; multi → entered.
 *   - if (sum > 0): counted path vs not-counted path.
 *
 * Note: Integer.MIN_VALUE is excluded from this suite — Math.abs(MIN_VALUE) overflows
 * to MIN_VALUE (negative), causing temp > 0 to be immediately false; the resulting
 * sum is 0 (not counted). This is a known behavioral difference from Claude's
 * implementation and is documented in the analysis file.
 */
public class HumanEval_108_ImprovedTest {

    private final Solution sol = new Solution();

    @Test
    @DisplayName("[] -> 0 (outer loop not entered)")
    void emptyList() {
        assertEquals(0, sol.countNums(Arrays.asList()));
    }

    @Test
    @DisplayName("[0] -> 0 (temp=0, inner while not entered, sum stays 0)")
    void zero() {
        assertEquals(0, sol.countNums(Arrays.asList(0)));
    }

    private static Stream<Arguments> singleDigitPositive() {
        return Stream.of(
            Arguments.of(1),
            Arguments.of(5),
            Arguments.of(9)
        );
    }

    @ParameterizedTest(name = "[{0}] -> 1 (single-digit positive)")
    @MethodSource("singleDigitPositive")
    @DisplayName("Single-digit positive numbers enter inner while once and are counted")
    void positiveSingleDigit(int n) {
        assertEquals(1, sol.countNums(Arrays.asList(n)));
    }

    @Test
    @DisplayName("[11] -> 1 (sum=2 > 0; inner while executes twice)")
    void positiveMultiDigit() {
        assertEquals(1, sol.countNums(Arrays.asList(11)));
    }

    @Test
    @DisplayName("[100] -> 1 (sum=1 > 0; zero digits do not disqualify)")
    void positiveWithZeroDigits() {
        assertEquals(1, sol.countNums(Arrays.asList(100)));
    }

    @Test
    @DisplayName("[-1] -> 0 (sum=1; firstDigit=1; after correction sum=-1 <= 0)")
    void negOne() {
        assertEquals(0, sol.countNums(Arrays.asList(-1)));
    }

    @Test
    @DisplayName("[-9] -> 0 (sum=9; firstDigit=9; after correction sum=-9 <= 0)")
    void negNine() {
        assertEquals(0, sol.countNums(Arrays.asList(-9)));
    }

    @Test
    @DisplayName("[-11] -> 0 (sum=2; firstDigit=1; correction -> sum=0)")
    void negEleven() {
        assertEquals(0, sol.countNums(Arrays.asList(-11)));
    }

    @Test
    @DisplayName("[-123] -> 1 (sum=6; firstDigit=1; correction -> sum=4 > 0)")
    void neg123() {
        assertEquals(1, sol.countNums(Arrays.asList(-123)));
    }

    @Test
    @DisplayName("[-19] -> 1 (sum=10; firstDigit=1; correction -> sum=8 > 0)")
    void neg19() {
        assertEquals(1, sol.countNums(Arrays.asList(-19)));
    }

    @Test
    @DisplayName("[-1, 11, -11] -> 1 (docstring example)")
    void docstringMixed() {
        assertEquals(1, sol.countNums(Arrays.asList(-1, 11, -11)));
    }

    @Test
    @DisplayName("[1, 1, 2] -> 3 (docstring example)")
    void docstringAllPositive() {
        assertEquals(3, sol.countNums(Arrays.asList(1, 1, 2)));
    }
}
