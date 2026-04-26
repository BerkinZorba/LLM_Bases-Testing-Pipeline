import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_122_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Dataset cases")
    class Dataset {
        @Test
        @DisplayName("(arr=[1,-2,-3,41,57,76,87,88,99], k=3) -> -4 (negatives count, prefix only)")
        void d1() {
            assertEquals(-4, s.addElements(Arrays.asList(1, -2, -3, 41, 57, 76, 87, 88, 99), 3));
        }

        @Test
        @DisplayName("(arr=[111,121,3,4000,5,6], k=2) -> 0 (both prefix items have 3 digits)")
        void d2() {
            assertEquals(0, s.addElements(Arrays.asList(111, 121, 3, 4000, 5, 6), 2));
        }

        @Test
        @DisplayName("(arr=[11,21,3,90,5,6,7,8,9], k=4) -> 125 (every prefix element qualifies)")
        void d3() {
            assertEquals(125, s.addElements(Arrays.asList(11, 21, 3, 90, 5, 6, 7, 8, 9), 4));
        }

        @Test
        @DisplayName("(arr=[111,21,3,4000,5,6,7,8,9], k=4) -> 24 (only 21 + 3)")
        void d4() {
            assertEquals(24, s.addElements(Arrays.asList(111, 21, 3, 4000, 5, 6, 7, 8, 9), 4));
        }
    }

    @Nested
    @DisplayName("Branch coverage — two-digit predicate Math.abs(v) < 100")
    class TwoDigitBranch {
        @Test
        @DisplayName("predicate true at upper edge: 99 included")
        void truePosEdge() {
            assertEquals(99, s.addElements(Arrays.asList(99, 1000), 1));
        }

        @Test
        @DisplayName("predicate false at upper edge: 100 excluded")
        void falsePosEdge() {
            assertEquals(0, s.addElements(Arrays.asList(100), 1));
        }

        @Test
        @DisplayName("predicate true at lower edge: -99 included")
        void trueNegEdge() {
            assertEquals(-99, s.addElements(Arrays.asList(-99, -1000), 1));
        }

        @Test
        @DisplayName("predicate false at lower edge: -100 excluded")
        void falseNegEdge() {
            assertEquals(0, s.addElements(Arrays.asList(-100), 1));
        }

        @Test
        @DisplayName("zero qualifies (|0| < 100) and contributes nothing to the sum")
        void zeroQualifies() {
            assertEquals(0, s.addElements(Arrays.asList(0, 0, 0), 3));
        }
    }

    @Nested
    @DisplayName("Loop bound — only the first k elements are inspected")
    class LoopBound {
        @Test
        @DisplayName("k=1: tail values (even huge ones) ignored")
        void kOne() {
            assertEquals(7, s.addElements(Arrays.asList(7, 9999, 9999, 9999), 1));
        }

        @Test
        @DisplayName("k == arr.size(): every element scanned")
        void kEqualsLen() {
            assertEquals(11 + 22 + 33 - 4, s.addElements(Arrays.asList(11, 22, 33, -4, 500, 600), 6));
            // 500 and 600 are outside range, dropped; -4 retained
        }

        @Test
        @DisplayName("k=3 ignores qualifying elements past index 2")
        void kSkipsTail() {
            assertEquals(0, s.addElements(Arrays.asList(100, 200, 300, 1, 2, 3), 3));
        }
    }

    @Nested
    @DisplayName("Singletons and empty-sum cases")
    class Singleton {
        @Test
        @DisplayName("single qualifying element -> itself")
        void singleQualifies() {
            assertEquals(1, s.addElements(Arrays.asList(1), 1));
        }

        @Test
        @DisplayName("single non-qualifying element -> 0")
        void singleRejected() {
            assertEquals(0, s.addElements(Arrays.asList(123), 1));
        }

        @Test
        @DisplayName("single negative two-digit -> itself")
        void singleNegativeTwoDigit() {
            assertEquals(-50, s.addElements(Arrays.asList(-50), 1));
        }
    }

    @Nested
    @DisplayName("Mixed signs — sum keeps sign")
    class MixedSigns {
        @Test
        @DisplayName("positives and negatives within range cancel exactly")
        void cancel() {
            assertEquals(0, s.addElements(Arrays.asList(50, -50, 25, -25), 4));
        }

        @Test
        @DisplayName("only negatives qualify -> negative sum")
        void onlyNegatives() {
            assertEquals(-30, s.addElements(Arrays.asList(-10, -20, -1000), 2));
        }

        @Test
        @DisplayName("invariant batch on (10,20,1000): k=3 keeps 30; k=2 keeps 30; k=1 keeps 10")
        void invariantBatch() {
            List<Integer> arr = Arrays.asList(10, 20, 1000);
            assertAll(
                    () -> assertEquals(30, s.addElements(arr, 3)),
                    () -> assertEquals(30, s.addElements(arr, 2)),
                    () -> assertEquals(10, s.addElements(arr, 1))
            );
        }
    }

    @Nested
    @DisplayName("Larger / homogeneous inputs")
    class Larger {
        @Test
        @DisplayName("Constraint upper bound: 100-element list, all 1s -> 100")
        void upperBoundLength() {
            List<Integer> arr = Collections.nCopies(100, 1);
            assertEquals(100, s.addElements(arr, 100));
        }

        @Test
        @DisplayName("All elements out of range -> 0")
        void allOutOfRange() {
            assertEquals(0, s.addElements(Arrays.asList(100, -100, 1000, -1000, 9999), 5));
        }

        @Test
        @DisplayName("Boundary mix: -99, -100, 99, 100 -> -99 + 99 = 0")
        void boundaryMix() {
            assertEquals(0, s.addElements(Arrays.asList(-99, -100, 99, 100), 4));
        }
    }
}
