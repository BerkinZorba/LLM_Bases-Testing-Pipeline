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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Manual black-box JUnit 6 suite for HumanEval_040.
 * Tested against generated_code/claude/HumanEval_040_v2.java.
 * Cases are derived from tests/manual_tests/claude/HumanEval_040_blackbox.md.
 */
public class HumanEval_040_ManualTest {

    private final Solution s = new Solution();

    private boolean call(Integer... values) {
        return s.triplesSumToZero(new ArrayList<>(Arrays.asList(values)));
    }

    @Nested
    @DisplayName("Valid equivalence classes (V1-V15)")
    class ValidClasses {

        static Stream<Arguments> falseClasses() {
            return Stream.of(
                    Arguments.of("V1  empty list",                   new ArrayList<Integer>()),
                    Arguments.of("V2  one element",                  new ArrayList<>(Arrays.asList(5))),
                    Arguments.of("V3  two elements",                 new ArrayList<>(Arrays.asList(0, 0))),
                    Arguments.of("V5  three elems no zero-sum",      new ArrayList<>(Arrays.asList(1, 2, 4))),
                    Arguments.of("V7  all positive",                 new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5))),
                    Arguments.of("V10 mixed — no triple",            new ArrayList<>(Arrays.asList(1, 3, 5, 0))),
                    Arguments.of("V13 large magnitude no triple",    new ArrayList<>(Arrays.asList(1, 3, 5, -100)))
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("falseClasses")
        void expectFalse(String classId, List<Integer> input) {
            assertFalse(s.triplesSumToZero(input), classId);
        }

        static Stream<Arguments> trueClasses() {
            return Stream.of(
                    Arguments.of("V4  three elems summing to zero",  new ArrayList<>(Arrays.asList(-3, 1, 2))),
                    Arguments.of("V6  three zeros",                  new ArrayList<>(Arrays.asList(0, 0, 0))),
                    Arguments.of("V8  all-neg with mixed",           new ArrayList<>(Arrays.asList(-3, -2, -1, 5, 6))),
                    Arguments.of("V9  mixed sign — triple exists",   new ArrayList<>(Arrays.asList(1, 3, -2, 1))),
                    Arguments.of("V11 duplicate vals form triple",   new ArrayList<>(Arrays.asList(-2, 1, 1, 5))),
                    Arguments.of("V12 triple spans min and max",     new ArrayList<>(Arrays.asList(-10, 3, 7, 100))),
                    Arguments.of("V14 multiple triples",             new ArrayList<>(Arrays.asList(-3, -1, 0, 1, 2, 3))),
                    Arguments.of("V15 neg + two positives",         new ArrayList<>(Arrays.asList(2, 4, -5, 3, 9, 7)))
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("trueClasses")
        void expectTrue(String classId, List<Integer> input) {
            assertTrue(s.triplesSumToZero(input), classId);
        }
    }

    @Nested
    @DisplayName("Boundary analysis")
    class Boundaries {

        @Test
        @DisplayName("Size boundary: 2 → 3 → 4 elements")
        void sizeBoundary() {
            assertFalse(call(0, 0));
            assertTrue(call(0, 0, 0));
            assertTrue(call(-1, 0, 1, 99));
        }

        @Test
        @DisplayName("Sum boundary: sum just below zero, exactly zero, just above zero")
        void sumBoundary() {
            assertFalse(call(-1, 1, 2));   // best sum = -1+1+2 = 2; no zero-sum triple
            assertTrue(call(-3, 1, 2));    // -3+1+2 = 0
            assertFalse(call(1, 2, 3));    // all positive, min sum = 6
        }

        @Test
        @DisplayName("Triple at first three sorted positions")
        void tripleAtSortedStart() {
            assertTrue(call(-3, 1, 2, 100, 200));
        }

        @Test
        @DisplayName("Triple at last three sorted positions")
        void tripleAtSortedEnd() {
            assertTrue(call(-200, -100, -3, 1, 2));
        }

        @Test
        @DisplayName("Two-pointer: left and right converge without finding a triple")
        void twoPointerConvergesNoMatch() {
            assertFalse(call(1, 2, 3, 4));
        }
    }

    @Nested
    @DisplayName("Invalid / undefined-by-spec inputs (pinned behavior)")
    class InvalidOrPinned {

        @Test
        @DisplayName("I1: null list throws NullPointerException")
        void nullListThrowsNpe() {
            assertThrows(NullPointerException.class,
                    () -> s.triplesSumToZero(null));
        }
    }
}
