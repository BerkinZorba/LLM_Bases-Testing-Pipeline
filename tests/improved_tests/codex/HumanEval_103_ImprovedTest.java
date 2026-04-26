import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class HumanEval_103_ImprovedTest {

    private final Solution solution = new Solution();

    private static Stream<Arguments> representativeRanges() {
        return Stream.of(
                Arguments.of(1, 1, "1"),
                Arguments.of(1, 2, "10"),
                Arguments.of(1, 4, "11"),
                Arguments.of(1, 6, "100"),
                Arguments.of(10, 20, "1111"),
                Arguments.of(20, 33, "11011"),
                Arguments.of(1000, 1001, "1111101001")
        );
    }

    @ParameterizedTest(name = "roundedAvg({0}, {1}) = {2}")
    @MethodSource("representativeRanges")
    @DisplayName("Returns the binary encoding of the rounded inclusive average")
    void returnsBinaryEncodingOfRoundedAverage(int n, int m, String expected) {
        Object result = solution.roundedAvg(n, m);
        assertInstanceOf(String.class, result);
        assertEquals(expected, result);
    }

    @Test
    void halfPointWithOddEndpointSumRoundsUpForPositiveInputs() {
        assertEquals("110", solution.roundedAvg(5, 6));
    }

    @Test
    void evenEndpointSumNeedsNoRounding() {
        assertEquals("1010", solution.roundedAvg(8, 12));
    }

    @Test
    void singleValueRangeReturnsBinaryOfThatValue() {
        assertEquals("1001", solution.roundedAvg(9, 9));
    }

    @Test
    void descendingRangeReturnsMinusOneAsInteger() {
        Object result = solution.roundedAvg(12, 3);
        assertInstanceOf(Integer.class, result);
        assertEquals(-1, result);
    }

    @Test
    void largeButSafeEndpointsAvoidIntermediateOverflow() {
        assertEquals(
                Integer.toBinaryString(Integer.MAX_VALUE - 1),
                solution.roundedAvg(Integer.MAX_VALUE - 2, Integer.MAX_VALUE)
        );
    }
}
