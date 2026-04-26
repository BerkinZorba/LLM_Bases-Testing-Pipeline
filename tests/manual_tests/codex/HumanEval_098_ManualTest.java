import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_098_ManualTest {

    private final Solution sol = new Solution();

    // V1: empty string — loop never entered
    @Test
    void v1_emptyString() {
        assertEquals(0, sol.countUpper(""));
    }

    // V2: no uppercase characters at all
    @Test
    void v2_allLowercase() {
        assertEquals(0, sol.countUpper("hello"));
    }

    // V3: uppercase vowels only at odd indices (1, 3) — step skips them
    @Test
    void v3_vowelsAtOddIndicesOnly() {
        // "bAbE": b@0, A@1, b@2, E@3 — even indices 0,2 hold consonants
        assertEquals(0, sol.countUpper("bAbE"));
    }

    // V4: single uppercase vowel at index 0
    @Test
    void v4_vowelAtIndexZero() {
        assertEquals(1, sol.countUpper("Abc"));
    }

    // V5: three uppercase vowels at even indices 0, 2, 4
    @Test
    void v5_threeVowelsAtEven() {
        // "AaEaIa": A@0, E@2, I@4
        assertEquals(3, sol.countUpper("AaEaIa"));
    }

    // V6: uppercase consonants at even indices — none counted
    @Test
    void v6_consonantsAtEven() {
        // "BcDeFg": B@0, D@2, F@4 — all consonants
        assertEquals(0, sol.countUpper("BcDeFg"));
    }

    // V7: mix of uppercase vowel and consonant at even indices
    @Test
    void v7_mixedVowelAndConsonant() {
        // "AcBcE": A@0, B@2, E@4 — A and E counted, B not
        assertEquals(2, sol.countUpper("AcBcE"));
    }

    // V8: O and U at even positions
    @Test
    void v8_ouAtEven() {
        // "OaUa": O@0, U@2
        assertEquals(2, sol.countUpper("OaUa"));
    }

    // Boundary: single uppercase vowel (index 0 only)
    @Test
    void boundary_singleUpperVowel() {
        assertEquals(1, sol.countUpper("I"));
    }

    // Boundary: single lowercase character
    @Test
    void boundary_singleLower() {
        assertEquals(0, sol.countUpper("a"));
    }
}
