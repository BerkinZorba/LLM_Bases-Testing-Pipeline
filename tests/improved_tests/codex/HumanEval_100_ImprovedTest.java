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
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HumanEval_100_ImprovedTest {

    private final Solution solution = new Solution();

    private static Stream<Arguments> representativePiles() {
        return Stream.of(
                Arguments.of(1, List.of(1)),
                Arguments.of(2, List.of(2, 4)),
                Arguments.of(3, List.of(3, 5, 7)),
                Arguments.of(7, List.of(7, 9, 11, 13, 15, 17, 19)),
                Arguments.of(10, List.of(10, 12, 14, 16, 18, 20, 22, 24, 26, 28))
        );
    }

    @ParameterizedTest(name = "makeAPile({0}) = {1}")
    @MethodSource("representativePiles")
    @DisplayName("Builds the expected arithmetic progression for representative sizes")
    void buildsExpectedPileForRepresentativeSizes(int n, List<Integer> expected) {
        assertEquals(expected, solution.makeAPile(n));
    }

    @Test
    void preservesOddParityAcrossAllLevels() {
        assertEquals(Arrays.asList(9, 11, 13, 15, 17, 19, 21, 23, 25), solution.makeAPile(9));
    }

    @Test
    void preservesEvenParityAcrossAllLevels() {
        assertEquals(Arrays.asList(12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34), solution.makeAPile(12));
    }

    @Test
    void returnsExactlyNLevels() {
        assertEquals(15, solution.makeAPile(15).size());
    }

    @Test
    void everyAdjacentPairDiffersByTwo() {
        List<Integer> pile = solution.makeAPile(11);
        assertTrue(IntStream.range(1, pile.size()).allMatch(i -> pile.get(i) - pile.get(i - 1) == 2));
    }

    @Test
    void firstLevelAlwaysEqualsInput() {
        assertEquals(13, solution.makeAPile(13).getFirst());
    }

    @Test
    void currentImplementationReturnsEmptyListForZero() {
        assertEquals(Collections.emptyList(), solution.makeAPile(0));
    }

    @Test
    void currentImplementationReturnsEmptyListForNegativeInput() {
        assertEquals(Collections.emptyList(), solution.makeAPile(-4));
    }
}
