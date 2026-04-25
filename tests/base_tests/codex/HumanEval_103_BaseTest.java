import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_103_BaseTest {

    private final Solution solution = new Solution();

    @Test
    void exampleOneToFive_returnsBinaryThree() {
        assertEquals("11", solution.roundedAvg(1, 5));
    }

    @Test
    void sevenToThirteen_returnsBinaryTen() {
        assertEquals("1010", solution.roundedAvg(7, 13));
    }

    @Test
    void nineSixtyFourToNineSeventySeven_returnsExpectedBinary() {
        assertEquals("1111001011", solution.roundedAvg(964, 977));
    }

    @Test
    void nineNinetySixToNineNinetySeven_returnsExpectedBinary() {
        assertEquals("1111100101", solution.roundedAvg(996, 997));
    }

    @Test
    void fiveSixtyToEightFiftyOne_returnsExpectedBinary() {
        assertEquals("1011000010", solution.roundedAvg(560, 851));
    }

    @Test
    void oneEightyFiveToFiveFortySix_returnsExpectedBinary() {
        assertEquals("101101110", solution.roundedAvg(185, 546));
    }

    @Test
    void threeSixtyTwoToFourNinetySix_returnsExpectedBinary() {
        assertEquals("110101101", solution.roundedAvg(362, 496));
    }

    @Test
    void threeFiftyToNineZeroTwo_returnsExpectedBinary() {
        assertEquals("1001110010", solution.roundedAvg(350, 902));
    }

    @Test
    void oneNinetySevenToTwoThirtyThree_returnsExpectedBinary() {
        assertEquals("11010111", solution.roundedAvg(197, 233));
    }

    @Test
    void descendingRange_returnsMinusOne() {
        assertEquals(-1, solution.roundedAvg(7, 5));
    }

    @Test
    void anotherDescendingRange_returnsMinusOne() {
        assertEquals(-1, solution.roundedAvg(5, 1));
    }

    @Test
    void equalEndpoints_returnBinaryOfTheValue() {
        assertEquals("101", solution.roundedAvg(5, 5));
    }
}
