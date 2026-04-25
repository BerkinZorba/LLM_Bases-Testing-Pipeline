import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_036_BaseTest {

    private final Solution s = new Solution();

    @Test
    void lessThanFifty_hasNoEligibleSevens() {
        assertEquals(0, s.fizzBuzz(50));
    }

    @Test
    void seventyEight_countsTwoSevensBeforeUpperBound() {
        assertEquals(2, s.fizzBuzz(78));
    }

    @Test
    void seventyNine_addsSeventySeven() {
        assertEquals(3, s.fizzBuzz(79));
    }

    @Test
    void oneHundred_keepsTotalAtThree() {
        assertEquals(3, s.fizzBuzz(100));
    }

    @Test
    void twoHundred_countsSixOccurrences() {
        assertEquals(6, s.fizzBuzz(200));
    }

    @Test
    void fourThousand_matchesDatasetExpectation() {
        assertEquals(192, s.fizzBuzz(4000));
    }

    @Test
    void tenThousand_matchesDatasetExpectation() {
        assertEquals(639, s.fizzBuzz(10000));
    }

    @Test
    void oneHundredThousand_matchesDatasetExpectation() {
        assertEquals(8026, s.fizzBuzz(100000));
    }
}
