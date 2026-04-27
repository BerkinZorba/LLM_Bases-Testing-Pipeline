import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_100_BaseTest {

    private final Solution s = new Solution();

    @Test
    void makeAPile_3_returns_3_5_7() {
        assertEquals(Arrays.asList(3, 5, 7), s.makeAPile(3));
    }

    @Test
    void makeAPile_4_returns_4_6_8_10() {
        assertEquals(Arrays.asList(4, 6, 8, 10), s.makeAPile(4));
    }

    @Test
    void makeAPile_5_returns_5_7_9_11_13() {
        assertEquals(Arrays.asList(5, 7, 9, 11, 13), s.makeAPile(5));
    }

    @Test
    void makeAPile_6_returns_6_8_10_12_14_16() {
        assertEquals(Arrays.asList(6, 8, 10, 12, 14, 16), s.makeAPile(6));
    }

    @Test
    void makeAPile_8_returns_8_10_12_14_16_18_20_22() {
        assertEquals(Arrays.asList(8, 10, 12, 14, 16, 18, 20, 22), s.makeAPile(8));
    }
}
