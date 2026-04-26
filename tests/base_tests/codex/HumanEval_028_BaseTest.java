import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class HumanEval_028_BaseTest {

    private final Solution s = new Solution();

    @Test
    void emptyList_returnsEmptyString() {
        assertEquals("", s.concatenate(List.of()));
    }

    @Test
    void docstringExample_abc() {
        assertEquals("abc", s.concatenate(Arrays.asList("a", "b", "c")));
    }

    @Test
    void singleElement() {
        assertEquals("x", s.concatenate(Arrays.asList("x")));
    }

    @Test
    void stringsWithSpaces() {
        assertEquals("hello world", s.concatenate(Arrays.asList("hello", " ", "world")));
    }
}
