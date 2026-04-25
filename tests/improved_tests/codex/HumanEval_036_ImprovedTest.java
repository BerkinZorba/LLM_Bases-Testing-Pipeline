import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Codex-authored improved JUnit 6 suite for HumanEval_036.
 *
 * Design goals:
 * - replace the dataset harness' assertion roulette with attributable cases;
 * - keep dataset expectations visible while adding branch-targeted checks;
 * - cover underspecified lower-bound behavior without changing production code.
 */
public class HumanEval_036_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> datasetCases() {
        return Stream.of(
                Arguments.of(50, 0),
                Arguments.of(78, 2),
                Arguments.of(79, 3),
                Arguments.of(100, 3),
                Arguments.of(200, 6),
                Arguments.of(4000, 192),
                Arguments.of(10000, 639),
                Arguments.of(100000, 8026)
        );
    }

    static Stream<Arguments> boundaryAndBranchCases() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, 0),
                Arguments.of(11, 0),
                Arguments.of(12, 0),
                Arguments.of(13, 0),
                Arguments.of(14, 0),
                Arguments.of(77, 0),
                Arguments.of(88, 3),
                Arguments.of(143, 4),
                Arguments.of(144, 4),
                Arguments.of(177, 5),
                Arguments.of(178, 5),
                Arguments.of(1000, 47)
        );
    }

    @ParameterizedTest(name = "dataset n={0} -> {1}")
    @MethodSource("datasetCases")
    @DisplayName("Dataset expectations")
    void datasetExpectations(int n, int expected) {
        assertEquals(expected, solution.fizzBuzz(n));
    }

    @ParameterizedTest(name = "branch case n={0} -> {1}")
    @MethodSource("boundaryAndBranchCases")
    @DisplayName("Boundary and branch-focused cases")
    void boundaryAndBranchFocusedCases(int n, int expected) {
        assertEquals(expected, solution.fizzBuzz(n));
    }

    @Test
    @DisplayName("Crossing 79 adds the single seven from 78")
    void crossingSeventyNineAddsOneOccurrence() {
        assertEquals(1, solution.fizzBuzz(79) - solution.fizzBuzz(78));
    }

    @Test
    @DisplayName("Crossing 78 includes 77 with two sevens")
    void crossingSeventyEightAddsTwoOccurrences() {
        assertEquals(2, solution.fizzBuzz(78) - solution.fizzBuzz(77));
    }

    @Test
    @DisplayName("Crossing 177 includes 176 exactly once")
    void crossingOneSeventySevenAddsOneOccurrence() {
        assertEquals(1, solution.fizzBuzz(177) - solution.fizzBuzz(176));
    }

    @Test
    @DisplayName("Divisible by both 11 and 13 is not double-counted")
    void overlapBetweenDivisorsIsHandledByUnion() {
        assertEquals(0, solution.fizzBuzz(144) - solution.fizzBuzz(143));
    }
}
