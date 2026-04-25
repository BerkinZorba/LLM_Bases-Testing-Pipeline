import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HumanEval_095_BaseTest {

    private final Solution solution = new Solution();

    @Test
    void lowercaseKeys_returnTrue() {
        assertTrue(solution.checkDictCase(mapOf(
                "p", "pineapple",
                "b", "banana"
        )));
    }

    @Test
    void mixedLowerAndUpperKeys_returnFalse() {
        assertFalse(solution.checkDictCase(mapOf(
                "p", "pineapple",
                "A", "banana",
                "B", "banana"
        )));
    }

    @Test
    void nonStringKey_returnFalse() {
        Map<Object, Object> input = new LinkedHashMap<>();
        input.put("p", "pineapple");
        input.put(5, "banana");
        input.put("a", "banana");
        assertFalse(solution.checkDictCase(input));
    }

    @Test
    void mixedCaseKeys_returnFalse() {
        assertFalse(solution.checkDictCase(mapOf(
                "Name", "John",
                "Age", "36",
                "City", "Houston"
        )));
    }

    @Test
    void uppercaseKeys_returnTrue() {
        assertTrue(solution.checkDictCase(mapOf(
                "STATE", "NC",
                "ZIP", "12345"
        )));
    }

    @Test
    void lowercaseWords_returnTrue() {
        assertTrue(solution.checkDictCase(mapOf(
                "fruit", "Orange",
                "taste", "Sweet"
        )));
    }

    @Test
    void emptyMap_returnFalse() {
        assertFalse(solution.checkDictCase(new LinkedHashMap<>()));
    }

    private static Map<Object, Object> mapOf(Object... entries) {
        Map<Object, Object> result = new LinkedHashMap<>();
        for (int i = 0; i < entries.length; i += 2) {
            result.put(entries[i], entries[i + 1]);
        }
        return result;
    }
}
