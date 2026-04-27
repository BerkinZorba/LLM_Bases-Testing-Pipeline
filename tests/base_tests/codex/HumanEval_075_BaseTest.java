import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

public class HumanEval_075_BaseTest {

    private final Solution s = new Solution();

    @Test
    void thirtyIsProduct_ofThreePrimes() {
        assertEquals(true, s.isMultiplyPrime(30));
    }

    @Test
    void twentyTwoIsNot() {
        assertEquals(false, s.isMultiplyPrime(22));
    }

    @Test
    void fourIsNot() {
        assertEquals(false, s.isMultiplyPrime(4));
    }

    @Test
    void eightIsProduct_twoSquaredTimesTwo() {
        assertEquals(true, s.isMultiplyPrime(8));
    }

    @Test
    void singlePrime_isNot() {
        assertEquals(false, s.isMultiplyPrime(3));
    }

    @Test
    void twelveIsProduct_2times2times3() {
        assertEquals(true, s.isMultiplyPrime(12));
    }
}
