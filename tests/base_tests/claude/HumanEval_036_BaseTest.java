import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class HumanEval_036_BaseTest {

    private final Solution s = new Solution();

    @Test
    void belowFirstMultiple_returnsZero() {
        assertEquals(0, s.fizzBuzz(50));
    }

    @Test
    void upTo78_countsTwoSevens() {
        assertEquals(2, s.fizzBuzz(78));
    }

    @Test
    void upTo79_includesSeventySeven() {
        assertEquals(3, s.fizzBuzz(79));
    }

    @Test
    void upTo100_matchesDataset() {
        assertEquals(3, s.fizzBuzz(100));
    }

    @Test
    void upTo200_matchesDataset() {
        assertEquals(6, s.fizzBuzz(200));
    }

    @Test
    void upTo4000_matchesDataset() {
        assertEquals(192, s.fizzBuzz(4000));
    }

    @Test
    void upTo10000_matchesDataset() {
        assertEquals(639, s.fizzBuzz(10000));
    }

    @Test
    void upTo100000_matchesDataset() {
        assertEquals(8026, s.fizzBuzz(100000));
    }
}
