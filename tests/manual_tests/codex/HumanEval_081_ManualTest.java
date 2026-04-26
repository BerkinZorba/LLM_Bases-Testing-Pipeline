import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_081_ManualTest {

    private final Solution solution = new Solution();

    @Nested
    @DisplayName("Valid classes")
    class ValidClasses {
        @Test
        void exactMaximumMapsToAPlus() {
            assertEquals(List.of("A+"), solution.numericalLetterGrade(List.of(4.0)));
        }

        @Test
        void exactThresholdFallsToLowerBucket() {
            assertEquals(List.of("A-"), solution.numericalLetterGrade(List.of(3.7)));
        }

        @Test
        void middleBandValueMapsToBMinus() {
            assertEquals(List.of("B-"), solution.numericalLetterGrade(List.of(2.5)));
        }

        @Test
        void mixedInputPreservesOrdering() {
            assertEquals(
                    Arrays.asList("A+", "B", "C-", "C", "A-"),
                    solution.numericalLetterGrade(Arrays.asList(4.0, 3.0, 1.7, 2.0, 3.5))
            );
        }
    }

    @Nested
    @DisplayName("Boundary and invalid partitions")
    class BoundaryAndInvalidPartitions {
        @Test
        void minimumBoundaryMapsToE() {
            assertEquals(List.of("E"), solution.numericalLetterGrade(List.of(0.0)));
        }

        @Test
        void zeroPointSevenRemainsInDMinus() {
            assertEquals(List.of("D-"), solution.numericalLetterGrade(List.of(0.7)));
        }

        @Test
        void justAboveZeroPointSevenMovesToD() {
            assertEquals(List.of("D"), solution.numericalLetterGrade(List.of(0.8)));
        }

        @Test
        void emptyListReturnsEmptyList() {
            assertEquals(Collections.emptyList(), solution.numericalLetterGrade(Collections.emptyList()));
        }

        @Test
        void negativeOutOfScopeValueFallsThroughToE() {
            assertEquals(List.of("E"), solution.numericalLetterGrade(List.of(-0.5)));
        }

        @Test
        void aboveFourOutOfScopeValueFallsThroughToA() {
            assertEquals(List.of("A"), solution.numericalLetterGrade(List.of(4.1)));
        }
    }
}
