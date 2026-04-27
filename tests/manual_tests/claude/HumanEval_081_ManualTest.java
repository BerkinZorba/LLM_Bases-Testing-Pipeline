import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanEval_081_ManualTest {

    private final Solution s = new Solution();

    private List<String> grade(Double... gpas) {
        return s.numericalLetterGrade(new ArrayList<>(Arrays.asList(gpas)));
    }

    @Nested
    @DisplayName("Valid partitions — V1..V16")
    class Valid {
        @Test @DisplayName("V1: [4.0] -> [A+]")
        void v1() { assertEquals(List.of("A+"), grade(4.0)); }

        @Test @DisplayName("V2: [3.8] -> [A]")
        void v2() { assertEquals(List.of("A"), grade(3.8)); }

        @Test @DisplayName("V3: [3.4] -> [A-]")
        void v3() { assertEquals(List.of("A-"), grade(3.4)); }

        @Test @DisplayName("V4: [3.1] -> [B+]")
        void v4() { assertEquals(List.of("B+"), grade(3.1)); }

        @Test @DisplayName("V5: [2.8] -> [B]")
        void v5() { assertEquals(List.of("B"), grade(2.8)); }

        @Test @DisplayName("V6: [2.4] -> [B-]")
        void v6() { assertEquals(List.of("B-"), grade(2.4)); }

        @Test @DisplayName("V7: [2.1] -> [C+]")
        void v7() { assertEquals(List.of("C+"), grade(2.1)); }

        @Test @DisplayName("V8: [1.8] -> [C]")
        void v8() { assertEquals(List.of("C"), grade(1.8)); }

        @Test @DisplayName("V9: [1.4] -> [C-]")
        void v9() { assertEquals(List.of("C-"), grade(1.4)); }

        @Test @DisplayName("V10: [1.2] -> [D+] (dataset)")
        void v10() { assertEquals(List.of("D+"), grade(1.2)); }

        @Test @DisplayName("V11: [0.8] -> [D]")
        void v11() { assertEquals(List.of("D"), grade(0.8)); }

        @Test @DisplayName("V12: [0.5] -> [D-] (dataset)")
        void v12() { assertEquals(List.of("D-"), grade(0.5)); }

        @Test @DisplayName("V13: [0.0] -> [E] (dataset)")
        void v13() { assertEquals(List.of("E"), grade(0.0)); }

        @Test @DisplayName("V14: dataset 5-element call")
        void v14() {
            assertEquals(Arrays.asList("A+", "B", "C-", "C", "A-"),
                    grade(4.0, 3.0, 1.7, 2.0, 3.5));
        }

        @Test @DisplayName("V15: order preserved under repeats [4.0,0.0,4.0,0.0]")
        void v15() {
            assertEquals(Arrays.asList("A+", "E", "A+", "E"), grade(4.0, 0.0, 4.0, 0.0));
        }

        @Test @DisplayName("V16: every-band batch reaches all 13 letters")
        void v16() {
            assertEquals(
                    Arrays.asList("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E"),
                    grade(4.0, 3.8, 3.4, 3.1, 2.8, 2.4, 2.1, 1.8, 1.4, 1.1, 0.8, 0.1, 0.0));
        }
    }

    @Nested
    @DisplayName("Boundary partitions — B1..B12")
    class Boundary {
        @Test @DisplayName("B1: 4.0 -> A+ (== top); 3.99 -> A (just below)")
        void b1() { assertEquals(Arrays.asList("A+", "A"), grade(4.0, 3.99)); }

        @Test @DisplayName("B2: 3.69 -> A-, 3.7 -> A-, 3.71 -> A")
        void b2() { assertEquals(Arrays.asList("A-", "A-", "A"), grade(3.69, 3.7, 3.71)); }

        @Test @DisplayName("B3: 3.29 -> B+, 3.3 -> B+, 3.31 -> A-")
        void b3() { assertEquals(Arrays.asList("B+", "B+", "A-"), grade(3.29, 3.3, 3.31)); }

        @Test @DisplayName("B4: 2.99 -> B, 3.0 -> B, 3.01 -> B+")
        void b4() { assertEquals(Arrays.asList("B", "B", "B+"), grade(2.99, 3.0, 3.01)); }

        @Test @DisplayName("B5: 2.69 -> B-, 2.7 -> B-, 2.71 -> B")
        void b5() { assertEquals(Arrays.asList("B-", "B-", "B"), grade(2.69, 2.7, 2.71)); }

        @Test @DisplayName("B6: 2.29 -> C+, 2.3 -> C+, 2.31 -> B-")
        void b6() { assertEquals(Arrays.asList("C+", "C+", "B-"), grade(2.29, 2.3, 2.31)); }

        @Test @DisplayName("B7: 1.99 -> C, 2.0 -> C, 2.01 -> C+")
        void b7() { assertEquals(Arrays.asList("C", "C", "C+"), grade(1.99, 2.0, 2.01)); }

        @Test @DisplayName("B8: 1.69 -> C-, 1.7 -> C-, 1.71 -> C")
        void b8() { assertEquals(Arrays.asList("C-", "C-", "C"), grade(1.69, 1.7, 1.71)); }

        @Test @DisplayName("B9: 1.29 -> D+, 1.3 -> D+, 1.31 -> C-")
        void b9() { assertEquals(Arrays.asList("D+", "D+", "C-"), grade(1.29, 1.3, 1.31)); }

        @Test @DisplayName("B10: 0.99 -> D, 1.0 -> D, 1.01 -> D+")
        void b10() { assertEquals(Arrays.asList("D", "D", "D+"), grade(0.99, 1.0, 1.01)); }

        @Test @DisplayName("B11: 0.69 -> D-, 0.7 -> D-, 0.71 -> D")
        void b11() { assertEquals(Arrays.asList("D-", "D-", "D"), grade(0.69, 0.7, 0.71)); }

        @Test @DisplayName("B12: 0.0 -> E, 0.01 -> D-")
        void b12() { assertEquals(Arrays.asList("E", "D-"), grade(0.0, 0.01)); }
    }

    @Nested
    @DisplayName("Pinned undefined-by-spec behavior — U1..U4")
    class PinnedUndefined {
        @Test @DisplayName("U1: empty list -> empty list")
        void u1_empty() {
            assertEquals(Collections.emptyList(),
                    s.numericalLetterGrade(new ArrayList<>(Collections.emptyList())));
        }

        @Test @DisplayName("U2: GPA above range (5.0) -> [A] via the > 3.7 branch (pinned)")
        void u2_aboveRange() { assertEquals(List.of("A"), grade(5.0)); }

        @Test @DisplayName("U3: negative GPA (-0.5) -> [E] via the else branch (pinned)")
        void u3_negative() { assertEquals(List.of("E"), grade(-0.5)); }

        @Test @DisplayName("U4: -0.0 -> [E] (Java IEEE-754: -0.0 == 0.0 is true; pinned)")
        void u4_signedZero() { assertEquals(List.of("E"), grade(-0.0)); }
    }
}
