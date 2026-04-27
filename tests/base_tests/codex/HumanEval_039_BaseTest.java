import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class HumanEval_039_BaseTest {

    private final Solution s = new Solution();

    @Test
    void first_is2() {
        assertEquals(2, s.primeFib(1));
    }

    @Test
    void second_is3() {
        assertEquals(3, s.primeFib(2));
    }

    @Test
    void third_is5() {
        assertEquals(5, s.primeFib(3));
    }

    @Test
    void fourth_is13() {
        assertEquals(13, s.primeFib(4));
    }

    @Test
    void fifth_is89() {
        assertEquals(89, s.primeFib(5));
    }
}
