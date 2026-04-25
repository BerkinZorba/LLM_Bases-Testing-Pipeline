import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Improved suite for HumanEval_057 (monotonic: list is non-decreasing OR non-increasing).
 *
 * Test-smell improvements over the dataset base test:
 *   - Assertion roulette: dataset Main packed 8 booleans into a single List<Boolean>
 *     with one AssertionError; here every case is its own @Test with a descriptive name.
 *   - Magic numbers: every list literal is paired with @DisplayName explaining the
 *     behavioral concern (strict-up, strict-down, plateau, single direction violation).
 *   - Eager test: cases are partitioned into nested classes by behavioral concern
 *     (degenerate sizes, strict monotonic, plateaus/equals, non-monotonic, branch matrix).
 *   - Conditional logic in tests: no loops/ifs in test bodies; values are listed.
 *
 * Branch-coverage targets in `Solution.monotonic`:
 *   - Outer for: list of size 0 and size 1 (loop never enters) vs size >= 2 (enters).
 *   - First if (`l.get(i) < l.get(i-1)`): true (strict drop) and false (equal or up).
 *   - Second if (`l.get(i) > l.get(i-1)`): true (strict rise) and false (equal or down).
 *   - Final OR: nonDecreasing-only true, nonIncreasing-only true, both true (all-equal),
 *     both false (a rise AND a drop somewhere -> not monotonic).
 */
public class HumanEval_057_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Loop-entry boundaries on list size")
    class DegenerateSizes {
        @Test
        @DisplayName("Empty list: loop never enters -> both flags stay true -> true")
        void emptyList_isMonotonic() {
            assertTrue(s.monotonic(new ArrayList<>()));
        }

        @Test
        @DisplayName("Single-element list: loop never enters -> true")
        void singleton_isMonotonic() {
            assertTrue(s.monotonic(new ArrayList<>(Collections.singletonList(42))));
        }
    }

    @Nested
    @DisplayName("Strictly monotonic sequences")
    class StrictlyMonotonic {
        @Test
        @DisplayName("Strictly increasing two-element list -> true")
        void strictUp_pair() {
            assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(1, 2))));
        }

        @Test
        @DisplayName("Strictly decreasing two-element list -> true")
        void strictDown_pair() {
            assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(2, 1))));
        }

        @Test
        @DisplayName("Strictly increasing across negatives and positives -> true")
        void strictUp_mixedSign() {
            assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(-5, -1, 0, 3, 100))));
        }

        @Test
        @DisplayName("Strictly decreasing across positives and negatives -> true")
        void strictDown_mixedSign() {
            assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(4, 1, 0, -10))));
        }
    }

    @Nested
    @DisplayName("Plateaus and equal-element behaviour")
    class Plateaus {
        @Test
        @DisplayName("All equal: both rise/drop ifs always false -> both flags stay true -> true")
        void allEqual_isMonotonic() {
            assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(9, 9, 9, 9))));
        }

        @Test
        @DisplayName("Pair of equals: never enters either inner if -> true")
        void equalPair_isMonotonic() {
            assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(7, 7))));
        }

        @Test
        @DisplayName("Non-decreasing with a plateau (1,2,2,3) -> true")
        void nonDecreasingWithPlateau_isMonotonic() {
            assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(1, 2, 2, 3))));
        }

        @Test
        @DisplayName("Non-increasing with a plateau (4,1,1,0) -> true")
        void nonIncreasingWithPlateau_isMonotonic() {
            assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(4, 1, 1, 0))));
        }
    }

    @Nested
    @DisplayName("Non-monotonic sequences")
    class NotMonotonic {
        @Test
        @DisplayName("Up then down two-step (1,3,2) sets BOTH flags false -> false")
        void upThenDown_threeElements() {
            assertFalse(s.monotonic(new ArrayList<>(Arrays.asList(1, 3, 2))));
        }

        @Test
        @DisplayName("Down then up two-step (3,1,2) sets BOTH flags false -> false")
        void downThenUp_threeElements() {
            assertFalse(s.monotonic(new ArrayList<>(Arrays.asList(3, 1, 2))));
        }

        @Test
        @DisplayName("Spike pattern (1,20,4,10) -> false")
        void spike_isNotMonotonic() {
            assertFalse(s.monotonic(new ArrayList<>(Arrays.asList(1, 20, 4, 10))));
        }

        @Test
        @DisplayName("Dip pattern (1,2,3,2,5,60) -> false")
        void dip_isNotMonotonic() {
            assertFalse(s.monotonic(new ArrayList<>(Arrays.asList(1, 2, 3, 2, 5, 60))));
        }
    }

    @Nested
    @DisplayName("Dataset reference values")
    class DatasetParity {
        @Test
        @DisplayName("Dataset case: monotonic([1,2,4,10]) == true")
        void d1() { assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(1, 2, 4, 10)))); }

        @Test
        @DisplayName("Dataset case: monotonic([1,2,4,20]) == true")
        void d2() { assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(1, 2, 4, 20)))); }

        @Test
        @DisplayName("Dataset case: monotonic([1,2,3,4,5,60]) == true")
        void d3() { assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 60)))); }
    }

    @Nested
    @DisplayName("Functional invariants (oracle-based, no recomputation in test)")
    class Invariants {
        @Test
        @DisplayName("Reversal preserves monotonicity (true list reversed is still monotonic)")
        void reversal_preservesTrue() {
            List<Integer> up = new ArrayList<>(Arrays.asList(1, 2, 4, 10));
            List<Integer> down = new ArrayList<>(up);
            Collections.reverse(down);
            assertAll(
                    () -> assertTrue(s.monotonic(up)),
                    () -> assertTrue(s.monotonic(down))
            );
        }

        @Test
        @DisplayName("Reversal preserves non-monotonicity (spike reversed is still non-monotonic)")
        void reversal_preservesFalse() {
            List<Integer> spike = new ArrayList<>(Arrays.asList(1, 20, 4, 10));
            List<Integer> reversed = new ArrayList<>(spike);
            Collections.reverse(reversed);
            assertAll(
                    () -> assertFalse(s.monotonic(spike)),
                    () -> assertFalse(s.monotonic(reversed))
            );
        }

        @Test
        @DisplayName("Constant offset of every element preserves the verdict")
        void offsetInvariant() {
            List<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 4, 10));
            List<Integer> b = new ArrayList<>(Arrays.asList(101, 102, 104, 110));
            assertEquals(s.monotonic(a), s.monotonic(b));
        }

        @Test
        @DisplayName("Two-element list is always monotonic regardless of order or equality")
        void anyPair_isMonotonic() {
            assertAll(
                    () -> assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(0, 0)))),
                    () -> assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(-1, 1)))),
                    () -> assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(1, -1)))),
                    () -> assertTrue(s.monotonic(new ArrayList<>(Arrays.asList(Integer.MIN_VALUE, Integer.MAX_VALUE))))
            );
        }
    }
}
