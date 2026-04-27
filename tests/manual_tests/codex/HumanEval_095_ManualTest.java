import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/* @Authors
 *   Student Names: Ahmet AKIN, Berkin ZORBA , Eylül Zeynep PINARBASI
 *   Student IDs: 15200027, 150200735, 150220725
 */

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HumanEval_095_ManualTest {

    private final Solution solution = new Solution();

    private static Map<Object, Object> mapOf(Object... entries) {
        Map<Object, Object> result = new LinkedHashMap<>();
        for (int i = 0; i < entries.length; i += 2) {
            result.put(entries[i], entries[i + 1]);
        }
        return result;
    }

    @Nested
    @DisplayName("Valid classes")
    class ValidClasses {
        @Test
        void minimalLowercaseMap() {
            assertTrue(solution.checkDictCase(mapOf("a", 1)));
        }

        @Test
        void minimalUppercaseMap() {
            assertTrue(solution.checkDictCase(mapOf("A", 1)));
        }

        @Test
        void lowercaseKeysWithSeparatorsAndDigits() {
            assertTrue(solution.checkDictCase(mapOf(
                    "zip_code1", "27513",
                    "state2", "nc"
            )));
        }

        @Test
        void uppercaseKeysWithSeparatorsAndDigits() {
            assertTrue(solution.checkDictCase(mapOf(
                    "ZIP_CODE1", "27513",
                    "STATE2", "NC"
            )));
        }
    }

    @Nested
    @DisplayName("Invalid classes")
    class InvalidClasses {
        @Test
        void mixedCaseAcrossKeys() {
            assertFalse(solution.checkDictCase(mapOf(
                    "a", 1,
                    "B", 2
            )));
        }

        @Test
        void titleCaseKey() {
            assertFalse(solution.checkDictCase(mapOf(
                    "Name", "John",
                    "Age", "36"
            )));
        }

        @Test
        void nonStringKey() {
            Map<Object, Object> input = mapOf("a", 1);
            input.put(8, 2);
            assertFalse(solution.checkDictCase(input));
        }

        @Test
        void noLetterKeyObservedAsInvalid() {
            assertFalse(solution.checkDictCase(mapOf("123", 1)));
        }

        @Test
        void uncasedUnicodeLetterObservedAsInvalid() {
            assertFalse(solution.checkDictCase(mapOf("中", 1)));
        }
    }

    @Nested
    @DisplayName("Boundaries")
    class Boundaries {
        @Test
        void emptyMap() {
            assertFalse(solution.checkDictCase(new LinkedHashMap<>()));
        }

        @Test
        void firstCrossCaseConflict() {
            assertFalse(solution.checkDictCase(mapOf(
                    "a", 1,
                    "A", 2
            )));
        }

        @Test
        void firstTypeConflict() {
            Map<Object, Object> input = mapOf("a", 1);
            input.put(1, 2);
            assertFalse(solution.checkDictCase(input));
        }

        @Test
        void emptyStringKeyObservedAsInvalid() {
            assertFalse(solution.checkDictCase(mapOf("", 1)));
        }
    }
}
