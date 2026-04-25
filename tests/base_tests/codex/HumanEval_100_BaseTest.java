import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_100_BaseTest {

    private final Solution solution = new Solution();

    @Test
    void exampleThree_returnsOddProgression() {
        assertEquals(Arrays.asList(3, 5, 7), solution.makeAPile(3));
    }

    @Test
    void four_returnsEvenProgression() {
        assertEquals(Arrays.asList(4, 6, 8, 10), solution.makeAPile(4));
    }

    @Test
    void five_returnsOddProgression() {
        assertEquals(Arrays.asList(5, 7, 9, 11, 13), solution.makeAPile(5));
    }

    @Test
    void six_returnsEvenProgression() {
        assertEquals(Arrays.asList(6, 8, 10, 12, 14, 16), solution.makeAPile(6));
    }

    @Test
    void eight_returnsEvenProgression() {
        assertEquals(Arrays.asList(8, 10, 12, 14, 16, 18, 20, 22), solution.makeAPile(8));
    }
}
