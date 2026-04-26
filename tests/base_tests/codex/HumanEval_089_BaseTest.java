import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_089_BaseTest {

    private final Solution s = new Solution();

    @Test
    void promptExampleHi() {
        assertEquals("lm", s.encrypt("hi"));
    }

    @Test
    void promptExampleKeyboardRun() {
        assertEquals("ewhjklnop", s.encrypt("asdfghjkl"));
    }

    @Test
    void promptExampleGf() {
        assertEquals("kj", s.encrypt("gf"));
    }

    @Test
    void promptExampleEt() {
        assertEquals("ix", s.encrypt("et"));
    }

    @Test
    void datasetLongerCaseOne() {
        assertEquals("jeiajeaijeiak", s.encrypt("faewfawefaewg"));
    }

    @Test
    void datasetLongerCaseTwo() {
        assertEquals("lippsqcjvmirh", s.encrypt("hellomyfriend"));
    }

    @Test
    void datasetLongCaseWithWraparound() {
        assertEquals("hbdhpqrmpjylqmpyjlpmlyjrqpmqryjlpmqryjljygyjl",
                s.encrypt("dxzdlmnilfuhmilufhlihufnmlimnufhlimnufhfucufh"));
    }

    @Test
    void singleLetter() {
        assertEquals("e", s.encrypt("a"));
    }
}
