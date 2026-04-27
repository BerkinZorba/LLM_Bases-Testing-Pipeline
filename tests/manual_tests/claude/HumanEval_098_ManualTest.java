import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class HumanEval_098_ManualTest {

    private final Solution s = new Solution();

    // V1 — empty string
    @Test @DisplayName("V1: '' -> 0")
    void v1_empty() { assertEquals(0, s.countUpper("")); }

    // V2 — no uppercase vowels
    @Test @DisplayName("V2: 'abcdefg' -> 0 (all lowercase)")
    void v2_allLower() { assertEquals(0, s.countUpper("abcdefg")); }

    @Test @DisplayName("V2: 'BcDeFg' -> 0 (uppercase consonants at even)")
    void v2_consonantsAtEven() { assertEquals(0, s.countUpper("BcDeFg")); }

    // V3 — uppercase vowels only at odd indices
    @Test @DisplayName("V3: 'xAxExI' -> 0 (A,E,I at odd indices 1,3,5)")
    void v3_vowelsAtOdd() { assertEquals(0, s.countUpper("xAxExI")); }

    // V4 — uppercase vowel at index 0
    @Test @DisplayName("V4: 'A' -> 1")
    void v4_singleA() { assertEquals(1, s.countUpper("A")); }

    @Test @DisplayName("V4: 'Abc' -> 1 (A at index 0)")
    void v4_aAtZero() { assertEquals(1, s.countUpper("Abc")); }

    // V5 — multiple even-index uppercase vowels
    @Test @DisplayName("V5: 'AAAAA' -> 3 (indices 0,2,4)")
    void v5_fiveA() { assertEquals(3, s.countUpper("AAAAA")); }

    @Test @DisplayName("V5: 'EcEcE' -> 3 (E at indices 0,2,4)")
    void v5_eAlternating() { assertEquals(3, s.countUpper("EcEcE")); }

    // V6 — docstring mix
    @Test @DisplayName("V6: 'aBCdEf' -> 1 (E at index 4)")
    void v6_docstring1() { assertEquals(1, s.countUpper("aBCdEf")); }

    @Test @DisplayName("V6: 'abcdefg' -> 0")
    void v6_docstring2() { assertEquals(0, s.countUpper("abcdefg")); }

    @Test @DisplayName("V6: 'dBBE' -> 0")
    void v6_docstring3() { assertEquals(0, s.countUpper("dBBE")); }

    // V7 — each vowel at index 0
    @Test @DisplayName("V7: each uppercase vowel at index 0 counts")
    void v7_eachVowel() {
        assertEquals(1, s.countUpper("A"));
        assertEquals(1, s.countUpper("E"));
        assertEquals(1, s.countUpper("I"));
        assertEquals(1, s.countUpper("O"));
        assertEquals(1, s.countUpper("U"));
    }

    // V8 — non-vowel uppercase at even index
    @Test @DisplayName("V8: 'Bx' -> 0 (B is not a vowel)")
    void v8_nonVowelAtEven() { assertEquals(0, s.countUpper("Bx")); }
}
