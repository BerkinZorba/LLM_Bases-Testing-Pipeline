import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_099_BaseTest {

    private final Solution s = new Solution();

    @Test
    void wholeNumberStringReturnsSameInteger() {
        assertEquals(10, s.closest_integer("10"));
    }

    @Test
    void positiveHalfRoundsAwayFromZero() {
        assertEquals(15, s.closest_integer("14.5"));
    }

    @Test
    void negativeHalfRoundsAwayFromZero() {
        assertEquals(-16, s.closest_integer("-15.5"));
    }

    @Test
    void positiveDecimalBelowHalfRoundsDown() {
        assertEquals(15, s.closest_integer("15.3"));
    }

    @Test
    void zeroReturnsZero() {
        assertEquals(0, s.closest_integer("0"));
    }
}
