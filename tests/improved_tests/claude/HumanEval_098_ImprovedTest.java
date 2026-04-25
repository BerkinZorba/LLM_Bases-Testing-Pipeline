import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Improved suite for HumanEval_098 (countUpper).
 *
 * Test-smell improvements:
 *   - Assertion roulette: each case is a separate @Test or parameterized row.
 *   - Magic numbers: display names describe the index/vowel relationship.
 *   - Eager test: cases partitioned by concern (empty, no vowels, vowels at odd indices,
 *     vowels at even indices, non-vowel uppercase, mixed).
 *
 * Branch-coverage targets in Solution.countUpper:
 *   - Loop guard: empty string (loop never entered) vs non-empty.
 *   - Loop step i+=2: visits indices 0,2,4,...
 *   - Vowel check: character is in "AEIOU" (count++) vs not.
 *   - Each vowel position: index 0, 2, 4 (even) vs 1, 3, 5 (odd, not visited).
 */
public class HumanEval_098_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Empty string")
    class EmptyString {
        @Test @DisplayName("'' -> 0")
        void empty() { assertEquals(0, s.countUpper("")); }
    }

    @Nested
    @DisplayName("No uppercase vowels at even indices -> 0")
    class NoUpperVowels {
        @Test @DisplayName("All lowercase -> 0")
        void allLower() { assertEquals(0, s.countUpper("abcdefg")); }

        @Test @DisplayName("Uppercase vowels only at odd indices -> 0")
        void vowelsAtOddOnly() { assertEquals(0, s.countUpper("xAxExI")); }

        @Test @DisplayName("Uppercase consonants at even indices -> 0")
        void consonantsAtEven() { assertEquals(0, s.countUpper("BcDeFg")); }

        @Test @DisplayName("dBBE: even indices are 'd','B' -> 0")
        void dBBE() { assertEquals(0, s.countUpper("dBBE")); }
    }

    @Nested
    @DisplayName("Uppercase vowels at even indices -> counted")
    class UpperVowelsAtEven {
        @Test @DisplayName("Docstring example 'aBCdEf' -> 1 (only E at index 4)")
        void docstringExample() { assertEquals(1, s.countUpper("aBCdEf")); }

        @Test @DisplayName("'AAAAA' -> 3 (A at indices 0,2,4)")
        void allCapsA() { assertEquals(3, s.countUpper("AAAAA")); }

        @Test @DisplayName("'EcEcE' -> 3 (E at indices 0,2,4)")
        void alternatingE() { assertEquals(3, s.countUpper("EcEcE")); }

        @Test @DisplayName("'A' -> 1 (single char at index 0)")
        void singleUpperVowel() { assertEquals(1, s.countUpper("A")); }
    }

    @Nested
    @DisplayName("Each vowel at index 0 contributes 1")
    class EachVowelAtIndex0 {
        @ParameterizedTest(name = "countUpper({0}) == 1")
        @CsvSource({"A", "E", "I", "O", "U"})
        void vowelAtZero(String v) { assertEquals(1, s.countUpper(v)); }
    }

    @Nested
    @DisplayName("Non-vowel uppercase letters at even indices do not count")
    class NonVowelUppercase {
        @ParameterizedTest(name = "countUpper({0}x) == 0")
        @CsvSource({"Bx", "Cx", "Dx", "Fx", "Gx"})
        void consonantAtEven(String v) { assertEquals(0, s.countUpper(v)); }
    }
}
