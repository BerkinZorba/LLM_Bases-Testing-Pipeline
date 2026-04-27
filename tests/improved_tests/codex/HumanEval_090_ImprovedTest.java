import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

/**
 * Improved suite for HumanEval_090 (codex / GPT).
 *
 * Smells addressed:
 *   - Assertion roulette: each case is a separate @Test with a display name.
 *   - Eager test: grouped by concern.
 *
 * Branch targets:
 *   - lst == null: spec-undefined, not tested (left as missed).
 *   - lst.size() < 2: empty and single-element lists.
 *   - min == null (first element): always true on first iteration.
 *   - num < min: new minimum found.
 *   - inner if (min != null && !num.equals(min)): previous min promoted to second.
 *   - else if: num is new second candidate.
 *   - second == null: no second found (all-same list).
 */
public class HumanEval_090_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Empty-result cases")
    class EmptyResult {
        @Test @DisplayName("empty list -> Optional.empty")
        void emptyList() {
            assertEquals(Optional.empty(), s.nextSmallest(List.of()));
        }

        @Test @DisplayName("single element -> Optional.empty")
        void singleElement() {
            assertEquals(Optional.empty(), s.nextSmallest(List.of(5)));
        }

        @Test @DisplayName("all same -> Optional.empty")
        void allSame() {
            assertEquals(Optional.empty(), s.nextSmallest(Arrays.asList(3, 3, 3)));
        }

        @Test @DisplayName("exactly two same elements -> Optional.empty")
        void twoSame() {
            assertEquals(Optional.empty(), s.nextSmallest(Arrays.asList(7, 7)));
        }
    }

    @Nested
    @DisplayName("Normal cases")
    class NormalCases {
        @Test @DisplayName("ascending [1,2,3,4,5] -> Optional[2]")
        void ascending() {
            assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(1, 2, 3, 4, 5)));
        }

        @Test @DisplayName("unsorted [5,1,4,3,2] -> Optional[2]")
        void unsorted() {
            assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(5, 1, 4, 3, 2)));
        }

        @Test @DisplayName("descending [5,4,3,2,1] -> Optional[2]")
        void descending() {
            assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(5, 4, 3, 2, 1)));
        }

        @Test @DisplayName("two distinct elements [2,1] -> Optional[2]")
        void twoDistinct() {
            assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(2, 1)));
        }

        @Test @DisplayName("negatives present -> correct second")
        void withNegatives() {
            assertEquals(Optional.of(-1), s.nextSmallest(Arrays.asList(-2, -1, 0, 1)));
        }
    }

    @Nested
    @DisplayName("Duplicate-handling cases")
    class DuplicateCases {
        @Test @DisplayName("minimum repeated, one different -> second is min")
        void minRepeated() {
            assertEquals(Optional.of(1), s.nextSmallest(Arrays.asList(1, 1, 1, 1, 0)));
        }

        @Test @DisplayName("duplicates of second-smallest -> still returns second")
        void secondDuplicated() {
            assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(1, 2, 2, 3)));
        }
    }
}
