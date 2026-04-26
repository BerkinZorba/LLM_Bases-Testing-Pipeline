import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HumanEval_095_ManualTest {

    private final Solution s = new Solution();

    private static Map<Object, Object> mapOf(Object... kv) {
        Map<Object, Object> m = new LinkedHashMap<>();
        for (int i = 0; i < kv.length; i += 2) {
            m.put(kv[i], kv[i + 1]);
        }
        return m;
    }

    @Nested
    @DisplayName("Valid partitions")
    class Valid {
        @Test @DisplayName("V1 single all-lower key")
        void v1() { assertTrue(s.checkDictCase(mapOf("a", "x"))); }

        @Test @DisplayName("V2 multiple all-lower keys")
        void v2() { assertTrue(s.checkDictCase(mapOf("p", "pineapple", "b", "banana"))); }

        @Test @DisplayName("V3 all-lower keys with cased values (values irrelevant)")
        void v3() { assertTrue(s.checkDictCase(mapOf("fruit", "Orange", "taste", "Sweet"))); }

        @Test @DisplayName("V4 single all-upper key")
        void v4() { assertTrue(s.checkDictCase(mapOf("A", "x"))); }

        @Test @DisplayName("V5 multiple all-upper keys")
        void v5() { assertTrue(s.checkDictCase(mapOf("STATE", "NC", "ZIP", "12345"))); }

        @Test @DisplayName("V6 all-lower + caseless (digits) -> true")
        void v6() { assertTrue(s.checkDictCase(mapOf("zip", "x", "12345", "y"))); }

        @Test @DisplayName("V7 all-upper + caseless (digits) -> true")
        void v7() { assertTrue(s.checkDictCase(mapOf("ZIP", "x", "12345", "y"))); }

        @Test @DisplayName("V8 caseless digits-only key alone")
        void v8() { assertTrue(s.checkDictCase(mapOf("123", "x"))); }

        @Test @DisplayName("V9 caseless empty-string key alone")
        void v9() { assertTrue(s.checkDictCase(mapOf("", "x"))); }

        @Test @DisplayName("V10 caseless punctuation key alone")
        void v10() { assertTrue(s.checkDictCase(mapOf("_", "x"))); }

        @Test @DisplayName("V11 mixed lower + upper keys -> false")
        void v11() { assertFalse(s.checkDictCase(mapOf("p", "pineapple", "A", "banana", "B", "banana"))); }

        @Test @DisplayName("V12 title-case keys -> false")
        void v12() { assertFalse(s.checkDictCase(mapOf("Name", "John", "Age", "36", "City", "Houston"))); }

        @Test @DisplayName("V13 single mixed-case key -> false")
        void v13() { assertFalse(s.checkDictCase(mapOf("aB", "x"))); }
    }

    @Nested
    @DisplayName("Invalid partitions")
    class Invalid {
        @Test @DisplayName("I1 empty map -> false")
        void i1() { assertFalse(s.checkDictCase(new HashMap<>())); }

        @Test @DisplayName("I2 only an Integer key -> false")
        void i2() { assertFalse(s.checkDictCase(mapOf(1, "x"))); }

        @Test @DisplayName("I3 string key followed by Integer key -> false")
        void i3() { assertFalse(s.checkDictCase(mapOf("a", "x", 7, "y"))); }

        @Test @DisplayName("I4 dataset row {\"p\", 5, \"a\"} -> false")
        void i4() { assertFalse(s.checkDictCase(mapOf("p", "pineapple", 5, "banana", "a", "banana"))); }
    }

    @Nested
    @DisplayName("Boundary — size, type, and case axes")
    class Boundaries {
        @Test @DisplayName("size=0 -> false (empty)")
        void size0() { assertFalse(s.checkDictCase(new HashMap<>())); }

        @Test @DisplayName("size=1 lower -> true")
        void size1Lower() { assertTrue(s.checkDictCase(mapOf("a", "x"))); }

        @Test @DisplayName("size=1 upper -> true")
        void size1Upper() { assertTrue(s.checkDictCase(mapOf("A", "x"))); }

        @Test @DisplayName("size=large all-lower -> true")
        void sizeLargeLower() {
            assertTrue(s.checkDictCase(mapOf(
                    "alpha", "1", "beta", "2", "gamma", "3", "delta", "4",
                    "epsilon", "5", "zeta", "6")));
        }

        @Test @DisplayName("size=large all-upper -> true")
        void sizeLargeUpper() {
            assertTrue(s.checkDictCase(mapOf(
                    "ALPHA", "1", "BETA", "2", "GAMMA", "3", "DELTA", "4",
                    "EPSILON", "5", "ZETA", "6")));
        }

        @Test @DisplayName("first-encountered key is non-string -> false")
        void firstKeyNonString() { assertFalse(s.checkDictCase(mapOf(99, "x"))); }

        @Test @DisplayName("non-string key after a valid one -> false")
        void nonStringAfterValid() { assertFalse(s.checkDictCase(mapOf("a", "x", 99, "y"))); }
    }
}
