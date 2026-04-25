import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_081_BaseTest {

    private final Solution s = new Solution();

    @Test
    void datasetExample_mixedFiveGrades() {
        assertEquals(
                Arrays.asList("A+", "B", "C-", "C", "A-"),
                s.numericalLetterGrade(new ArrayList<>(Arrays.asList(4.0, 3.0, 1.7, 2.0, 3.5)))
        );
    }

    @Test
    void singleton_oneTwo_returnsDPlus() {
        assertEquals(List.of("D+"), s.numericalLetterGrade(new ArrayList<>(List.of(1.2))));
    }

    @Test
    void singleton_zeroFive_returnsDMinus() {
        assertEquals(List.of("D-"), s.numericalLetterGrade(new ArrayList<>(List.of(0.5))));
    }

    @Test
    void singleton_zero_returnsE() {
        assertEquals(List.of("E"), s.numericalLetterGrade(new ArrayList<>(List.of(0.0))));
    }

    @Test
    void mixedFive_lowMidRange() {
        assertEquals(
                Arrays.asList("D", "D-", "C-", "B", "B+"),
                s.numericalLetterGrade(new ArrayList<>(Arrays.asList(1.0, 0.3, 1.5, 2.8, 3.3)))
        );
    }

    @Test
    void zeroAndZeroSeven_returnEAndDMinus() {
        assertEquals(
                Arrays.asList("E", "D-"),
                s.numericalLetterGrade(new ArrayList<>(Arrays.asList(0.0, 0.7)))
        );
    }
}
