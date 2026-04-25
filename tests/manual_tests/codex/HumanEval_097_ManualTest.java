import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_097_ManualTest {

    private final Solution solution = new Solution();

    @Nested
    @DisplayName("Valid classes")
    class ValidClasses {
        @Test
        void positiveDigits() {
            assertEquals(16, solution.multiply(148, 412));
        }

        @Test
        void firstOperandNegative() {
            assertEquals(21, solution.multiply(-23, 17));
        }

        @Test
        void bothOperandsNegative() {
            assertEquals(24, solution.multiply(-18, -43));
        }

        @Test
        void oneDigitOperands() {
            assertEquals(35, solution.multiply(5, -7));
        }

        @Test
        void higherPlacesIgnored() {
            assertEquals(36, solution.multiply(1006, 2006));
        }
    }

    @Nested
    @DisplayName("Boundaries")
    class Boundaries {
        @Test
        void zeroOperand() {
            assertEquals(0, solution.multiply(0, 1));
        }

        @Test
        void bothZero() {
            assertEquals(0, solution.multiply(0, 0));
        }

        @Test
        void integerMaxValueUsesFinalDigitOnly() {
            assertEquals(14, solution.multiply(Integer.MAX_VALUE, 12));
        }

        @Test
        void integerMinValueUsesMagnitudeOfNegativeRemainder() {
            assertEquals(72, solution.multiply(Integer.MIN_VALUE, 19));
        }

        @Test
        void maxDigitPair() {
            assertEquals(81, solution.multiply(999, 999));
        }
    }
}
