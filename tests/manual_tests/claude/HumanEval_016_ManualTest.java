import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Manual black-box JUnit 6 suite for HumanEval_016.
 * Cases are derived from tests/manual_tests/claude/HumanEval_016_blackbox.md.
 * Each test method's DisplayName references the equivalence-class or boundary ID
 * from that document so failures map back to the design table.
 */
public class HumanEval_016_ManualTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Valid equivalence classes (V1–V11)")
    class ValidClasses {

        @ParameterizedTest(name = "{0} — \"{1}\" → {2}")
        @CsvSource(value = {
                "V1 empty                | ''                    | 0",
                "V2 single-char          | 'a'                   | 1",
                "V3 distinct lowercase   | 'abcde'               | 5",
                "V4 one letter repeated  | 'aaaaAAAAaaaa'        | 1",
                "V5 mixed-case duplicates| 'abcdecadeCADE'       | 5",
                "V6 letters + spaces     | 'Jerry jERRY JeRRRY'  | 5",
                "V7 digits only          | '0123456789'          | 10",
                "V8 punctuation only     | '!?.,;'               | 5",
                "V9 whitespace only      | '   '                 | 1",
                "V10 mixed everything    | 'Aa Bb 11 22'         | 5",
                "V11 BMP non-ASCII case  | 'ÄäÖöÜü'              | 3"
        }, delimiter = '|')
        void allValidClasses(String classId, String input, int expected) {
            assertEquals(expected, s.countDistinctCharacters(input),
                    () -> classId + " input=\"" + input + "\"");
        }
    }

    @Nested
    @DisplayName("Boundary analysis")
    class Boundaries {

        @Test
        @DisplayName("Just below / on / just above length 1")
        void aroundLengthOne() {
            assertEquals(0, s.countDistinctCharacters(""));
            assertEquals(1, s.countDistinctCharacters("a"));
            assertEquals(2, s.countDistinctCharacters("ab"));
        }

        @Test
        @DisplayName("All-same vs one-different transition")
        void allSameToOneDifferent() {
            assertEquals(1, s.countDistinctCharacters("aaaa"));
            assertEquals(2, s.countDistinctCharacters("aaab"));
            assertEquals(3, s.countDistinctCharacters("aabc"));
        }

        @Test
        @DisplayName("Full ASCII lowercase alphabet — adding an uppercase doesn't grow the set")
        void fullAlphabetSaturation() {
            assertEquals(26, s.countDistinctCharacters("abcdefghijklmnopqrstuvwxyz"));
            assertEquals(26, s.countDistinctCharacters("abcdefghijklmnopqrstuvwxyzA"));
        }

        @Test
        @DisplayName("Full digit alphabet — adding a duplicate digit doesn't grow the set")
        void fullDigitSetSaturation() {
            assertEquals(10, s.countDistinctCharacters("0123456789"));
            assertEquals(10, s.countDistinctCharacters("01234567890"));
        }
    }

    @Nested
    @DisplayName("Special-character / encoding cases")
    class SpecialCharacters {

        @Test
        @DisplayName("Tab and space are distinct characters")
        void tabAndSpaceAreDistinct() {
            // chars: a, \t, b, space  → 4
            assertEquals(4, s.countDistinctCharacters("a\tb a b"));
        }

        @Test
        @DisplayName("Repeated punctuation collapses to one")
        void repeatedPunctuationCollapses() {
            assertEquals(1, s.countDistinctCharacters("!!!"));
        }

        @Test
        @DisplayName("Two distinct supplementary code points → 4 char-level units")
        void twoSurrogatePairs() {
            // 😀 = D83D DE00, 😃 = D83D DE03  → distinct UTF-16 units: D83D, DE00, DE03 = 3
            // Pinning the actual char-level result.
            assertEquals(3, s.countDistinctCharacters("😀😃"));
        }
    }

    @Nested
    @DisplayName("Invalid / undefined-by-spec inputs (pinned behavior)")
    class InvalidOrPinned {

        @Test
        @DisplayName("I1: null input throws NullPointerException")
        void nullInputThrowsNpe() {
            assertThrows(NullPointerException.class,
                    () -> s.countDistinctCharacters(null));
        }

        @Test
        @DisplayName("I2: single emoji (surrogate pair) is counted as 2 chars")
        void surrogatePairIsTwoChars() {
            assertEquals(2, s.countDistinctCharacters("😀"));
        }

        @Test
        @DisplayName("I3: Turkish I family — pin Locale.ROOT toLowerCase semantics")
        void turkishIFamily() {
            // 'İ' decomposes to 'i' + U+0307; 'ı' stays; 'I' folds to 'i'; 'i' stays.
            // Distinct chars after fold: {i, U+0307, ı} = 3.
            assertEquals(3, s.countDistinctCharacters("İIıi"));
        }
    }
}
