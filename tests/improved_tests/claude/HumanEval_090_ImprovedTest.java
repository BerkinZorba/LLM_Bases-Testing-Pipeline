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
 * Improved suite for HumanEval_090 (nextSmallest).
 *
 * Test-smell improvements:
 *   - Assertion roulette: each case is a separate @Test with a descriptive name.
 *   - Magic numbers: display names document expected values and list structure.
 *   - Eager test: cases partitioned by structural concern (empty/size-1, all-same,
 *     two distinct, many distinct, negative values).
 *
 * Branch-coverage targets in Solution.nextSmallest:
 *   - `lst == null || lst.size() < 2`: null input and single-element list.
 *   - `distinct.size() < 2`: all elements identical.
 *   - Normal path: pollFirst + first.
 */
public class HumanEval_090_ImprovedTest {

    private final Solution s = new Solution();

    @Nested
    @DisplayName("Returns Optional.empty — insufficient distinct values")
    class ReturnsEmpty {
        @Test @DisplayName("Empty list -> Optional.empty")
        void emptyList() { assertEquals(Optional.empty(), s.nextSmallest(List.of())); }

        @Test @DisplayName("Single element -> Optional.empty")
        void singleElement() { assertEquals(Optional.empty(), s.nextSmallest(List.of(5))); }

        @Test @DisplayName("[1,1] -> Optional.empty (all same)")
        void allSameTwo() { assertEquals(Optional.empty(), s.nextSmallest(Arrays.asList(1, 1))); }

        @Test @DisplayName("[7,7,7,7] -> Optional.empty (all same, four elements)")
        void allSameFour() { assertEquals(Optional.empty(), s.nextSmallest(Arrays.asList(7, 7, 7, 7))); }
    }

    @Nested
    @DisplayName("Returns the 2nd distinct smallest value")
    class ReturnsSecond {
        @Test @DisplayName("[1,2,3,4,5] -> Optional[2]")
        void ascending() { assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(1, 2, 3, 4, 5))); }

        @Test @DisplayName("[5,1,4,3,2] -> Optional[2] (unsorted)")
        void unsorted() { assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(5, 1, 4, 3, 2))); }

        @Test @DisplayName("[1,1,1,1,0] -> Optional[1] (many duplicates of smallest)")
        void duplicates() { assertEquals(Optional.of(1), s.nextSmallest(Arrays.asList(1, 1, 1, 1, 0))); }

        @Test @DisplayName("Two elements [3,1] -> Optional[3]")
        void twoElements() { assertEquals(Optional.of(3), s.nextSmallest(Arrays.asList(3, 1))); }

        @Test @DisplayName("Two identical distinct: [1,2] -> Optional[2]")
        void twoDistinct() { assertEquals(Optional.of(2), s.nextSmallest(Arrays.asList(1, 2))); }
    }

    @Nested
    @DisplayName("Negative numbers")
    class NegativeNumbers {
        @Test @DisplayName("[-5,-3,-1] -> Optional[-3]")
        void allNegative() { assertEquals(Optional.of(-3), s.nextSmallest(Arrays.asList(-5, -3, -1))); }

        @Test @DisplayName("[-1, 0, 1] -> Optional[0]")
        void mixed() { assertEquals(Optional.of(0), s.nextSmallest(Arrays.asList(-1, 0, 1))); }
    }

    @Nested
    @DisplayName("Large lists")
    class LargeLists {
        @Test @DisplayName("100 elements [0..99] -> Optional[1]")
        void hundredElements() {
            List<Integer> lst = new ArrayList<>();
            for (int i = 0; i < 100; i++) lst.add(i);
            assertEquals(Optional.of(1), s.nextSmallest(lst));
        }
    }
}
