import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class HumanEval_090_BaseTest {

    private final Solution s = new Solution();

    @Test
    void ascendingList_secondSmallestIs2() {
        assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(1, 2, 3, 4, 5)));
    }

    @Test
    void unsortedList_secondSmallestIs2() {
        assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(5, 1, 4, 3, 2)));
    }

    @Test
    void emptyList_returnsEmpty() {
        assertEquals(Optional.empty(), s.nextSmallest(Arrays.asList()));
    }

    @Test
    void allSame_returnsEmpty() {
        assertEquals(Optional.empty(), s.nextSmallest(Arrays.asList(1, 1)));
    }

    @Test
    void duplicatesWithTwoDistinct_returnsSecond() {
        assertEquals(Optional.of(1), s.nextSmallest(Arrays.asList(1, 1, 1, 1, 0)));
    }
}
