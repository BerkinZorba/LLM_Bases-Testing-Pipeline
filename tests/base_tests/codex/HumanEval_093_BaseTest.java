import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_093_BaseTest {

    private final Solution s = new Solution();

    @Test
    void uppercaseWordWithVowelReplacement() {
        assertEquals("tgst", s.encode("TEST"));
    }

    @Test
    void mixedCaseName() {
        assertEquals("mWDCSKR", s.encode("Mudasir"));
    }

    @Test
    void uppercaseVowelsAndConsonant() {
        assertEquals("ygs", s.encode("YES"));
    }

    @Test
    void sentenceWithSpaces() {
        assertEquals("tHKS KS C MGSSCGG", s.encode("This is a message"));
    }

    @Test
    void mixedCaseSentence() {
        assertEquals("k dQnT kNqW wHcT Tq wRkTg", s.encode("I DoNt KnOw WhAt tO WrItE"));
    }
}
