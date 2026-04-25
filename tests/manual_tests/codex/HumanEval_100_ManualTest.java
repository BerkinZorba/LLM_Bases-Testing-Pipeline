import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_100_ManualTest {

    private final Solution solution = new Solution();

    @Nested
    @DisplayName("Valid classes")
    class ValidClasses {
        @Test
        void smallestPositiveOddInput() {
            assertEquals(Arrays.asList(1), solution.makeAPile(1));
        }

        @Test
        void smallestPositiveEvenInput() {
            assertEquals(Arrays.asList(2, 4), solution.makeAPile(2));
        }

        @Test
        void generalOddInput() {
            assertEquals(Arrays.asList(5, 7, 9, 11, 13), solution.makeAPile(5));
        }

        @Test
        void generalEvenInput() {
            assertEquals(Arrays.asList(6, 8, 10, 12, 14, 16), solution.makeAPile(6));
        }

        @Test
        void largerOddInputKeepsProgression() {
            assertEquals(Arrays.asList(9, 11, 13, 15, 17, 19, 21, 23, 25), solution.makeAPile(9));
        }
    }

    @Nested
    @DisplayName("Boundaries and out-of-scope observations")
    class BoundariesAndOutOfScope {
        @Test
        void firstLevelMatchesInput() {
            assertEquals(4, solution.makeAPile(4).getFirst());
        }

        @Test
        void eachLevelAdvancesByTwo() {
            assertEquals(Arrays.asList(8, 10, 12, 14, 16, 18, 20, 22), solution.makeAPile(8));
        }

        @Test
        void zeroInputCurrentlyReturnsEmptyList() {
            assertEquals(Collections.emptyList(), solution.makeAPile(0));
        }

        @Test
        void negativeInputCurrentlyReturnsEmptyList() {
            assertEquals(Collections.emptyList(), solution.makeAPile(-3));
        }
    }
}
