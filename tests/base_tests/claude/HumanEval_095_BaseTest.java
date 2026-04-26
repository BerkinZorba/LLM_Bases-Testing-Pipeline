import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HumanEval_095_BaseTest {

    private final Solution s = new Solution();

    private static Map<Object, Object> mapOf(Object... kv) {
        Map<Object, Object> m = new HashMap<>();
        for (int i = 0; i < kv.length; i += 2) {
            m.put(kv[i], kv[i + 1]);
        }
        return m;
    }

    @Test
    void allLowerCaseKeys_returnsTrue() {
        assertTrue(s.checkDictCase(mapOf("p", "pineapple", "b", "banana")));
    }

    @Test
    void mixedLowerAndUpperKeys_returnsFalse() {
        assertFalse(s.checkDictCase(mapOf("p", "pineapple", "A", "banana", "B", "banana")));
    }

    @Test
    void containsNonStringKey_returnsFalse() {
        assertFalse(s.checkDictCase(mapOf("p", "pineapple", 5, "banana", "a", "banana")));
    }

    @Test
    void titleCaseKeys_returnsFalse() {
        assertFalse(s.checkDictCase(mapOf("Name", "John", "Age", "36", "City", "Houston")));
    }

    @Test
    void allUpperCaseKeys_returnsTrue() {
        assertTrue(s.checkDictCase(mapOf("STATE", "NC", "ZIP", "12345")));
    }

    @Test
    void allLowerCaseKeys_returnsTrue_secondCase() {
        assertTrue(s.checkDictCase(mapOf("fruit", "Orange", "taste", "Sweet")));
    }

    @Test
    void emptyMap_returnsFalse() {
        assertFalse(s.checkDictCase(new HashMap<>()));
    }
}
