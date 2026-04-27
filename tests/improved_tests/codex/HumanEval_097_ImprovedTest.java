import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_097_ImprovedTest {

    private final Solution solution = new Solution();

    private static Stream<Arguments> representativeProducts() {
        return Stream.of(
                Arguments.of(1, 2, 2),
                Arguments.of(123, 45, 15),
                Arguments.of(999, 999, 81),
                Arguments.of(-41, 32, 2),
                Arguments.of(-41, -32, 2),
                Arguments.of(70, -89, 0),
                Arguments.of(Integer.MAX_VALUE, 12, 14),
                Arguments.of(Integer.MIN_VALUE, 19, 72)
        );
    }

    @ParameterizedTest(name = "multiply({0}, {1}) = {2}")
    @MethodSource("representativeProducts")
    @DisplayName("Multiplies the magnitude of the unit digits")
    void multipliesMagnitudeOfUnitDigits(int a, int b, int expected) {
        assertEquals(expected, solution.multiply(a, b));
    }

    @Test
    void ignoresTensAndHigherPlaces() {
        assertEquals(36, solution.multiply(1006, 2006));
    }

    @Test
    void zeroUnitDigitDominatesNonZeroOperand() {
        assertEquals(0, solution.multiply(450, -17));
    }

    @Test
    void bothNegativeOperandsStillProducePositiveDigitProduct() {
        assertEquals(24, solution.multiply(-18, -43));
    }

    @Test
    void oneDigitInputsBehaveLikeWholeNumbers() {
        assertEquals(35, solution.multiply(5, -7));
    }
}
