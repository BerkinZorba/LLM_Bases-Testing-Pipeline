import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_122_ManualTest {

    private final Solution solution = new Solution();

    @Nested
    @DisplayName("Valid classes")
    class ValidClasses {
        @Test
        void allEligiblePrefixValuesAreSummed() {
            assertEquals(125, solution.addElements(Arrays.asList(11, 21, 3, 90, 5, 6), 4));
        }

        @Test
        void mixedMagnitudesOnlyIncludeEligibleValues() {
            assertEquals(24, solution.addElements(Arrays.asList(111, 21, 3, 4000, 5, 6), 4));
        }

        @Test
        void negativeSingleDigitValuesRemainIncluded() {
            assertEquals(-4, solution.addElements(Arrays.asList(1, -2, -3, 41, 57), 3));
        }

        @Test
        void negativeTwoDigitValuesRemainIncluded() {
            assertEquals(-30, solution.addElements(Arrays.asList(-10, -20, 300, 4), 3));
        }
    }

    @Nested
    @DisplayName("Boundary and invalid partitions")
    class BoundaryAndInvalidPartitions {
        @Test
        void smallestInScopeInputReturnsTheOnlyElement() {
            assertEquals(1, solution.addElements(Arrays.asList(1), 1));
        }

        @Test
        void firstThreeDigitPositiveMagnitudeIsExcluded() {
            assertEquals(99, solution.addElements(Arrays.asList(99, 100), 2));
        }

        @Test
        void firstThreeDigitNegativeMagnitudeIsExcluded() {
            assertEquals(-99, solution.addElements(Arrays.asList(-99, -100), 2));
        }

        @Test
        void eligibleValuesAfterKAreIgnored() {
            assertEquals(0, solution.addElements(Arrays.asList(1000, 2000, 7, 8, 9), 2));
        }

        @Test
        void fullLengthPrefixConsumesEveryPosition() {
            assertEquals(51, solution.addElements(Arrays.asList(12, 123, -4, -56, 700, 99, -100), 7));
        }
    }
}
