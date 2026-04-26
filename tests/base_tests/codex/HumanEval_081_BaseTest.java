import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_081_BaseTest {

    private final Solution solution = new Solution();

    @Test
    void exampleMixReturnsExpectedLetters() {
        assertEquals(
                Arrays.asList("A+", "B", "C-", "C", "A-"),
                solution.numericalLetterGrade(new ArrayList<>(Arrays.asList(4.0, 3.0, 1.7, 2.0, 3.5)))
        );
    }

    @Test
    void justAboveOneReturnsDPlus() {
        assertEquals(List.of("D+"), solution.numericalLetterGrade(new ArrayList<>(List.of(1.2))));
    }

    @Test
    void halfPointReturnsDMinus() {
        assertEquals(List.of("D-"), solution.numericalLetterGrade(new ArrayList<>(List.of(0.5))));
    }

    @Test
    void zeroReturnsE() {
        assertEquals(List.of("E"), solution.numericalLetterGrade(new ArrayList<>(List.of(0.0))));
    }

    @Test
    void mixedThresholdValuesMapToExpectedLetters() {
        assertEquals(
                Arrays.asList("D", "D-", "C-", "B", "B+"),
                solution.numericalLetterGrade(new ArrayList<>(Arrays.asList(1.0, 0.3, 1.5, 2.8, 3.3)))
        );
    }

    @Test
    void zeroAndSevenTenthsCoverLowestBands() {
        assertEquals(
                Arrays.asList("E", "D-"),
                solution.numericalLetterGrade(new ArrayList<>(Arrays.asList(0.0, 0.7)))
        );
    }
}
