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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Codex-authored executable manual suite derived from the black-box analysis.
 */
public class HumanEval_057_ManualTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> validClasses() {
        return Stream.of(
                Arguments.of("V1", List.of(), true),
                Arguments.of("V2", List.of(4), true),
                Arguments.of("V3", Arrays.asList(1, 2, 3, 4), true),
                Arguments.of("V4", Arrays.asList(9, 7, 5, 1), true),
                Arguments.of("V5", Arrays.asList(2, 2, 2, 2), true),
                Arguments.of("V6", Arrays.asList(1, 1, 2, 2, 3), true),
                Arguments.of("V7", Arrays.asList(5, 4, 4, 3, 1), true),
                Arguments.of("V8", Arrays.asList(Integer.MIN_VALUE, 0, Integer.MAX_VALUE), true)
        );
    }

    static Stream<Arguments> invalidClasses() {
        return Stream.of(
                Arguments.of("I1", Arrays.asList(1, 3, 2), false),
                Arguments.of("I2", Arrays.asList(5, 2, 4), false),
                Arguments.of("I3", Arrays.asList(1, 2, 2, 1), false),
                Arguments.of("I4", Arrays.asList(3, 3, 5, 4), false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("validClasses")
    @DisplayName("Valid equivalence classes")
    void validEquivalenceClasses(String id, List<Integer> input, boolean expected) {
        assertTrue(solution.monotonic(input) == expected);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidClasses")
    @DisplayName("Invalid equivalence classes")
    void invalidEquivalenceClasses(String id, List<Integer> input, boolean expected) {
        assertFalse(solution.monotonic(input) != expected);
    }

    @Test
    @DisplayName("Boundary change from one element to two equal elements stays monotone")
    void singletonToTwoEqualElements() {
        assertTrue(solution.monotonic(Arrays.asList(6, 6)));
    }

    @Test
    @DisplayName("Boundary change from monotone prefix to one violating tail flips result")
    void violatingTailBreaksMonotonicity() {
        assertFalse(solution.monotonic(Arrays.asList(0, 1, 2, 1)));
    }
}
