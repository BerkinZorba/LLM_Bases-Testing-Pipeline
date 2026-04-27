import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit 6 port of the HumanEval_089 dataset base harness.
 * Source: tests/base_tests/original/HumanEval_089/Main.java
 * Each Objects.equals assertion in the original Main is one named test here.
 * No assertion logic, inputs, or expected values have been changed.
 */
public class HumanEval_089_BaseTest {

    private final Solution s = new Solution();

    @Test
    void hi_returnsLm() {
        assertEquals("lm", s.encrypt("hi"));
    }

    @Test
    void asdfghjkl_returnsEwhjklnop() {
        assertEquals("ewhjklnop", s.encrypt("asdfghjkl"));
    }

    @Test
    void gf_returnsKj() {
        assertEquals("kj", s.encrypt("gf"));
    }

    @Test
    void et_returnsIx() {
        assertEquals("ix", s.encrypt("et"));
    }

    @Test
    void faewfawefaewg_returnsJeiajeaijeiak() {
        assertEquals("jeiajeaijeiak", s.encrypt("faewfawefaewg"));
    }

    @Test
    void hellomyfriend_returnsLippsqcjvmirh() {
        assertEquals("lippsqcjvmirh", s.encrypt("hellomyfriend"));
    }

    @Test
    void longInput_returnsCorrectEncryption() {
        assertEquals("hbdhpqrmpjylqmpyjlpmlyjrqpmqryjlpmqryjljygyjl",
                s.encrypt("dxzdlmnilfuhmilufhlihufnmlimnufhlimnufhfucufh"));
    }

    @Test
    void singleA_returnsE() {
        assertEquals("e", s.encrypt("a"));
    }
}
