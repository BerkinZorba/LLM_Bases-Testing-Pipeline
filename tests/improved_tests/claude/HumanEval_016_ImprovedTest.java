import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Improved JUnit 6 suite for HumanEval_016 (countDistinctCharacters).
 *
 * Improvements over the dataset base suite:
 *   - splits "assertion roulette" cases into named, parameterized rows so that
 *     a single failure points at exactly one input rather than a list of booleans;
 *   - replaces magic numbers in assertions with explicit (input, expected) pairs;
 *   - adds branch / behavioral coverage that the base suite never exercises:
 *       * idempotency on repeat invocation (no hidden state),
 *       * digit-only and punctuation-only inputs (case folding must not mangle them),
 *       * mixed letters + digits + whitespace,
 *       * non-ASCII letters with case (Latin-1 supplement),
 *       * Turkish dotted/dotless 'I' — case folding edge case where naive
 *         toLowerCase() may diverge from a "regardless of case" reading,
 *       * surrogate pair (one code point split across two UTF-16 chars):
 *         documents the implementation's char-level (not code-point-level) behavior.
 */
public class HumanEval_016_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Dataset cases — one assertion per row")
    class DatasetCases {

        @ParameterizedTest(name = "[{index}] \"{0}\" → {1}")
        @CsvSource(value = {
                "''               | 0",
                "'abcde'          | 5",
                "'abcdecadeCADE'  | 5",
                "'aaaaAAAAaaaa'   | 1",
                "'Jerry jERRY JeRRRY' | 5"
        }, delimiter = '|')
        void datasetExpectations(String input, int expected) {
            assertEquals(expected, s.countDistinctCharacters(input),
                    () -> "input=\"" + input + "\"");
        }
    }

    @Nested
    @DisplayName("Behavioral properties beyond the base suite")
    class BehavioralProperties {

        @Test
        @DisplayName("Idempotent on repeated calls (no hidden state)")
        void idempotentOnRepeatedCalls() {
            String input = "Jerry jERRY JeRRRY";
            int first = s.countDistinctCharacters(input);
            int second = s.countDistinctCharacters(input);
            int third = s.countDistinctCharacters(input);
            assertAll(
                    () -> assertEquals(first, second, "2nd call diverged from 1st"),
                    () -> assertEquals(second, third, "3rd call diverged from 2nd")
            );
        }

        @ParameterizedTest(name = "single-character input \"{0}\" → 1")
        @ValueSource(strings = {"a", "Z", "0", " ", "!", "ä"})
        void singleCharacterInputs(String input) {
            assertEquals(1, s.countDistinctCharacters(input),
                    () -> "input=\"" + input + "\"");
        }

        @Test
        @DisplayName("Digits only — case folding must be a no-op for digits")
        void digitsOnly() {
            assertEquals(10, s.countDistinctCharacters("0123456789"),
                    "expected 10 distinct digits");
        }

        @Test
        @DisplayName("Punctuation only — non-letter characters count")
        void punctuationOnly() {
            assertEquals(5, s.countDistinctCharacters("!?.,;"));
        }

        @Test
        @DisplayName("Letters + digits + whitespace mixed")
        void lettersDigitsWhitespaceMixed() {
            // distinct (case-insensitive): a b 1 2 ' ' = 5
            assertEquals(5, s.countDistinctCharacters("Aa Bb 11 22"));
        }
    }

    @Nested
    @DisplayName("Case-folding edge cases")
    class CaseFolding {

        @Test
        @DisplayName("Latin-1 supplement letters with case")
        void latin1SupplementCaseFolding() {
            // distinct (case-insensitive): ä, ö, ü = 3
            assertEquals(3, s.countDistinctCharacters("ÄäÖöÜü"));
        }

        @Test
        @DisplayName("Turkish dotted/dotless I — documents Locale.ROOT toLowerCase() behavior")
        void turkishDottedDotlessI() {
            // Under default toLowerCase() (locale-insensitive root mapping):
            //   'I' (U+0049) -> 'i' (U+0069)
            //   'İ' (U+0130) -> 'i' + combining dot above (U+0307)  [two chars]
            //   'ı' (U+0131) stays 'ı'
            //   'i' (U+0069) stays 'i'
            // Resulting char set: {i, U+0307, ı} = 3.
            // The dataset spec says "regardless of case", so this case is intentionally
            // pinned to document what the current implementation produces; if a future
            // refactor switches to Locale.ROOT vs Locale("tr") this test will surface it.
            assertEquals(3, s.countDistinctCharacters("İIıi"));
        }

        @Test
        @DisplayName("Surrogate pair — implementation operates on UTF-16 code units, not code points")
        void surrogatePairIsTwoChars() {
            // U+1F600 GRINNING FACE = high surrogate D83D + low surrogate DE00.
            // The current char-based implementation sees 2 distinct chars, not 1 code point.
            String grinningFace = "😀";
            assertEquals(2, s.countDistinctCharacters(grinningFace),
                    "char-level counting; pinning current behavior so a code-point switch is visible");
        }
    }
}
