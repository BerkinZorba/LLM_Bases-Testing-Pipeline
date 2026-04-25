import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Codex-authored executable manual suite derived from the black-box analysis.
 */
public class HumanEval_019_ManualTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> validClasses() {
        return Stream.of(
                Arguments.of("V1", "", ""),
                Arguments.of("V2", "three", "three"),
                Arguments.of("V3", "three five nine", "three five nine"),
                Arguments.of("V4", "nine eight seven six five four three two one zero",
                        "zero one two three four five six seven eight nine"),
                Arguments.of("V5", "five zero four seven nine eight", "zero four five seven eight nine"),
                Arguments.of("V6", "two two one", "one two two"),
                Arguments.of("V7", "zero zero zero", "zero zero zero"),
                Arguments.of("V8", "one zero one zero", "zero zero one one")
        );
    }

    @ParameterizedTest(name = "{0}: \"{1}\" -> \"{2}\"")
    @MethodSource("validClasses")
    @DisplayName("Valid equivalence classes")
    void validEquivalenceClasses(String id, String input, String expected) {
        assertEquals(expected, solution.sortNumbers(input));
    }

    @Test
    @DisplayName("Boundary: one token")
    void oneTokenBoundary() {
        assertEquals("zero", solution.sortNumbers("zero"));
    }

    @Test
    @DisplayName("Boundary: two tokens needing swap")
    void twoTokensNeedingSwapBoundary() {
        assertEquals("zero one", solution.sortNumbers("one zero"));
    }

    @Test
    @DisplayName("Boundary: lowest and highest value")
    void lowestAndHighestBoundary() {
        assertEquals("zero nine", solution.sortNumbers("nine zero"));
    }

    @Test
    @DisplayName("Null input is pinned to empty string")
    void nullInputReturnsEmptyString() {
        assertEquals("", solution.sortNumbers(null));
    }

    @Test
    @DisplayName("Invalid token throws NullPointerException")
    void invalidTokenThrows() {
        assertThrows(NullPointerException.class, () -> solution.sortNumbers("one ten"));
    }

    @Test
    @DisplayName("Leading space creates invalid empty token")
    void leadingSpaceThrows() {
        assertThrows(NullPointerException.class, () -> solution.sortNumbers(" one"));
    }

    @Test
    @DisplayName("Single invalid token is returned unchanged")
    void singleInvalidTokenReturnsUnchanged() {
        assertEquals("One", solution.sortNumbers("One"));
    }

    @Test
    @DisplayName("Different case token requiring comparison throws NullPointerException")
    void differentCaseWithComparisonThrows() {
        assertThrows(NullPointerException.class, () -> solution.sortNumbers("One zero"));
    }
}
