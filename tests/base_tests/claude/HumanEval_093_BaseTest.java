import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit 6 port of the HumanEval_093 dataset base harness.
 * Source: tests/base_tests/original/HumanEval_093/Main.java
 * Each Objects.equals assertion in the original Main is one named test here.
 * No assertion logic, inputs, or expected values have been changed.
 */
public class HumanEval_093_BaseTest {

    private final Solution s = new Solution();

    @Test
    void TEST_returnsTgst() {
        assertEquals("tgst", s.encode("TEST"));
    }

    @Test
    void Mudasir_returnsMWDCSKR() {
        assertEquals("mWDCSKR", s.encode("Mudasir"));
    }

    @Test
    void YES_returnsYgs() {
        assertEquals("ygs", s.encode("YES"));
    }

    @Test
    void thisIsAMessage_returnsTHKSKSCMGSSCGG() {
        assertEquals("tHKS KS C MGSSCGG", s.encode("This is a message"));
    }

    @Test
    void iDontKnowWhatToWrite_returnsEncoded() {
        assertEquals("k dQnT kNqW wHcT Tq wRkTg", s.encode("I DoNt KnOw WhAt tO WrItE"));
    }
}
