import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_106_BaseTest {

    private final Solution s = new Solution();

    @Test
    void promptExampleN5() {
        assertEquals(Arrays.asList(1, 2, 6, 24, 15), s.f(5));
    }

    @Test
    void datasetExampleN7() {
        assertEquals(Arrays.asList(1, 2, 6, 24, 15, 720, 28), s.f(7));
    }

    @Test
    void n1ReturnsFirstOddSum() {
        assertEquals(List.of(1), s.f(1));
    }

    @Test
    void n3ReturnsOddEvenOddPattern() {
        assertEquals(Arrays.asList(1, 2, 6), s.f(3));
    }
}
