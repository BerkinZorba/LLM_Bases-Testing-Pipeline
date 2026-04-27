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

public class HumanEval_103_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Dataset cases")
    class Dataset {
        @Test
        @DisplayName("roundedAvg(1, 5) == \"11\"")
        void d1() {
            assertEquals("11", s.roundedAvg(1, 5));
        }

        @Test
        @DisplayName("roundedAvg(7, 5) == -1 (n > m)")
        void d2() {
            assertEquals(-1, s.roundedAvg(7, 5));
        }

        @Test
        @DisplayName("roundedAvg(10, 20) == \"1111\"")
        void d3() {
            assertEquals("1111", s.roundedAvg(10, 20));
        }

        @Test
        @DisplayName("roundedAvg(20, 33) == \"11011\" (half-up rounding: 26.5 -> 27)")
        void d4() {
            assertEquals("11011", s.roundedAvg(20, 33));
        }
    }

    @Nested
    @DisplayName("Branch coverage — guard n > m")
    class GuardBranch {
        @Test
        @DisplayName("guard true: n strictly greater than m -> -1")
        void guardTrue() {
            assertEquals(-1, s.roundedAvg(10, 1));
        }

        @Test
        @DisplayName("guard false at edge: n == m -> singleton range, no -1")
        void guardFalseAtEdge() {
            assertEquals("1010", s.roundedAvg(10, 10));
        }

        @Test
        @DisplayName("guard true at edge: n == m + 1 -> -1")
        void guardTrueAtEdge() {
            assertEquals(-1, s.roundedAvg(11, 10));
        }
    }

    @Nested
    @DisplayName("Return type contract")
    class ReturnType {
        @Test
        @DisplayName("success path returns String")
        void successIsString() {
            Object r = s.roundedAvg(1, 5);
            assertInstanceOf(String.class, r);
        }

        @Test
        @DisplayName("failure path returns Integer (-1)")
        void failureIsInteger() {
            Object r = s.roundedAvg(7, 5);
            assertInstanceOf(Integer.class, r);
            assertEquals(-1, r);
        }
    }

    @Nested
    @DisplayName("Rounding behavior")
    class Rounding {
        @Test
        @DisplayName("exact integer average: avg(1..3) = 2 -> \"10\"")
        void exactInteger() {
            assertEquals("10", s.roundedAvg(1, 3));
        }

        @Test
        @DisplayName("half value rounds up (half-up): avg(1..4) = 2.5 -> 3 -> \"11\"")
        void halfRoundsUp() {
            assertEquals("11", s.roundedAvg(1, 4));
        }

        @Test
        @DisplayName("avg(1..6) = 3.5 -> 4 -> \"100\"")
        void anotherHalfRoundsUp() {
            assertEquals("100", s.roundedAvg(1, 6));
        }

        @Test
        @DisplayName("avg(1..2) = 1.5 -> 2 -> \"10\"")
        void smallHalfRoundsUp() {
            assertEquals("10", s.roundedAvg(1, 2));
        }
    }

    @Nested
    @DisplayName("Single-element ranges (loop body executes exactly once)")
    class Singleton {
        @Test
        @DisplayName("n == m == 1 -> \"1\"")
        void one() {
            assertEquals("1", s.roundedAvg(1, 1));
        }

        @Test
        @DisplayName("n == m == 2 -> \"10\"")
        void two() {
            assertEquals("10", s.roundedAvg(2, 2));
        }

        @Test
        @DisplayName("n == m == 8 -> \"1000\" (power-of-two binary form)")
        void eight() {
            assertEquals("1000", s.roundedAvg(8, 8));
        }
    }

    @Nested
    @DisplayName("Larger ranges")
    class Larger {
        @Test
        @DisplayName("avg(1..100) = 50.5 -> 51 -> \"110011\"")
        void oneTo100() {
            assertEquals("110011", s.roundedAvg(1, 100));
        }

        @Test
        @DisplayName("avg(50..100) = 75 -> \"1001011\"")
        void fiftyTo100() {
            assertEquals("1001011", s.roundedAvg(50, 100));
        }

        @Test
        @DisplayName("multi-level invariants for (10, 20): avg=15, binary=\"1111\"")
        void invariants() {
            Object r = s.roundedAvg(10, 20);
            assertAll(
                    () -> assertInstanceOf(String.class, r),
                    () -> assertEquals("1111", r),
                    () -> assertEquals(15, Integer.parseInt((String) r, 2))
            );
        }
    }
}
