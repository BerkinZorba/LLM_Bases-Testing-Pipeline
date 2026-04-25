import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HumanEval_100_ManualTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Valid partitions — V1..V11")
    class Valid {
        @Test
        @DisplayName("V1: n=1 -> [1] (lower valid edge)")
        void v1_n1() {
            assertEquals(List.of(1), s.makeAPile(1));
        }

        @Test
        @DisplayName("V2: n=2 -> [2, 4] (smallest even n)")
        void v2_n2() {
            assertEquals(Arrays.asList(2, 4), s.makeAPile(2));
        }

        @Test
        @DisplayName("V3: n=3 -> [3, 5, 7]")
        void v3_n3() {
            assertEquals(Arrays.asList(3, 5, 7), s.makeAPile(3));
        }

        @Test
        @DisplayName("V4: n=4 -> [4, 6, 8, 10]")
        void v4_n4() {
            assertEquals(Arrays.asList(4, 6, 8, 10), s.makeAPile(4));
        }

        @Test
        @DisplayName("V5: n=5 -> [5, 7, 9, 11, 13]")
        void v5_n5() {
            assertEquals(Arrays.asList(5, 7, 9, 11, 13), s.makeAPile(5));
        }

        @Test
        @DisplayName("V6: n=6 -> [6, 8, 10, 12, 14, 16]")
        void v6_n6() {
            assertEquals(Arrays.asList(6, 8, 10, 12, 14, 16), s.makeAPile(6));
        }

        @Test
        @DisplayName("V7: n=8 -> [8, 10, 12, 14, 16, 18, 20, 22]")
        void v7_n8() {
            assertEquals(Arrays.asList(8, 10, 12, 14, 16, 18, 20, 22), s.makeAPile(8));
        }

        @Test
        @DisplayName("V8: n=9 -> closed-form check at every index")
        void v8_n9() {
            int n = 9;
            List<Integer> p = s.makeAPile(n);
            List<Integer> expected = new ArrayList<>();
            for (int i = 0; i < n; i++) expected.add(n + 2 * i);
            assertEquals(expected, p);
        }

        @Test
        @DisplayName("V9: n=10 -> [10, 12, ..., 28]")
        void v9_n10() {
            assertEquals(Arrays.asList(10, 12, 14, 16, 18, 20, 22, 24, 26, 28),
                    s.makeAPile(10));
        }

        @Test
        @DisplayName("V10: n=50 -> size==50 and last element == 3*50-2 == 148")
        void v10_n50() {
            List<Integer> p = s.makeAPile(50);
            assertAll(
                    () -> assertEquals(50, p.size()),
                    () -> assertEquals(50, p.get(0)),
                    () -> assertEquals(148, p.get(p.size() - 1))
            );
        }

        @Test
        @DisplayName("V11: n=100 -> size==100 and last element == 3*100-2 == 298")
        void v11_n100() {
            List<Integer> p = s.makeAPile(100);
            assertAll(
                    () -> assertEquals(100, p.size()),
                    () -> assertEquals(100, p.get(0)),
                    () -> assertEquals(298, p.get(p.size() - 1))
            );
        }
    }

    @Nested
    @DisplayName("Boundary partitions — B1..B3")
    class Boundary {
        @Test
        @DisplayName("B1: n=1 (lower edge) — loop body must run exactly once")
        void b1_n1() {
            List<Integer> p = s.makeAPile(1);
            assertAll(
                    () -> assertEquals(1, p.size()),
                    () -> assertEquals(1, p.get(0))
            );
        }

        @Test
        @DisplayName("B2: n=2 (one above edge) — first and last are distinct cells")
        void b2_n2() {
            List<Integer> p = s.makeAPile(2);
            assertAll(
                    () -> assertEquals(2, p.size()),
                    () -> assertEquals(2, p.get(0)),
                    () -> assertEquals(4, p.get(1))
            );
        }

        @Test
        @DisplayName("B3: n=100 (large) — sorted ascending and step==2 throughout")
        void b3_n100_sortedAndStepTwo() {
            List<Integer> p = s.makeAPile(100);
            List<Integer> sorted = new ArrayList<>(p);
            Collections.sort(sorted);
            assertEquals(p, sorted, "pile must already be sorted ascending");
            for (int i = 1; i < p.size(); i++) {
                assertEquals(2, p.get(i) - p.get(i - 1),
                        "step at index " + i + " was not +2");
            }
        }
    }

    @Nested
    @DisplayName("Postcondition invariants")
    class Invariants {
        @Test
        @DisplayName("first element equals n across many n values")
        void firstEqualsN() {
            assertAll(
                    () -> assertEquals(1, s.makeAPile(1).get(0)),
                    () -> assertEquals(7, s.makeAPile(7).get(0)),
                    () -> assertEquals(20, s.makeAPile(20).get(0))
            );
        }

        @Test
        @DisplayName("last element equals 3n-2 across many n values")
        void lastEquals3nMinus2() {
            int[] ns = {1, 2, 3, 7, 20, 99};
            for (int n : ns) {
                List<Integer> p = s.makeAPile(n);
                assertEquals(3 * n - 2, p.get(p.size() - 1),
                        "last element wrong for n=" + n);
            }
        }

        @Test
        @DisplayName("parity of every element matches parity of n")
        void parityPreserved() {
            List<Integer> odd = s.makeAPile(11);
            List<Integer> even = s.makeAPile(12);
            assertAll(
                    () -> assertTrue(odd.stream().allMatch(x -> x % 2 == 1),
                            "all elements must be odd when n is odd: " + odd),
                    () -> assertTrue(even.stream().allMatch(x -> x % 2 == 0),
                            "all elements must be even when n is even: " + even)
            );
        }
    }

    @Nested
    @DisplayName("Pinned undefined-by-spec behavior — U1, U2")
    class PinnedUndefined {
        @Test
        @DisplayName("U1: n=0 -> empty list (observed; not a spec contract)")
        void u1_zero() {
            assertEquals(List.of(), s.makeAPile(0));
        }

        @Test
        @DisplayName("U2: n=-3 -> empty list (observed; not a spec contract)")
        void u2_negative() {
            assertEquals(List.of(), s.makeAPile(-3));
        }
    }
}
