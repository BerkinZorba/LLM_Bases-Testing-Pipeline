import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class HumanEval_002_BaseTest {

    private final Solution s = new Solution();

    @Test
    void docstringExample_threePointFive() {
        assertEquals(0.5, s.truncateNumber(3.5), 1e-9);
    }

    @Test
    void onePointThreeThree() {
        assertEquals(0.33, s.truncateNumber(1.33), 1e-9);
    }

    @Test
    void largeNumber_fracPart() {
        assertEquals(0.456, s.truncateNumber(123.456), 1e-9);
    }

    @Test
    void exactInteger_returnsZero() {
        assertEquals(0.0, s.truncateNumber(1.0), 1e-9);
    }

    @Test
    void lessThanOne_returnsSelf() {
        assertEquals(0.5, s.truncateNumber(0.5), 1e-9);
    }
}
