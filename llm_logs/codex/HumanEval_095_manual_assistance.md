# LLM Interaction Log

- Prompt ID: HumanEval_095
- Model: codex
- Workflow Step: manual_assistance
- Timestamp: 2026-04-26 02:07:45
- Output Files:
  - tests/manual_tests/codex/HumanEval_095_blackbox.md
  - tests/manual_tests/codex/HumanEval_095_ManualTest.java

## Exact Prompt
Create Codex-side manual black-box artifacts for `HumanEval_095` (`checkDictCase`): a markdown assessment covering equivalence classes, valid and invalid classes, and boundary conditions, plus an executable JUnit 6 suite derived from that black-box design.

## Exact Response
```markdown
# HumanEval_095 manual black-box assessment (Codex)

## Prompt contract

`checkDictCase(Map<Object, Object> dict)` should return `true` only when:

- the map is not empty,
- every key is a `String`,
- every key belongs to the same case class,
- that shared class is either lowercase-only or uppercase-only.

If any key is non-string, mixed-case, or the map is empty, the observable result should be `false`.

## Equivalence classes

### Valid classes

- V1: all keys are lowercase strings
  - Example: `{"a": 1, "b": 2}` -> `true`
- V2: all keys are uppercase strings
  - Example: `{"A": 1, "B": 2}` -> `true`
- V3: lowercase words with separators or digits but no uppercase letters
  - Example: `{"zip_code1": 1, "state2": 2}` -> `true`
- V4: uppercase words with separators or digits but no lowercase letters
  - Example: `{"ZIP_CODE1": 1, "STATE2": 2}` -> `true`

### Invalid / out-of-scope classes

- I1: mixed lowercase and uppercase keys across the map
  - Example: `{"a": 1, "B": 2}` -> `false`
- I2: a mixed-case string key
  - Example: `{"Name": "John"}` -> `false`
- I3: a non-string key
  - Example: `{"a": 1, 8: 2}` -> `false`
- I4: empty map
  - Example: `{}` -> `false`
- I5: string key with no cased letters
  - Current Codex implementation treats keys like `""` or `"123"` as invalid and returns `false`.

## Boundary conditions

- B1: smallest non-empty lowercase map
  - `{"a": 1}` -> `true`
- B2: smallest non-empty uppercase map
  - `{"A": 1}` -> `true`
- B3: first cross-case conflict
  - `{"a": 1, "A": 2}` -> `false`
- B4: first type conflict
  - `{"a": 1, 1: 2}` -> `false`
- B5: empty-input boundary
  - `{}` -> `false`

## Expected black-box observations

- Values do not affect the result; only key type and key casing matter.
- Once the first key establishes lowercase or uppercase expectation, every later key must match it.
- Mixed-case words such as `"Name"` are rejected because they are neither fully lowercase nor fully uppercase.
- The current implementation also rejects strings without any letters because they do not belong to either cased class.
```

```java
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
```

## Decision Note
Accepted as the Codex-authored manual black-box assessment and executable manual suite for HumanEval_095.
