import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HumanEval_095_ImprovedTest {

    private final Solution solution = new Solution();

    private static Stream<Arguments> representativeCases() {
        return Stream.of(
                Arguments.of("single lowercase key", mapOf("a", 1), true),
                Arguments.of("single uppercase key", mapOf("A", 1), true),
                Arguments.of("multiple lowercase keys", mapOf("alpha", 1, "beta", 2, "gamma", 3), true),
                Arguments.of("multiple uppercase keys", mapOf("ALPHA", 1, "BETA", 2, "GAMMA", 3), true),
                Arguments.of("mixed lowercase and uppercase keys", mapOf("alpha", 1, "BETA", 2), false),
                Arguments.of("title-case key invalidates map", mapOf("Name", 1, "Age", 2), false),
                Arguments.of("digit-only string key is not cased", mapOf("123", 1), false),
                Arguments.of("empty string key is not cased", mapOf("", 1), false),
                Arguments.of("uncased unicode letter is rejected", mapOf("中", 1), false),
                Arguments.of("letters with digits can still be lowercase", mapOf("code123", 1, "value9", 2), true),
                Arguments.of("letters with digits can still be uppercase", mapOf("CODE123", 1, "VALUE9", 2), true),
                Arguments.of("underscore does not break lowercase classification", mapOf("snake_case", 1), true),
                Arguments.of("non-string key invalidates otherwise lowercase map", mapWithNonStringKey(), false)
        );
    }

    @ParameterizedTest(name = "{0} -> {2}")
    @MethodSource("representativeCases")
    @DisplayName("Covers key-type and key-case partitions")
    void coversKeyTypeAndCasePartitions(String label, Map<Object, Object> input, boolean expected) {
        if (expected) {
            assertTrue(solution.checkDictCase(input), label);
        } else {
            assertFalse(solution.checkDictCase(input), label);
        }
    }

    @Test
    void laterMixedCaseKeyCausesFailureAfterValidPrefix() {
        assertFalse(solution.checkDictCase(mapOf(
                "alpha", 1,
                "beta", 2,
                "Gamma", 3
        )));
    }

    @Test
    void uppercaseExpectationRejectsLaterLowercaseKey() {
        assertFalse(solution.checkDictCase(mapOf(
                "STATE", "NC",
                "ZIP", "27513",
                "city", "Raleigh"
        )));
    }

    @Test
    void lowercaseExpectationRejectsLaterUppercaseKey() {
        assertFalse(solution.checkDictCase(mapOf(
                "state", "nc",
                "zip", "27513",
                "CITY", "Raleigh"
        )));
    }

    @Test
    void emptyMapIsExplicitlyRejected() {
        assertFalse(solution.checkDictCase(new LinkedHashMap<>()));
    }

    private static Map<Object, Object> mapWithNonStringKey() {
        Map<Object, Object> result = mapOf("alpha", 1, "beta", 2);
        result.put(7, 3);
        return result;
    }

    private static Map<Object, Object> mapOf(Object... entries) {
        Map<Object, Object> result = new LinkedHashMap<>();
        for (int i = 0; i < entries.length; i += 2) {
            result.put(entries[i], entries[i + 1]);
        }
        return result;
    }
}
