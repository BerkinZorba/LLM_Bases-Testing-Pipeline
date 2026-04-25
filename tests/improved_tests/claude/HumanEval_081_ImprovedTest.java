import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HumanEval_081_ImprovedTest {

    private final Solution s = new Solution();

    private List<String> grade(Double... gpas) {
        return s.numericalLetterGrade(new ArrayList<>(Arrays.asList(gpas)));
    }

    @Nested
    @DisplayName("Dataset cases")
    class Dataset {
        @Test
        @DisplayName("[4.0, 3.0, 1.7, 2.0, 3.5] -> [A+, B, C-, C, A-]")
        void d1() {
            assertEquals(Arrays.asList("A+", "B", "C-", "C", "A-"), grade(4.0, 3.0, 1.7, 2.0, 3.5));
        }

        @Test
        @DisplayName("[1.2] -> [D+]")
        void d2() { assertEquals(List.of("D+"), grade(1.2)); }

        @Test
        @DisplayName("[0.5] -> [D-]")
        void d3() { assertEquals(List.of("D-"), grade(0.5)); }

        @Test
        @DisplayName("[0.0] -> [E]")
        void d4() { assertEquals(List.of("E"), grade(0.0)); }

        @Test
        @DisplayName("[1.0, 0.3, 1.5, 2.8, 3.3] -> [D, D-, C-, B, B+]")
        void d5() {
            assertEquals(Arrays.asList("D", "D-", "C-", "B", "B+"), grade(1.0, 0.3, 1.5, 2.8, 3.3));
        }

        @Test
        @DisplayName("[0.0, 0.7] -> [E, D-]")
        void d6() { assertEquals(Arrays.asList("E", "D-"), grade(0.0, 0.7)); }
    }

    @Nested
    @DisplayName("Branch coverage — every band edge: at-edge stays in lower band, just-above moves up")
    class BandEdges {
        @Test @DisplayName("4.0 == top -> A+ (only exact equality maps to A+)")
        void topEdge() { assertEquals(List.of("A+"), grade(4.0)); }

        @Test @DisplayName("3.7 stays A-, 3.71 moves to A")
        void edge_37() {
            assertEquals(Arrays.asList("A-", "A"), grade(3.7, 3.71));
        }

        @Test @DisplayName("3.3 stays B+, 3.31 moves to A-")
        void edge_33() {
            assertEquals(Arrays.asList("B+", "A-"), grade(3.3, 3.31));
        }

        @Test @DisplayName("3.0 stays B, 3.01 moves to B+")
        void edge_30() {
            assertEquals(Arrays.asList("B", "B+"), grade(3.0, 3.01));
        }

        @Test @DisplayName("2.7 stays B-, 2.71 moves to B")
        void edge_27() {
            assertEquals(Arrays.asList("B-", "B"), grade(2.7, 2.71));
        }

        @Test @DisplayName("2.3 stays C+, 2.31 moves to B-")
        void edge_23() {
            assertEquals(Arrays.asList("C+", "B-"), grade(2.3, 2.31));
        }

        @Test @DisplayName("2.0 stays C, 2.01 moves to C+")
        void edge_20() {
            assertEquals(Arrays.asList("C", "C+"), grade(2.0, 2.01));
        }

        @Test @DisplayName("1.7 stays C-, 1.71 moves to C")
        void edge_17() {
            assertEquals(Arrays.asList("C-", "C"), grade(1.7, 1.71));
        }

        @Test @DisplayName("1.3 stays D+, 1.31 moves to C-")
        void edge_13() {
            assertEquals(Arrays.asList("D+", "C-"), grade(1.3, 1.31));
        }

        @Test @DisplayName("1.0 stays D, 1.01 moves to D+")
        void edge_10() {
            assertEquals(Arrays.asList("D", "D+"), grade(1.0, 1.01));
        }

        @Test @DisplayName("0.7 stays D-, 0.71 moves to D")
        void edge_07() {
            assertEquals(Arrays.asList("D-", "D"), grade(0.7, 0.71));
        }

        @Test @DisplayName("0.0 stays E, 0.01 moves to D-")
        void edge_00() {
            assertEquals(Arrays.asList("E", "D-"), grade(0.0, 0.01));
        }
    }

    @Nested
    @DisplayName("Inputs and outputs — empty list, ordering, length parity")
    class StructuralProperties {
        @Test
        @DisplayName("empty input -> empty output")
        void empty() {
            assertEquals(Collections.emptyList(),
                    s.numericalLetterGrade(new ArrayList<>(Collections.emptyList())));
        }

        @Test
        @DisplayName("output length equals input length")
        void lengthParity() {
            List<String> out = grade(0.0, 0.5, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0);
            assertEquals(9, out.size());
        }

        @Test
        @DisplayName("order is preserved (per-index mapping, not sorted)")
        void orderPreserved() {
            assertEquals(Arrays.asList("A+", "E", "A+", "E"), grade(4.0, 0.0, 4.0, 0.0));
        }

        @Test
        @DisplayName("returned list is a non-null List<String>")
        void returnType() {
            List<String> out = grade(2.5);
            assertTrue(out != null);
            assertEquals(1, out.size());
        }
    }

    @Nested
    @DisplayName("Each band reachable from at least one representative GPA")
    class EveryBandReachable {
        @Test
        @DisplayName("Hit all 13 bands across one call")
        void allBands() {
            // representatives chosen strictly inside each band
            List<String> out = grade(
                    4.0,   // A+
                    3.8,   // A
                    3.4,   // A-
                    3.1,   // B+
                    2.8,   // B
                    2.4,   // B-
                    2.1,   // C+
                    1.8,   // C
                    1.4,   // C-
                    1.1,   // D+
                    0.8,   // D
                    0.1,   // D-
                    0.0    // E
            );
            assertEquals(
                    Arrays.asList("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E"),
                    out);
        }
    }
}
