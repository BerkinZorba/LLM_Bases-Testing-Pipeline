import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_122_ManualTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Valid partitions — V1..V11")
    class Valid {
        @Test
        @DisplayName("V1: ([1], 1) -> 1 (single qualifying element)")
        void v1() {
            assertEquals(1, s.addElements(Arrays.asList(1), 1));
        }

        @Test
        @DisplayName("V2: ([123], 1) -> 0 (single non-qualifying element)")
        void v2() {
            assertEquals(0, s.addElements(Arrays.asList(123), 1));
        }

        @Test
        @DisplayName("V3: every prefix qualifies -> 125 (dataset)")
        void v3() {
            assertEquals(125, s.addElements(Arrays.asList(11, 21, 3, 90, 5, 6, 7, 8, 9), 4));
        }

        @Test
        @DisplayName("V4: no prefix qualifies -> 0 (dataset)")
        void v4() {
            assertEquals(0, s.addElements(Arrays.asList(111, 121, 3, 4000, 5, 6), 2));
        }

        @Test
        @DisplayName("V5: mixed -> 24 (dataset)")
        void v5() {
            assertEquals(24, s.addElements(Arrays.asList(111, 21, 3, 4000, 5, 6, 7, 8, 9), 4));
        }

        @Test
        @DisplayName("V6: negatives contribute with sign -> -4 (dataset)")
        void v6() {
            assertEquals(-4, s.addElements(Arrays.asList(1, -2, -3, 41, 57, 76, 87, 88, 99), 3));
        }

        @Test
        @DisplayName("V7: only negatives qualify -> -30")
        void v7() {
            assertEquals(-30, s.addElements(Arrays.asList(-10, -20, -1000), 2));
        }

        @Test
        @DisplayName("V8: mixed signs cancel -> 0")
        void v8() {
            assertEquals(0, s.addElements(Arrays.asList(50, -50, 25, -25), 4));
        }

        @Test
        @DisplayName("V9: tail beyond k ignored -> 7")
        void v9() {
            assertEquals(7, s.addElements(Arrays.asList(7, 9999, 9999, 9999), 1));
        }

        @Test
        @DisplayName("V10: k == arr.size() -> 62 (out-of-range tail dropped by predicate)")
        void v10() {
            assertEquals(62, s.addElements(Arrays.asList(11, 22, 33, -4, 500, 600), 6));
        }

        @Test
        @DisplayName("V11: length-100 list of 1s -> 100 (constraint upper bound)")
        void v11() {
            List<Integer> arr = Collections.nCopies(100, 1);
            assertEquals(100, s.addElements(arr, 100));
        }
    }

    @Nested
    @DisplayName("Boundary partitions — B1..B7")
    class Boundary {
        @Test
        @DisplayName("B1: 99 included (upper edge of two-digit predicate)")
        void b1() {
            assertEquals(99, s.addElements(Arrays.asList(99, 1000), 1));
        }

        @Test
        @DisplayName("B2: 100 excluded (just past upper edge)")
        void b2() {
            assertEquals(0, s.addElements(Arrays.asList(100), 1));
        }

        @Test
        @DisplayName("B3: -99 included (lower edge of two-digit predicate)")
        void b3() {
            assertEquals(-99, s.addElements(Arrays.asList(-99, -1000), 1));
        }

        @Test
        @DisplayName("B4: -100 excluded (just past lower edge)")
        void b4() {
            assertEquals(0, s.addElements(Arrays.asList(-100), 1));
        }

        @Test
        @DisplayName("B5: symmetric edge mix [-99,-100,99,100] -> 0")
        void b5() {
            assertEquals(0, s.addElements(Arrays.asList(-99, -100, 99, 100), 4));
        }

        @Test
        @DisplayName("B6: k == arr.size() -> last index inspected")
        void b6() {
            assertEquals(30, s.addElements(Arrays.asList(10, 20, 1000), 3));
        }

        @Test
        @DisplayName("B7: smallest allowed k=1 -> loop runs once")
        void b7() {
            assertEquals(1, s.addElements(Arrays.asList(1), 1));
        }
    }

    @Nested
    @DisplayName("Pinned undefined-by-spec behavior — U1..U3")
    class PinnedUndefined {
        @Test
        @DisplayName("U1: k=0 -> 0 (loop body never runs); pinned, not a spec contract")
        void u1_kZero() {
            assertEquals(0, s.addElements(Arrays.asList(1, 2, 3), 0));
        }

        @Test
        @DisplayName("U2: Integer.MAX_VALUE -> 0 (excluded by predicate); pinned")
        void u2_maxValue() {
            assertEquals(0, s.addElements(Arrays.asList(Integer.MAX_VALUE), 1));
        }

        @Test
        @DisplayName("U3: Integer.MIN_VALUE included due to Math.abs(MIN_VALUE) == MIN_VALUE quirk; pinned")
        void u3_minValueQuirk() {
            // Math.abs(Integer.MIN_VALUE) returns Integer.MIN_VALUE (overflow), so
            // |v| < 100 evaluates true on this single value and it is summed in.
            assertEquals(Integer.MIN_VALUE, s.addElements(Arrays.asList(Integer.MIN_VALUE), 1));
        }
    }
}
