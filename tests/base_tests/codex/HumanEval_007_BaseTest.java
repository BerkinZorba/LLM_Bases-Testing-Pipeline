import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_007_BaseTest {

    private final Solution s = new Solution();

    @Test
    void emptyList_returnsEmptyList() {
        assertEquals(List.of(),
                s.filterBySubstring(new ArrayList<>(List.of()), "john"));
    }

    @Test
    void filtersWordsContainingExactSubstring_xxx() {
        assertEquals(Arrays.asList("xxx", "xxxAAA", "xxx"),
                s.filterBySubstring(new ArrayList<>(Arrays.asList("xxx", "asd", "xxy", "john doe", "xxxAAA", "xxx")), "xxx"));
    }

    @Test
    void filtersWordsContainingExactSubstring_xx() {
        assertEquals(Arrays.asList("xxx", "aaaxxy", "xxxAAA", "xxx"),
                s.filterBySubstring(new ArrayList<>(Arrays.asList("xxx", "asd", "aaaxxy", "john doe", "xxxAAA", "xxx")), "xx"));
    }

    @Test
    void filtersMiddleSubstring_run() {
        assertEquals(Arrays.asList("grunt", "prune"),
                s.filterBySubstring(new ArrayList<>(Arrays.asList("grunt", "trumpet", "prune", "gruesome")), "run"));
    }
}
