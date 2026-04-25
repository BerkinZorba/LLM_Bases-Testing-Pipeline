import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_016_BaseTest {

    private final Solution s = new Solution();

    @Test
    void emptyString_returnsZero() {
        assertEquals(0, s.countDistinctCharacters(""));
    }

    @Test
    void allLowercaseDistinct_returnsLength() {
        assertEquals(5, s.countDistinctCharacters("abcde"));
    }

    @Test
    void mixedCaseDuplicatesAcrossSegments_caseInsensitive() {
        assertEquals(5, s.countDistinctCharacters("abcde" + "cade" + "CADE"));
    }

    @Test
    void onlyOneLetterRepeatedInBothCases_returnsOne() {
        assertEquals(1, s.countDistinctCharacters("aaaaAAAAaaaa"));
    }

    @Test
    void mixedCaseWithSpaces_countsSpaceAsACharacter() {
        assertEquals(5, s.countDistinctCharacters("Jerry jERRY JeRRRY"));
    }
}
