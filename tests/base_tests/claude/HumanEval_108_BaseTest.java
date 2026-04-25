import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

public class HumanEval_108_BaseTest {

    private final Solution s = new Solution();

    @Test
    void emptyList_returnsZero() {
        assertEquals(0, s.countNums(Arrays.asList()));
    }

    @Test
    void docstringExample_negativeOnePlusEleven_minus11() {
        assertEquals(1, s.countNums(Arrays.asList(-1, 11, -11)));
    }

    @Test
    void docstringExample_positiveNumbers_all() {
        assertEquals(3, s.countNums(Arrays.asList(1, 1, 2)));
    }

    @Test
    void negativeWithPositiveDigitSum() {
        assertEquals(1, s.countNums(Arrays.asList(-123)));
    }

    @Test
    void zero_doesNotCount() {
        assertEquals(0, s.countNums(Arrays.asList(0)));
    }
}
