import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_018_BaseTest {

    private final Solution s = new Solution();

    @Test
    void emptySource_returnsZero() {
        assertEquals(0, s.howManyTimes("", "x"));
    }

    @Test
    void countsSingleCharacterOccurrences() {
        assertEquals(4, s.howManyTimes("xyxyxyx", "x"));
    }

    @Test
    void countsOverlappingMultiCharacterOccurrences() {
        assertEquals(4, s.howManyTimes("cacacacac", "cac"));
    }

    @Test
    void countsPrefixWordOccurrence() {
        assertEquals(1, s.howManyTimes("john doe", "john"));
    }
}
