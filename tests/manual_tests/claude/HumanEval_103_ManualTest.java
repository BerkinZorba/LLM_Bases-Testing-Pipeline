import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class HumanEval_103_ManualTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Valid partitions — V1..V9")
    class Valid {
        @Test
        @DisplayName("V1: (1, 1) -> \"1\" (singleton range, smallest valid n)")
        void v1() {
            assertEquals("1", s.roundedAvg(1, 1));
        }

        @Test
        @DisplayName("V2: (8, 8) -> \"1000\" (power-of-two singleton)")
        void v2() {
            assertEquals("1000", s.roundedAvg(8, 8));
        }

        @Test
        @DisplayName("V3: (1, 3) -> \"10\" (exact integer average, 2)")
        void v3() {
            assertEquals("10", s.roundedAvg(1, 3));
        }

        @Test
        @DisplayName("V4: (1, 4) -> \"11\" (half-up rule: 2.5 -> 3)")
        void v4() {
            assertEquals("11", s.roundedAvg(1, 4));
        }

        @Test
        @DisplayName("V5: (1, 5) -> \"11\" (dataset)")
        void v5() {
            assertEquals("11", s.roundedAvg(1, 5));
        }

        @Test
        @DisplayName("V6: (10, 20) -> \"1111\" (dataset, integer mid 15)")
        void v6() {
            assertEquals("1111", s.roundedAvg(10, 20));
        }

        @Test
        @DisplayName("V7: (20, 33) -> \"11011\" (dataset, half-up 26.5 -> 27)")
        void v7() {
            assertEquals("11011", s.roundedAvg(20, 33));
        }

        @Test
        @DisplayName("V8: (1, 100) -> \"110011\" (50.5 -> 51)")
        void v8() {
            assertEquals("110011", s.roundedAvg(1, 100));
        }

        @Test
        @DisplayName("V9: (50, 100) -> \"1001011\" (avg = 75 exactly)")
        void v9() {
            assertEquals("1001011", s.roundedAvg(50, 100));
        }
    }

    @Nested
    @DisplayName("Boundary partitions — B1..B5")
    class Boundary {
        @Test
        @DisplayName("B1: (1, 1) — lower-edge valid, guard false, loop runs once")
        void b1() {
            assertEquals("1", s.roundedAvg(1, 1));
        }

        @Test
        @DisplayName("B2: (11, 10) — n == m + 1, guard true -> -1")
        void b2() {
            assertEquals(-1, s.roundedAvg(11, 10));
        }

        @Test
        @DisplayName("B3: (100, 1) — well inside invalid region, -> -1")
        void b3() {
            assertEquals(-1, s.roundedAvg(100, 1));
        }

        @Test
        @DisplayName("B4: (1, 2) — smallest fractional avg, 1.5 -> 2 -> \"10\"")
        void b4() {
            assertEquals("10", s.roundedAvg(1, 2));
        }

        @Test
        @DisplayName("B5: (20, 33) — half-up at fractional 26.5 -> 27 -> \"11011\"")
        void b5() {
            assertEquals("11011", s.roundedAvg(20, 33));
        }
    }

    @Nested
    @DisplayName("Return-type contract")
    class ReturnType {
        @Test
        @DisplayName("success path returns String")
        void successIsString() {
            assertInstanceOf(String.class, s.roundedAvg(1, 5));
        }

        @Test
        @DisplayName("failure path returns Integer with value -1")
        void failureIsIntegerMinusOne() {
            Object r = s.roundedAvg(7, 5);
            assertAll(
                    () -> assertInstanceOf(Integer.class, r),
                    () -> assertEquals(-1, r)
            );
        }
    }

    @Nested
    @DisplayName("Pinned undefined-by-spec behavior — U1, U2")
    class PinnedUndefined {
        @Test
        @DisplayName("U1: (0, 5) -> \"11\" (avg(0..5) = 2.5 -> 3); pinned, not a spec contract")
        void u1_zeroN() {
            assertEquals("11", s.roundedAvg(0, 5));
        }

        @Test
        @DisplayName("U2: (-2, 2) -> \"0\" (avg = 0); pinned, not a spec contract")
        void u2_negativeN() {
            assertEquals("0", s.roundedAvg(-2, 2));
        }
    }
}
