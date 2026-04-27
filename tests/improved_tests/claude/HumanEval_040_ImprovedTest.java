import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Improved JUnit 6 suite for HumanEval_040 (triplesSumToZero).
 * Tested against generated_code/claude/HumanEval_040_v2.java.
 *
 * Improvements over the dataset harness:
 *   - splits the original assertion roulette into named, parameterized rows;
 *   - exercises the n < 3 guard: empty, one-element, two-element lists;
 *   - adds all-negative, all-zero, duplicate-value, and large-value cases;
 *   - adds cases where the zero-sum triple spans the first/last elements of the sorted list;
 *   - adds a case with exactly three elements that do / don't sum to zero.
 */
public class HumanEval_040_ImprovedTest {

    private final Solution s = new Solution();

    private boolean call(Integer... values) {
        return s.triplesSumToZero(new ArrayList<>(Arrays.asList(values)));
    }

    @Nested
    @DisplayName("Dataset cases — one assertion per row")
    class DatasetCases {

        static Stream<Arguments> falseDatasetCases() {
            return Stream.of(
                    Arguments.of("1,3,5,0",        Arrays.asList(1, 3, 5, 0)),
                    Arguments.of("1,3,5,-1",       Arrays.asList(1, 3, 5, -1)),
                    Arguments.of("1,2,3,7",        Arrays.asList(1, 2, 3, 7)),
                    Arguments.of("1,2,5,7",        Arrays.asList(1, 2, 5, 7)),
                    Arguments.of("1",              Arrays.asList(1)),
                    Arguments.of("1,3,5,-100",     Arrays.asList(1, 3, 5, -100)),
                    Arguments.of("100,3,5,-100",   Arrays.asList(100, 3, 5, -100))
            );
        }

        @ParameterizedTest(name = "false — [{0}]")
        @MethodSource("falseDatasetCases")
        void expectFalse(String label, List<Integer> input) {
            assertFalse(s.triplesSumToZero(new ArrayList<>(input)), label);
        }

        static Stream<Arguments> trueDatasetCases() {
            return Stream.of(
                    Arguments.of("1,3,-2,1",           Arrays.asList(1, 3, -2, 1)),
                    Arguments.of("2,4,-5,3,9,7",       Arrays.asList(2, 4, -5, 3, 9, 7))
            );
        }

        @ParameterizedTest(name = "true — [{0}]")
        @MethodSource("trueDatasetCases")
        void expectTrue(String label, List<Integer> input) {
            assertTrue(s.triplesSumToZero(new ArrayList<>(input)), label);
        }
    }

    @Nested
    @DisplayName("Guard branch: list shorter than 3")
    class ShortListGuard {

        @Test
        @DisplayName("Empty list returns false")
        void emptyList() {
            assertFalse(call());
        }

        @Test
        @DisplayName("One-element list returns false")
        void oneElement() {
            assertFalse(call(5));
        }

        @Test
        @DisplayName("Two-element list returns false")
        void twoElements() {
            assertFalse(call(0, 0));
        }
    }

    @Nested
    @DisplayName("Exactly three elements")
    class ExactlyThreeElements {

        @Test
        @DisplayName("Three elements that sum to zero")
        void threeSumToZero() {
            assertTrue(call(-3, 1, 2));
        }

        @Test
        @DisplayName("Three elements that do not sum to zero")
        void threeDoNotSumToZero() {
            assertFalse(call(1, 2, 4));
        }

        @Test
        @DisplayName("Three zeros sum to zero")
        void threeZeros() {
            assertTrue(call(0, 0, 0));
        }
    }

    @Nested
    @DisplayName("Behavioral properties")
    class BehavioralProperties {

        @Test
        @DisplayName("All-negative list with a zero-sum triple")
        void allNegativeWithTriple() {
            assertTrue(call(-3, -2, -1, 5, 6));
        }

        @Test
        @DisplayName("All-positive list — no zero-sum triple possible")
        void allPositiveNoTriple() {
            assertFalse(call(1, 2, 3, 4, 5));
        }

        @Test
        @DisplayName("Duplicate values form the zero-sum triple")
        void duplicateValuesFormTriple() {
            assertTrue(call(-2, 1, 1, 5));
        }

        @Test
        @DisplayName("Zero-sum triple uses first and last sorted elements")
        void tripleSpansFirstAndLast() {
            assertTrue(call(-10, 3, 7, 100));
        }

        @Test
        @DisplayName("Large values, no zero-sum triple")
        void largeValuesNoTriple() {
            assertFalse(call(1000000, 999999, -1, -2));
        }

        @Test
        @DisplayName("Input list is not mutated by the call")
        void inputNotMutated() {
            List<Integer> input = new ArrayList<>(Arrays.asList(3, 1, -4, 2));
            s.triplesSumToZero(input);
            assertEquals(Arrays.asList(3, 1, -4, 2), input);
        }

        private void assertEquals(List<Integer> expected, List<Integer> actual) {
            org.junit.jupiter.api.Assertions.assertEquals(expected, actual);
        }
    }
}
