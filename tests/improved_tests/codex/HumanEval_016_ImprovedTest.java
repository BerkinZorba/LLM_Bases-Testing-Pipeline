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
 * Codex-authored improved JUnit 6 suite for HumanEval_016.
 *
 * Design goals:
 * - remove the dataset harness' assertion-roulette structure;
 * - keep dataset expectations explicit and individually attributable;
 * - add representative unseen inputs across whitespace, symbols, digits,
 *   repeated invocation, and Unicode case-folding boundaries.
 */
public class HumanEval_016_ImprovedTest {

    private final Solution solution = new Solution();

    static Stream<Arguments> datasetCases() {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of("abcde", 5),
                Arguments.of("abcde" + "cade" + "CADE", 5),
                Arguments.of("aaaaAAAAaaaa", 1),
                Arguments.of("Jerry jERRY JeRRRY", 5)
        );
    }

    static Stream<Arguments> extendedCases() {
        return Stream.of(
                Arguments.of("A", 1),
                Arguments.of("AaAaBb", 2),
                Arguments.of("0123401234", 5),
                Arguments.of("!? !?", 3),
                Arguments.of("Line\nline", 5),
                Arguments.of("ÄäÖöÜü", 3),
                Arguments.of("Java 17", 6),
                Arguments.of("😀", 2),
                Arguments.of("İIıi", 3)
        );
    }

    @ParameterizedTest(name = "dataset case \"{0}\" -> {1}")
    @MethodSource("datasetCases")
    @DisplayName("Dataset expectations")
    void datasetExpectations(String input, int expected) {
        assertEquals(expected, solution.countDistinctCharacters(input));
    }

    @ParameterizedTest(name = "extended case \"{0}\" -> {1}")
    @MethodSource("extendedCases")
    @DisplayName("Extended coverage cases")
    void extendedCoverageCases(String input, int expected) {
        assertEquals(expected, solution.countDistinctCharacters(input));
    }

    @Test
    @DisplayName("Repeated calls return the same result")
    void repeatedCallsAreStable() {
        String input = "MiXeD Case 123";
        int expected = solution.countDistinctCharacters(input);

        assertEquals(expected, solution.countDistinctCharacters(input));
        assertEquals(expected, solution.countDistinctCharacters(input));
    }

    @Test
    @DisplayName("Whitespace variants remain distinct")
    void whitespaceVariantsRemainDistinct() {
        assertEquals(4, solution.countDistinctCharacters("a\t a\n"));
    }

    @Test
    @DisplayName("Alphabet saturation does not grow with uppercase duplicate")
    void alphabetSaturation() {
        assertEquals(26, solution.countDistinctCharacters("abcdefghijklmnopqrstuvwxyz"));
        assertEquals(26, solution.countDistinctCharacters("abcdefghijklmnopqrstuvwxyzA"));
    }
}
