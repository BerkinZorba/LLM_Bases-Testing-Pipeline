import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_098_BaseTest {

    private final Solution s = new Solution();

    @Test
    void docstringExample_aBCdEf_returns1() {
        assertEquals(1, s.countUpper("aBCdEf"));
    }

    @Test
    void allLowercase_returns0() {
        assertEquals(0, s.countUpper("abcdefg"));
    }

    @Test
    void dBBE_returns0() {
        assertEquals(0, s.countUpper("dBBE"));
    }

    @Test
    void allCapsVowels_returnsThreeFromEvenPositions() {
        assertEquals(3, s.countUpper("AAAAA"));
    }

    @Test
    void alternatingVowels_allOnEvenIndices() {
        assertEquals(3, s.countUpper("EcEcE"));
    }
}
