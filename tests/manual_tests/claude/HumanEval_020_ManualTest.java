import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Manual black-box JUnit 6 suite for HumanEval_020.
 * Cases are derived from tests/manual_tests/claude/HumanEval_020_blackbox.md.
 */
public class HumanEval_020_ManualTest {

    private final Solution s = new Solution();

    private List<Double> call(Double... values) {
        return s.findClosestElements(new ArrayList<>(Arrays.asList(values)));
    }

    @Nested
    @DisplayName("Valid equivalence classes (V1-V12)")
    class ValidClasses {

        static Stream<Arguments> validClasses() {
            return Stream.of(
                    Arguments.of("V1  two elements natural order",
                            Arrays.asList(1.0, 3.0),
                            Arrays.asList(1.0, 3.0)),
                    Arguments.of("V2  two elements reversed",
                            Arrays.asList(3.0, 1.0),
                            Arrays.asList(1.0, 3.0)),
                    Arguments.of("V3  duplicate elements — diff zero",
                            Arrays.asList(2.0, 5.0, 2.0),
                            Arrays.asList(2.0, 2.0)),
                    Arguments.of("V4  closest at start of sorted list",
                            Arrays.asList(1.0, 1.1, 3.0, 5.0),
                            Arrays.asList(1.0, 1.1)),
                    Arguments.of("V5  closest at end of sorted list",
                            Arrays.asList(1.0, 3.0, 4.9, 5.0),
                            Arrays.asList(4.9, 5.0)),
                    Arguments.of("V6  closest in middle of sorted list",
                            Arrays.asList(1.0, 2.0, 2.1, 4.0, 6.0),
                            Arrays.asList(2.0, 2.1)),
                    Arguments.of("V7  multiple pairs, unique closest",
                            Arrays.asList(1.0, 2.0, 3.9, 4.0, 5.0),
                            Arrays.asList(3.9, 4.0)),
                    Arguments.of("V8  all negative numbers",
                            Arrays.asList(-5.0, -3.0, -2.9, -1.0),
                            Arrays.asList(-3.0, -2.9)),
                    Arguments.of("V9  mixed positive and negative",
                            Arrays.asList(-0.1, 0.0, 3.0, 5.0),
                            Arrays.asList(-0.1, 0.0)),
                    Arguments.of("V10 all equal spacing — first pair wins",
                            Arrays.asList(1.0, 2.0, 3.0, 4.0),
                            Arrays.asList(1.0, 2.0)),
                    Arguments.of("V11 closest not adjacent in input",
                            Arrays.asList(1.0, 2.0, 5.9, 4.0, 5.0),
                            Arrays.asList(5.0, 5.9)),
                    Arguments.of("V12 two identical elements only",
                            Arrays.asList(4.0, 4.0),
                            Arrays.asList(4.0, 4.0))
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("validClasses")
        void allValidClasses(String classId, List<Double> input, List<Double> expected) {
            assertEquals(expected, s.findClosestElements(new ArrayList<>(input)),
                    () -> classId);
        }
    }

    @Nested
    @DisplayName("Boundary analysis")
    class Boundaries {

        @Test
        @DisplayName("Minimum list size: exactly two elements")
        void exactlyTwoElements() {
            assertEquals(Arrays.asList(2.0, 5.0), call(2.0, 5.0));
        }

        @Test
        @DisplayName("Diff = 0 boundary: duplicate pair")
        void diffZeroBoundary() {
            assertEquals(Arrays.asList(3.0, 3.0), call(1.0, 3.0, 3.0, 7.0));
        }

        @Test
        @DisplayName("Closest pair at sorted index 0 (first pair)")
        void closestAtFirstSortedIndex() {
            assertEquals(Arrays.asList(1.0, 1.05), call(1.0, 1.05, 3.0, 6.0));
        }

        @Test
        @DisplayName("Closest pair at last sorted index (last pair)")
        void closestAtLastSortedIndex() {
            assertEquals(Arrays.asList(3.0, 3.05), call(1.0, 3.0, 3.05));
        }

        @Test
        @DisplayName("Three-element list — loop runs twice")
        void threeElements() {
            assertEquals(Arrays.asList(2.0, 2.5), call(1.0, 2.0, 2.5));
        }
    }

    @Nested
    @DisplayName("Input list not mutated")
    class Immutability {

        @Test
        @DisplayName("Original list order is preserved after call")
        void inputNotMutated() {
            List<Double> input = new ArrayList<>(Arrays.asList(3.0, 1.0, 2.0));
            s.findClosestElements(input);
            assertEquals(Arrays.asList(3.0, 1.0, 2.0), input);
        }
    }

    @Nested
    @DisplayName("Invalid / undefined-by-spec inputs (pinned behavior)")
    class InvalidOrPinned {

        @Test
        @DisplayName("I1: null list throws NullPointerException")
        void nullListThrowsNpe() {
            assertThrows(NullPointerException.class,
                    () -> s.findClosestElements(null));
        }
    }
}
