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
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * Improved JUnit 6 suite for HumanEval_106 (f).
 *
 * Improvements over the dataset harness:
 *   - splits assertion-roulette block into named tests;
 *   - adds n=0 (empty list — loop never runs);
 *   - adds n=2 (minimal case exercising both odd and even branches once each);
 *   - adds n=6 (ends on an even index to verify factorial is the last element);
 *   - verifies each individual element value by position for larger n;
 *   - verifies the returned list is a fresh instance per call (no shared state);
 *   - adds focused single-element tests for first odd and first even index.
 */
public class HumanEval_106_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Dataset cases — one assertion per test")
    class DatasetCases {

        @Test void f5_correctList() {
            assertEquals(Arrays.asList(1, 2, 6, 24, 15), s.f(5));
        }

        @Test void f7_correctList() {
            assertEquals(Arrays.asList(1, 2, 6, 24, 15, 720, 28), s.f(7));
        }

        @Test void f1_singleOddElement() {
            assertEquals(List.of(1), s.f(1));
        }

        @Test void f3_correctList() {
            assertEquals(Arrays.asList(1, 2, 6), s.f(3));
        }
    }

    @Nested
    @DisplayName("Edge cases")
    class EdgeCases {

        @Test
        @DisplayName("n=0 returns empty list (loop never executes)")
        void f0_returnsEmptyList() {
            assertEquals(Collections.emptyList(), s.f(0));
        }

        @Test
        @DisplayName("n=2: minimal case — one odd (i=1) and one even (i=2)")
        void f2_minimalBothBranches() {
            // i=1 odd: sum(1..1)=1; i=2 even: factorial(2)=2
            assertEquals(Arrays.asList(1, 2), s.f(2));
        }

        @Test
        @DisplayName("n=6: last element is factorial(6)=720")
        void f6_endsOnEven() {
            assertEquals(Arrays.asList(1, 2, 6, 24, 15, 720), s.f(6));
        }
    }

    @Nested
    @DisplayName("Individual element verification")
    class IndividualElements {

        static Stream<Arguments> indexAndValues() {
            return Stream.of(
                    // (index 0-based in result, i 1-based in spec, expected)
                    Arguments.of(1, "i=1 odd: sum(1..1)=1",         1),
                    Arguments.of(2, "i=2 even: factorial(2)=2",     2),
                    Arguments.of(3, "i=3 odd: sum(1..3)=6",         6),
                    Arguments.of(4, "i=4 even: factorial(4)=24",   24),
                    Arguments.of(5, "i=5 odd: sum(1..5)=15",       15),
                    Arguments.of(6, "i=6 even: factorial(6)=720", 720),
                    Arguments.of(7, "i=7 odd: sum(1..7)=28",       28),
                    Arguments.of(8, "i=8 even: factorial(8)=40320", 40320)
            );
        }

        @ParameterizedTest(name = "{1}")
        @MethodSource("indexAndValues")
        void elementAt(int i, String label, int expected) {
            assertEquals(expected, s.f(i).get(i - 1), label);
        }
    }

    @Nested
    @DisplayName("Result list properties")
    class ListProperties {

        @Test
        @DisplayName("List size equals n")
        void listSizeEqualsN() {
            for (int n = 0; n <= 8; n++) {
                assertEquals(n, s.f(n).size(), "size for n=" + n);
            }
        }

        @Test
        @DisplayName("Repeated calls return independent (not same) list instances")
        void repeatedCallsReturnFreshInstances() {
            List<Integer> first = s.f(5);
            List<Integer> second = s.f(5);
            assertNotSame(first, second);
            assertEquals(first, second);
        }
    }
}
