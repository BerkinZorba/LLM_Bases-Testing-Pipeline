import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class HumanEval_103_ManualTest {

    private final Solution solution = new Solution();

    @Nested
    @DisplayName("Valid classes")
    class ValidClasses {
        @Test
        void integerAverageRange() {
            assertEquals("1111", solution.roundedAvg(10, 20));
        }

        @Test
        void halfStepAverageRoundsUp() {
            assertEquals("110", solution.roundedAvg(5, 6));
        }

        @Test
        void singleValueRangeReturnsBinaryString() {
            assertEquals("101", solution.roundedAvg(5, 5));
        }

        @Test
        void largerRepresentativeRange() {
            assertEquals("1011000010", solution.roundedAvg(560, 851));
        }
    }

    @Nested
    @DisplayName("Invalid and boundary classes")
    class InvalidAndBoundaryClasses {
        @Test
        void descendingRangeReturnsIntegerMinusOne() {
            Object result = solution.roundedAvg(7, 5);
            assertInstanceOf(Integer.class, result);
            assertEquals(-1, result);
        }

        @Test
        void smallestSingleValueRange() {
            assertEquals("1", solution.roundedAvg(1, 1));
        }

        @Test
        void smallestIncreasingRange() {
            assertEquals("10", solution.roundedAvg(1, 2));
        }

        @Test
        void equalEndpointsAtNineStayNineInBinary() {
            assertEquals("1001", solution.roundedAvg(9, 9));
        }

        @Test
        void nearIntegerMaxValueAvoidsOverflow() {
            assertEquals(
                    Integer.toBinaryString(Integer.MAX_VALUE - 1),
                    solution.roundedAvg(Integer.MAX_VALUE - 2, Integer.MAX_VALUE)
            );
        }
    }
}
