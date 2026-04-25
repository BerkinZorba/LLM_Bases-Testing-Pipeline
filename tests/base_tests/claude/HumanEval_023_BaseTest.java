import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_023_BaseTest {

    private final Solution s = new Solution();

    @Test
    void emptyString_returnsZero() {
        assertEquals(0, s.strlen(""));
    }

    @Test
    void docstringExample_abc() {
        assertEquals(3, s.strlen("abc"));
    }

    @Test
    void singleChar() {
        assertEquals(1, s.strlen("x"));
    }

    @Test
    void stringWithSpaces() {
        assertEquals(11, s.strlen("hello world"));
    }
}
