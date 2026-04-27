import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_122_ImprovedTest {

    private final Solution solution = new Solution();

    private static Stream<Arguments> representativePrefixes() {
        return Stream.of(
                Arguments.of(Arrays.asList(1), 1, 1),
                Arguments.of(Arrays.asList(9, 10, 99, 100), 3, 118),
                Arguments.of(Arrays.asList(111, 21, 3, 4000, 5), 4, 24),
                Arguments.of(Arrays.asList(-9, -10, -99, 1234), 3, -118),
                Arguments.of(Arrays.asList(100, 1000, 7, 8), 2, 0)
        );
    }

    @ParameterizedTest(name = "addElements({0}, {1}) = {2}")
    @MethodSource("representativePrefixes")
    @DisplayName("Sums only values with at most two digits from the first k elements")
    void sumsOnlyEligibleValuesFromThePrefix(List<Integer> arr, int k, int expected) {
        assertEquals(expected, solution.addElements(arr, k));
    }

    @Test
    void ignoresEligibleValuesBeyondThePrefixBoundary() {
        assertEquals(0, solution.addElements(Arrays.asList(1000, 2000, 7, 8, 9), 2));
    }

    @Test
    void includesSingleDigitNegativeNumbersUsingAbsoluteDigitCount() {
        assertEquals(-6, solution.addElements(Arrays.asList(-1, -2, -3, 1000), 3));
    }

    @Test
    void includesExactlyTwoDigitNegativeNumbers() {
        assertEquals(-30, solution.addElements(Arrays.asList(-10, -20, 300, 4), 3));
    }

    @Test
    void excludesThreeDigitNegativeNumbers() {
        assertEquals(5, solution.addElements(Arrays.asList(-100, -999, 5, 6), 3));
    }

    @Test
    void kEqualToArrayLengthConsumesTheWholeList() {
        assertEquals(51, solution.addElements(Arrays.asList(12, 123, -4, -56, 700, 99, -100), 7));
    }
}
