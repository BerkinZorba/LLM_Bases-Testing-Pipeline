import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HumanEval_100_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Dataset cases — odd starting n")
    class DatasetOddStart {
        @Test
        @DisplayName("n=3 -> [3, 5, 7] (smallest odd dataset case)")
        void n3() {
            assertEquals(Arrays.asList(3, 5, 7), s.makeAPile(3));
        }

        @Test
        @DisplayName("n=5 -> [5, 7, 9, 11, 13]")
        void n5() {
            assertEquals(Arrays.asList(5, 7, 9, 11, 13), s.makeAPile(5));
        }
    }

    @Nested
    @DisplayName("Dataset cases — even starting n")
    class DatasetEvenStart {
        @Test
        @DisplayName("n=4 -> [4, 6, 8, 10]")
        void n4() {
            assertEquals(Arrays.asList(4, 6, 8, 10), s.makeAPile(4));
        }

        @Test
        @DisplayName("n=6 -> [6, 8, 10, 12, 14, 16]")
        void n6() {
            assertEquals(Arrays.asList(6, 8, 10, 12, 14, 16), s.makeAPile(6));
        }

        @Test
        @DisplayName("n=8 -> [8, 10, 12, 14, 16, 18, 20, 22]")
        void n8() {
            assertEquals(Arrays.asList(8, 10, 12, 14, 16, 18, 20, 22), s.makeAPile(8));
        }
    }

    @Nested
    @DisplayName("Boundary — smallest valid n")
    class Boundary {
        @Test
        @DisplayName("n=1 returns the singleton [1] (loop-body executed exactly once)")
        void n1() {
            assertEquals(List.of(1), s.makeAPile(1));
        }

        @Test
        @DisplayName("n=2 returns [2, 4] (smallest even start)")
        void n2() {
            assertEquals(Arrays.asList(2, 4), s.makeAPile(2));
        }
    }

    @Nested
    @DisplayName("Structural invariants")
    class Invariants {
        @Test
        @DisplayName("size(pile) == n for a sweep across small n")
        void sizeEqualsN() {
            assertAll(
                    () -> assertEquals(1, s.makeAPile(1).size()),
                    () -> assertEquals(2, s.makeAPile(2).size()),
                    () -> assertEquals(7, s.makeAPile(7).size()),
                    () -> assertEquals(10, s.makeAPile(10).size()),
                    () -> assertEquals(50, s.makeAPile(50).size())
            );
        }

        @Test
        @DisplayName("first element equals n")
        void firstEqualsN() {
            assertAll(
                    () -> assertEquals(1, s.makeAPile(1).get(0)),
                    () -> assertEquals(7, s.makeAPile(7).get(0)),
                    () -> assertEquals(50, s.makeAPile(50).get(0))
            );
        }

        @Test
        @DisplayName("last element equals n + 2*(n-1) = 3n-2")
        void lastEquals3nMinus2() {
            List<Integer> p7 = s.makeAPile(7);
            List<Integer> p10 = s.makeAPile(10);
            List<Integer> p50 = s.makeAPile(50);
            assertAll(
                    () -> assertEquals(3 * 7 - 2, p7.get(p7.size() - 1)),
                    () -> assertEquals(3 * 10 - 2, p10.get(p10.size() - 1)),
                    () -> assertEquals(3 * 50 - 2, p50.get(p50.size() - 1))
            );
        }

        @Test
        @DisplayName("consecutive elements always differ by exactly +2 (arithmetic progression)")
        void stepIsTwo() {
            List<Integer> p = s.makeAPile(10);
            assertAll(
                    () -> assertEquals(2, p.get(1) - p.get(0)),
                    () -> assertEquals(2, p.get(2) - p.get(1)),
                    () -> assertEquals(2, p.get(3) - p.get(2)),
                    () -> assertEquals(2, p.get(4) - p.get(3)),
                    () -> assertEquals(2, p.get(5) - p.get(4)),
                    () -> assertEquals(2, p.get(6) - p.get(5)),
                    () -> assertEquals(2, p.get(7) - p.get(6)),
                    () -> assertEquals(2, p.get(8) - p.get(7)),
                    () -> assertEquals(2, p.get(9) - p.get(8))
            );
        }

        @Test
        @DisplayName("parity is preserved across every level (odd n -> all odd; even n -> all even)")
        void parityPreserved() {
            List<Integer> odd = s.makeAPile(7);
            List<Integer> even = s.makeAPile(8);
            assertAll(
                    () -> assertTrue(odd.stream().allMatch(x -> x % 2 == 1),
                            "all elements must be odd when n is odd: " + odd),
                    () -> assertTrue(even.stream().allMatch(x -> x % 2 == 0),
                            "all elements must be even when n is even: " + even)
            );
        }

        @Test
        @DisplayName("element at index i is exactly n + 2*i (closed form)")
        void closedForm() {
            int n = 9;
            List<Integer> p = s.makeAPile(n);
            assertAll(
                    () -> assertEquals(n + 0, p.get(0)),
                    () -> assertEquals(n + 2, p.get(1)),
                    () -> assertEquals(n + 4, p.get(2)),
                    () -> assertEquals(n + 6, p.get(3)),
                    () -> assertEquals(n + 8, p.get(4)),
                    () -> assertEquals(n + 10, p.get(5)),
                    () -> assertEquals(n + 12, p.get(6)),
                    () -> assertEquals(n + 14, p.get(7)),
                    () -> assertEquals(n + 16, p.get(8))
            );
        }
    }

    @Nested
    @DisplayName("Branch coverage — for-loop guard")
    class BranchCoverage {
        @Test
        @DisplayName("loop body executes for n=1 (single iteration)")
        void loopRunsOnce() {
            assertEquals(1, s.makeAPile(1).size());
        }

        @Test
        @DisplayName("loop body executes many times for moderately large n")
        void loopRunsMany() {
            assertEquals(100, s.makeAPile(100).size());
        }
    }
}
