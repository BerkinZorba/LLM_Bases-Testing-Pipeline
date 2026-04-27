import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class HumanEval_108_BaseTest {

    private final Solution s = new Solution();

    @Test
    void emptyList_returnsZero() {
        assertEquals(0, s.countNums(Arrays.asList()));
    }

    @Test
    void docstringExample_neg1_pos11_neg11_returns1() {
        assertEquals(1, s.countNums(Arrays.asList(-1, 11, -11)));
    }

    @Test
    void docstringExample_threePositives_returnsAll() {
        assertEquals(3, s.countNums(Arrays.asList(1, 1, 2)));
    }

    @Test
    void neg123_signedSumPositive_counted() {
        assertEquals(1, s.countNums(Arrays.asList(-123)));
    }

    @Test
    void zero_notCounted() {
        assertEquals(0, s.countNums(Arrays.asList(0)));
    }
}
