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

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Improved JUnit 6 suite for HumanEval_020 (findClosestElements).
 *
 * Improvements over the dataset harness:
 *   - splits the original assertion roulette list into named, parameterized rows;
 *   - uses explicit (input, expected) pairs instead of inline boolean expressions;
 *   - adds the minimum-list-size boundary (exactly two elements);
 *   - adds duplicate-element pairs (diff = 0), negative numbers, mixed-sign inputs;
 *   - adds closest-pair-at-start and closest-pair-at-end positions;
 *   - adds tie-breaking: when multiple pairs share the minimum diff the first
 *     (lowest-index in sorted order) pair is returned.
 */
public class HumanEval_020_ImprovedTest {

    private final Solution s = new Solution();

    private List<Double> call(Double... values) {
        return s.findClosestElements(new ArrayList<>(Arrays.asList(values)));
    }

    @Nested
    @DisplayName("Dataset cases — one assertion per row")
    class DatasetCases {

        static Stream<Arguments> datasetCases() {
            return Stream.of(
                    Arguments.of("closest near middle (3.9,4.0)",
                            Arrays.asList(1.0, 2.0, 3.9, 4.0, 5.0, 2.2),
                            Arrays.asList(3.9, 4.0)),
                    Arguments.of("closest at end (5.0,5.9)",
                            Arrays.asList(1.0, 2.0, 5.9, 4.0, 5.0),
                            Arrays.asList(5.0, 5.9)),
                    Arguments.of("prompt example (2.0,2.2)",
                            Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.2),
                            Arrays.asList(2.0, 2.2)),
                    Arguments.of("duplicate element (2.0,2.0)",
                            Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.0),
                            Arrays.asList(2.0, 2.0)),
                    Arguments.of("uneven spacing (2.2,3.1)",
                            Arrays.asList(1.1, 2.2, 3.1, 4.1, 5.1),
                            Arrays.asList(2.2, 3.1))
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("datasetCases")
        void datasetExpectations(String label, List<Double> input, List<Double> expected) {
            assertEquals(expected, s.findClosestElements(new ArrayList<>(input)),
                    () -> label);
        }
    }

    @Nested
    @DisplayName("Minimum list size — exactly two elements")
    class TwoElementList {

        @Test
        @DisplayName("Two elements already ordered")
        void twoElements_naturalOrder() {
            assertEquals(Arrays.asList(1.0, 3.0), call(1.0, 3.0));
        }

        @Test
        @DisplayName("Two elements reversed — result still ordered smaller first")
        void twoElements_reversedOrder() {
            assertEquals(Arrays.asList(1.0, 3.0), call(3.0, 1.0));
        }

        @Test
        @DisplayName("Two identical elements — diff zero")
        void twoElements_identical() {
            assertEquals(Arrays.asList(2.0, 2.0), call(2.0, 2.0));
        }
    }

    @Nested
    @DisplayName("Position of closest pair")
    class ClosestPairPosition {

        @Test
        @DisplayName("Closest pair is first pair in sorted order")
        void closestAtStart() {
            assertEquals(Arrays.asList(1.0, 1.1), call(1.0, 1.1, 3.0, 5.0));
        }

        @Test
        @DisplayName("Closest pair is last pair in sorted order")
        void closestAtEnd() {
            assertEquals(Arrays.asList(4.9, 5.0), call(1.0, 3.0, 4.9, 5.0));
        }

        @Test
        @DisplayName("Closest pair is in the middle")
        void closestInMiddle() {
            assertEquals(Arrays.asList(2.0, 2.1), call(1.0, 2.0, 2.1, 4.0, 6.0));
        }
    }

    @Nested
    @DisplayName("Behavioral properties")
    class BehavioralProperties {

        @Test
        @DisplayName("Result contains smaller element first")
        void resultOrderedSmallerFirst() {
            List<Double> result = call(5.0, 3.0, 3.1, 1.0);
            assertEquals(1, result.size() == 2 ? (result.get(0) <= result.get(1) ? 1 : 0) : 0);
            assertEquals(Arrays.asList(3.0, 3.1), result);
        }

        @Test
        @DisplayName("Negative numbers — closest pair identified correctly")
        void negativeNumbers() {
            assertEquals(Arrays.asList(-3.0, -2.9), call(-5.0, -3.0, -2.9, -1.0));
        }

        @Test
        @DisplayName("Mixed positive and negative numbers")
        void mixedSignNumbers() {
            assertEquals(Arrays.asList(-0.1, 0.0), call(-5.0, -0.1, 0.0, 3.0));
        }

        @Test
        @DisplayName("Tie-breaking: first pair in sorted order wins when diffs are equal")
        void tieBraking_firstPairWins() {
            // sorted: [1.0, 2.0, 3.0, 4.0] — all diffs are 1.0; first pair (1.0,2.0) wins
            assertEquals(Arrays.asList(1.0, 2.0), call(1.0, 2.0, 3.0, 4.0));
        }

        @Test
        @DisplayName("Input list is not mutated by the call")
        void inputListNotMutated() {
            List<Double> input = new ArrayList<>(Arrays.asList(3.0, 1.0, 2.0));
            s.findClosestElements(input);
            assertEquals(Arrays.asList(3.0, 1.0, 2.0), input);
        }
    }
}
