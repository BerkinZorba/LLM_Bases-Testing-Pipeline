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
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Codex-authored improved JUnit 6 suite for HumanEval_019.
 *
 * Design goals:
 * - split the dataset's assertion-list harness into attributable checks;
 * - cover all valid numeral words and duplicate values;
 * - pin underspecified null, extra-space, and invalid-token behavior.
 */
public class HumanEval_019_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> datasetCases() {
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of("three", "three"),
                Arguments.of("three five nine", "three five nine"),
                Arguments.of("five zero four seven nine eight", "zero four five seven eight nine")
        );
    }

    static Stream<Arguments> extendedValidCases() {
        return Stream.of(
                Arguments.of("nine eight seven six five four three two one zero",
                        "zero one two three four five six seven eight nine"),
                Arguments.of("two two one", "one two two"),
                Arguments.of("zero zero zero", "zero zero zero"),
                Arguments.of("one zero one zero", "zero zero one one"),
                Arguments.of("six three six three", "three three six six"),
                Arguments.of("eight two five", "two five eight")
        );
    }

    @ParameterizedTest(name = "\"{0}\" -> \"{1}\"")
    @MethodSource("datasetCases")
    @DisplayName("Dataset expectations")
    void datasetExpectations(String input, String expected) {
        assertEquals(expected, solution.sortNumbers(input));
    }

    @ParameterizedTest(name = "\"{0}\" -> \"{1}\"")
    @MethodSource("extendedValidCases")
    @DisplayName("Extended valid cases")
    void extendedValidCases(String input, String expected) {
        assertEquals(expected, solution.sortNumbers(input));
    }

    @Test
    @DisplayName("Null input is pinned to empty string")
    void nullInputReturnsEmptyString() {
        assertEquals("", solution.sortNumbers(null));
    }

    @Test
    @DisplayName("Invalid numeral word throws NullPointerException")
    void invalidWordThrows() {
        assertThrows(NullPointerException.class, () -> solution.sortNumbers("one ten"));
    }

    @Test
    @DisplayName("Extra spaces create an invalid empty token")
    void extraSpacesThrow() {
        assertThrows(NullPointerException.class, () -> solution.sortNumbers("one  two"));
    }
}
