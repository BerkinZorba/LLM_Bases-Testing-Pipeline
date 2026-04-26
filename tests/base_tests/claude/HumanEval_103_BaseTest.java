import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_103_BaseTest {

    private final Solution s = new Solution();

    @Test
    void roundedAvg_1_5_returns_11() {
        assertEquals("11", s.roundedAvg(1, 5));
    }

    @Test
    void roundedAvg_7_13_returns_1010() {
        assertEquals("1010", s.roundedAvg(7, 13));
    }

    @Test
    void roundedAvg_964_977_returns_1111001011() {
        assertEquals("1111001011", s.roundedAvg(964, 977));
    }

    @Test
    void roundedAvg_996_997_returns_1111100101() {
        assertEquals("1111100101", s.roundedAvg(996, 997));
    }

    @Test
    void roundedAvg_560_851_returns_1011000010() {
        assertEquals("1011000010", s.roundedAvg(560, 851));
    }

    @Test
    void roundedAvg_185_546_returns_101101110() {
        assertEquals("101101110", s.roundedAvg(185, 546));
    }

    @Test
    void roundedAvg_362_496_returns_110101101() {
        assertEquals("110101101", s.roundedAvg(362, 496));
    }

    @Test
    void roundedAvg_350_902_returns_1001110010() {
        assertEquals("1001110010", s.roundedAvg(350, 902));
    }

    @Test
    void roundedAvg_197_233_returns_11010111() {
        assertEquals("11010111", s.roundedAvg(197, 233));
    }

    @Test
    void roundedAvg_7_5_returns_minus1() {
        assertEquals(-1, s.roundedAvg(7, 5));
    }

    @Test
    void roundedAvg_5_1_returns_minus1() {
        assertEquals(-1, s.roundedAvg(5, 1));
    }

    @Test
    void roundedAvg_5_5_returns_101() {
        assertEquals("101", s.roundedAvg(5, 5));
    }
}
