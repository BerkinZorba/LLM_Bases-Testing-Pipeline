import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Codex-authored improved JUnit 6 suite for HumanEval_059.
 *
 * Design goals:
 * - replace the dataset harness's aggregate assertions with attributable cases;
 * - cover repeated factor stripping for both `2` and odd divisors;
 * - exercise the final leftover-prime path after trial division.
 */
public class HumanEval_059_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> representativeCompositeInputs() {
        return Stream.of(
                Arguments.of("dataset 15", 15, 5),
                Arguments.of("dataset 27", 27, 3),
                Arguments.of("dataset 63", 63, 7),
                Arguments.of("dataset 330", 330, 11),
                Arguments.of("dataset 13195", 13195, 29),
                Arguments.of("power of two", 2048, 2),
                Arguments.of("repeated odd prime", 81, 3),
                Arguments.of("perfect square of odd prime", 49, 7),
                Arguments.of("mixed factors ending in larger prime", 84, 7),
                Arguments.of("semi-prime with large tail factor", 299, 23)
        );
    }

    @ParameterizedTest(name = "{0} -> {2}")
    @MethodSource("representativeCompositeInputs")
    @DisplayName("Dataset expectations and branch-focused composite cases")
    void representativeCompositeCases(String label, int input, int expected) {
        assertEquals(expected, solution.largestPrimeFactor(input), label);
    }

    @Test
    @DisplayName("Removing repeated factor two leaves an odd prime tail")
    void repeatedFactorTwoLeavesOddPrimeTail() {
        assertEquals(5, solution.largestPrimeFactor(80));
    }

    @Test
    @DisplayName("An odd composite can reduce to a final prime larger than the current divisor")
    void oddCompositeCanLeaveLargePrimeTail() {
        assertEquals(17, solution.largestPrimeFactor(255));
    }

    @Test
    @DisplayName("Composite with multiple odd factors returns the largest one")
    void multipleOddFactorsReturnLargestPrimeFactor() {
        assertEquals(13, solution.largestPrimeFactor(1287));
    }
}
