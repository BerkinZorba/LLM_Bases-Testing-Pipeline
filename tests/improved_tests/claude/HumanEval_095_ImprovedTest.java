import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HumanEval_095_ImprovedTest {

    private final Solution s = new Solution();

    private static Map<Object, Object> mapOf(Object... kv) {
        Map<Object, Object> m = new LinkedHashMap<>();
        for (int i = 0; i < kv.length; i += 2) {
            m.put(kv[i], kv[i + 1]);
        }
        return m;
    }

    @Nested
    @DisplayName("Dataset cases")
    class Dataset {
        @Test
        @DisplayName("{p:pineapple, b:banana} -> true (all lower)")
        void d1() {
            assertTrue(s.checkDictCase(mapOf("p", "pineapple", "b", "banana")));
        }

        @Test
        @DisplayName("{p, A, B} -> false (mixed lower and upper)")
        void d2() {
            assertFalse(s.checkDictCase(mapOf("p", "pineapple", "A", "banana", "B", "banana")));
        }

        @Test
        @DisplayName("{p, 5, a} -> false (non-string key forbidden)")
        void d3() {
            assertFalse(s.checkDictCase(mapOf("p", "pineapple", 5, "banana", "a", "banana")));
        }

        @Test
        @DisplayName("{Name, Age, City} -> false (title case is neither all-lower nor all-upper)")
        void d4() {
            assertFalse(s.checkDictCase(mapOf("Name", "John", "Age", "36", "City", "Houston")));
        }

        @Test
        @DisplayName("{STATE, ZIP} -> true (all upper)")
        void d5() {
            assertTrue(s.checkDictCase(mapOf("STATE", "NC", "ZIP", "12345")));
        }

        @Test
        @DisplayName("{fruit, taste} -> true (all lower, case-2)")
        void d6() {
            assertTrue(s.checkDictCase(mapOf("fruit", "Orange", "taste", "Sweet")));
        }

        @Test
        @DisplayName("empty map -> false")
        void d7() {
            assertFalse(s.checkDictCase(new HashMap<>()));
        }
    }

    @Nested
    @DisplayName("Branch coverage — empty / non-string-key / lower / upper / mixed paths")
    class Branches {
        @Test
        @DisplayName("empty short-circuits without entering the loop")
        void emptyShortCircuit() {
            assertFalse(s.checkDictCase(new HashMap<>()));
        }

        @Test
        @DisplayName("first key non-string short-circuits to false (Integer key)")
        void firstKeyNonString() {
            assertFalse(s.checkDictCase(mapOf(1, "a")));
        }

        @Test
        @DisplayName("non-string key after a valid lower key still returns false")
        void nonStringAfterValid() {
            // LinkedHashMap preserves insertion order; the Integer key is encountered second.
            assertFalse(s.checkDictCase(mapOf("a", "x", 7, "y")));
        }

        @Test
        @DisplayName("single lower-case key -> true (allLower path)")
        void singleLower() {
            assertTrue(s.checkDictCase(mapOf("a", "x")));
        }

        @Test
        @DisplayName("single upper-case key -> true (allUpper path)")
        void singleUpper() {
            assertTrue(s.checkDictCase(mapOf("A", "x")));
        }

        @Test
        @DisplayName("single title-case key -> false (clears both flags)")
        void singleTitle() {
            assertFalse(s.checkDictCase(mapOf("Ab", "x")));
        }

        @Test
        @DisplayName("two-key all-lower -> true")
        void twoLower() {
            assertTrue(s.checkDictCase(mapOf("a", "x", "b", "y")));
        }

        @Test
        @DisplayName("two-key all-upper -> true")
        void twoUpper() {
            assertTrue(s.checkDictCase(mapOf("A", "x", "B", "y")));
        }

        @Test
        @DisplayName("one lower + one upper key -> false")
        void oneLowerOneUpper() {
            assertFalse(s.checkDictCase(mapOf("a", "x", "B", "y")));
        }
    }

    @Nested
    @DisplayName("Edge cases — caseless strings, empty key, unicode")
    class EdgeCases {
        @Test
        @DisplayName("digits-only key counts as all-lower AND all-upper -> true")
        void digitsOnly() {
            assertTrue(s.checkDictCase(mapOf("123", "x")));
        }

        @Test
        @DisplayName("empty-string key alone -> true (caseless)")
        void emptyKeyAlone() {
            assertTrue(s.checkDictCase(mapOf("", "x")));
        }

        @Test
        @DisplayName("digits key together with all-lower keys -> true")
        void digitsWithLower() {
            assertTrue(s.checkDictCase(mapOf("zip", "x", "12345", "y")));
        }

        @Test
        @DisplayName("digits key together with all-upper keys -> true")
        void digitsWithUpper() {
            assertTrue(s.checkDictCase(mapOf("ZIP", "x", "12345", "y")));
        }

        @Test
        @DisplayName("underscore-only key alone -> true (caseless)")
        void underscoreAlone() {
            assertTrue(s.checkDictCase(mapOf("_", "x")));
        }

        @Test
        @DisplayName("mixed-case single key (e.g. aB) -> false")
        void mixedCaseSingle() {
            assertFalse(s.checkDictCase(mapOf("aB", "x")));
        }
    }

    @Nested
    @DisplayName("Inputs and outputs — return type, side-effect freedom")
    class StructuralProperties {
        @Test
        @DisplayName("input map is not mutated by the call")
        void inputUnchanged() {
            Map<Object, Object> m = mapOf("a", "x", "b", "y");
            int before = m.size();
            s.checkDictCase(m);
            assertTrue(m.size() == before);
            assertTrue(m.containsKey("a"));
            assertTrue(m.containsKey("b"));
        }

        @Test
        @DisplayName("repeated calls with the same map are deterministic")
        void deterministic() {
            Map<Object, Object> m = mapOf("A", "x", "B", "y");
            assertTrue(s.checkDictCase(m));
            assertTrue(s.checkDictCase(m));
        }
    }
}
