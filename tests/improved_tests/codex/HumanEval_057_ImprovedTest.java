import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Codex-authored improved JUnit 6 suite for HumanEval_057.
 *
 * Design goals:
 * - replace the dataset harness' assertion roulette with attributable cases;
 * - isolate non-decreasing, non-increasing, and mixed-direction behaviors;
 * - cover both inner comparisons and the final OR outcome.
 */
public class HumanEval_057_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> monotonicCases() {
        return Stream.of(
                Arguments.of("dataset increasing", Arrays.asList(1, 2, 4, 10), true),
                Arguments.of("dataset increasing larger tail", Arrays.asList(1, 2, 4, 20), true),
                Arguments.of("dataset mixed spike", Arrays.asList(1, 20, 4, 10), false),
                Arguments.of("dataset decreasing", Arrays.asList(4, 1, 0, -10), true),
                Arguments.of("dataset decreasing with plateau", Arrays.asList(4, 1, 1, 0), true),
                Arguments.of("dataset mixed dip", Arrays.asList(1, 2, 3, 2, 5, 60), false),
                Arguments.of("dataset increasing long", Arrays.asList(1, 2, 3, 4, 5, 60), true),
                Arguments.of("dataset all equal", Arrays.asList(9, 9, 9, 9), true),
                Arguments.of("empty list", List.of(), true),
                Arguments.of("single item", List.of(7), true),
                Arguments.of("plateau then rise", Arrays.asList(2, 2, 2, 3, 3), true),
                Arguments.of("plateau then drop", Arrays.asList(8, 8, 5, 5, 1), true),
                Arguments.of("rise then plateau then drop", Arrays.asList(1, 3, 3, 2), false),
                Arguments.of("drop then plateau then rise", Arrays.asList(5, 3, 3, 4), false)
        );
    }

    @ParameterizedTest(name = "{0} -> {2}")
    @MethodSource("monotonicCases")
    @DisplayName("Dataset expectations and branch-focused cases")
    void datasetAndBranchCases(String label, List<Integer> input, boolean expected) {
        if (expected) {
            assertTrue(solution.monotonic(input), label);
        } else {
            assertFalse(solution.monotonic(input), label);
        }
    }

    @Test
    @DisplayName("A late increase invalidates previously non-increasing data")
    void lateIncreaseInvalidatesNonIncreasingDirection() {
        assertFalse(solution.monotonic(Arrays.asList(9, 7, 7, 8)));
    }

    @Test
    @DisplayName("A late decrease invalidates previously non-decreasing data")
    void lateDecreaseInvalidatesNonDecreasingDirection() {
        assertFalse(solution.monotonic(Arrays.asList(1, 4, 4, 2)));
    }

    @Test
    @DisplayName("Strict increase keeps only the non-decreasing flag alive")
    void strictIncreaseRemainsMonotonic() {
        assertTrue(solution.monotonic(Arrays.asList(-3, -1, 0, 2, 9)));
    }

    @Test
    @DisplayName("Strict decrease keeps only the non-increasing flag alive")
    void strictDecreaseRemainsMonotonic() {
        assertTrue(solution.monotonic(Arrays.asList(12, 5, 0, -4)));
    }
}
