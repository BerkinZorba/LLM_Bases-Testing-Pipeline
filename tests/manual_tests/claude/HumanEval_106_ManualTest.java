import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Manual black-box JUnit 6 suite for HumanEval_106.
 * Cases are derived from tests/manual_tests/claude/HumanEval_106_blackbox.md.
 */
public class HumanEval_106_ManualTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Valid equivalence classes (V1-V8)")
    class ValidClasses {

        static Stream<Arguments> validCases() {
            return Stream.of(
                    Arguments.of("V1 n=0 empty",        0, Collections.emptyList()),
                    Arguments.of("V2 n=1 single odd",   1, List.of(1)),
                    Arguments.of("V3 n=2 odd+even",     2, Arrays.asList(1, 2)),
                    Arguments.of("V4 n=3 ends odd",     3, Arrays.asList(1, 2, 6)),
                    Arguments.of("V5 n=4 ends even",    4, Arrays.asList(1, 2, 6, 24)),
                    Arguments.of("V6 n=5 example",      5, Arrays.asList(1, 2, 6, 24, 15)),
                    Arguments.of("V7 n=7 dataset",      7, Arrays.asList(1, 2, 6, 24, 15, 720, 28)),
                    Arguments.of("V8 n=8 ends even",    8, Arrays.asList(1, 2, 6, 24, 15, 720, 28, 40320))
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("validCases")
        void validEquivalenceClasses(String label, int n, List<Integer> expected) {
            assertEquals(expected, s.f(n), label);
        }
    }

    @Nested
    @DisplayName("Element-level boundary analysis")
    class ElementBoundaries {

        @Test
        @DisplayName("All expected elements for i=1..8 individually verified")
        void allElementsForFirstEight() {
            List<Integer> result = s.f(8);
            assertEquals(1,     result.get(0), "i=1 odd sum");
            assertEquals(2,     result.get(1), "i=2 even factorial");
            assertEquals(6,     result.get(2), "i=3 odd sum");
            assertEquals(24,    result.get(3), "i=4 even factorial");
            assertEquals(15,    result.get(4), "i=5 odd sum");
            assertEquals(720,   result.get(5), "i=6 even factorial");
            assertEquals(28,    result.get(6), "i=7 odd sum");
            assertEquals(40320, result.get(7), "i=8 even factorial");
        }
    }

    @Nested
    @DisplayName("List-level boundaries")
    class ListBoundaries {

        @Test
        @DisplayName("n=0: loop never executes, returns empty list")
        void zeroSize() {
            assertEquals(Collections.emptyList(), s.f(0));
        }

        @Test
        @DisplayName("n=1: loop executes once, result has 1 element (odd sum)")
        void singleElementOdd() {
            List<Integer> r = s.f(1);
            assertEquals(1, r.size());
            assertEquals(1, r.get(0));
        }

        @Test
        @DisplayName("n=2: minimal case covering both parity branches")
        void minimalBothBranches() {
            assertEquals(Arrays.asList(1, 2), s.f(2));
        }

        @Test
        @DisplayName("Result size always equals n for n in [0..8]")
        void listSizeEqualsN() {
            for (int n = 0; n <= 8; n++) {
                assertEquals(n, s.f(n).size(), "size for n=" + n);
            }
        }
    }

    @Nested
    @DisplayName("Invalid / undefined-by-spec inputs (I1 — pinned)")
    class InvalidInputs {

        @Test
        @DisplayName("I1: n < 0 returns empty list (loop condition false immediately)")
        void negativeN_returnsEmptyList() {
            assertEquals(Collections.emptyList(), s.f(-1));
            assertEquals(Collections.emptyList(), s.f(-5));
        }
    }
}
