# LLM Interaction Log

- Prompt ID: HumanEval_007
- Model: codex
- Workflow Step: manual_assistance
- Timestamp: 2026-04-25 19:11:29 +03
- Output Files:
  - tests/manual_tests/codex/HumanEval_007_blackbox.md
  - tests/manual_tests/codex/HumanEval_007_ManualTest.java

## Exact Prompt
Write an independent Codex-authored manual black-box assessment and executable JUnit 6 manual test suite for `HumanEval_007` (`filterBySubstring`) without copying codex-side structure. Include equivalence classes, boundary cases, invalid or underspecified inputs, and executable tests derived from that analysis.

## Exact Response
```markdown
# HumanEval_007 - Manual black-box assessment (Codex)

Function under test: `Solution.filterBySubstring(List<String> strings, String substring)`

Prompt requirement:

> Filter an input list of strings only for ones that contain given substring

This analysis treats the prompt as a black-box contract over a list of Java strings and a substring. A returned list should contain exactly those original elements for which containment is true.

## 1. Observable input dimensions

| Dimension | Partitions |
|-----------|------------|
| List size | empty, one element, multiple elements |
| Match count | no matches, one match, many matches |
| Match location | whole string, prefix, middle, suffix |
| Multiplicity | unique matching values, duplicate matching values |
| Substring length | empty string, one character, multiple characters |
| Character category | letters, spaces, punctuation, non-ASCII text |
| Case relationship | exact case vs different case |
| Reference validity | null list, null substring, null element |

## 2. Equivalence classes

### Valid classes

| ID | Class | Example | Expected |
|----|-------|---------|----------|
| V1 | Empty list | `([], "x")` | `[]` |
| V2 | No element contains substring | `(["red", "blue"], "z")` | `[]` |
| V3 | Single exact element match | `(["same"], "same")` | `["same"]` |
| V4 | Prefix match | `(["start", "branch"], "sta")` | `["start"]` |
| V5 | Middle match | `(["planet", "lane", "plain"], "lan")` | `["planet", "lane"]` |
| V6 | Suffix match | `(["ending", "pending", "end"], "ing")` | `["ending", "pending"]` |
| V7 | Duplicate matching values | `(["aa", "bb", "aa"], "aa")` | `["aa", "aa"]` |
| V8 | Empty substring | `(["", "abc"], "")` | `["", "abc"]` |
| V9 | Empty element with non-empty substring | `(["", "abc"], "a")` | `["abc"]` |
| V10 | Case-sensitive search | `(["Dog", "dog", "hotDog"], "Dog")` | `["Dog", "hotDog"]` |
| V11 | Whitespace and punctuation | `(["a b", "a-b", "ab"], "a-")` | `["a-b"]` |
| V12 | Non-ASCII text | `(["ışık", "isik", "güz"], "ış")` | `["ışık"]` |

### Invalid / undefined-by-spec classes

| ID | Class | Current behavior |
|----|-------|------------------|
| I1 | `strings == null` | `NullPointerException` |
| I2 | `substring == null` | `NullPointerException` from `String.contains` |
| I3 | Matching path sees a `null` element | `NullPointerException` |

## 3. Boundary cases

| Boundary | Cases |
|----------|-------|
| List length | `[]`, one element, three elements |
| Substring length | `""`, `"a"`, `"abc"` |
| Element length | empty element, one-character element, longer element |
| Match position | whole string, prefix, middle, suffix, absent |

## 4. Coverage rationale

The manual suite exercises loop entry and exit, the true and false outcomes of `contains`, duplicate retention, order preservation, empty substring behavior, and undefined null-handling behavior.
```

```java
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Codex-authored manual suite derived from tests/manual_tests/codex/HumanEval_007_blackbox.md.
 */
public class HumanEval_007_ManualTest {

    private final Solution solution = new Solution();

    @Nested
    @DisplayName("Valid equivalence classes")
    class ValidClasses {

        static Stream<Arguments> validClasses() {
            return Stream.of(
                    Arguments.of("V1", List.of(), "x", List.of()),
                    Arguments.of("V2", Arrays.asList("red", "blue"), "z", List.of()),
                    Arguments.of("V3", List.of("same"), "same", List.of("same")),
                    Arguments.of("V4", Arrays.asList("start", "branch"), "sta", List.of("start")),
                    Arguments.of("V5", Arrays.asList("planet", "lane", "plain"), "lan", Arrays.asList("planet", "lane")),
                    Arguments.of("V6", Arrays.asList("ending", "pending", "end"), "ing", Arrays.asList("ending", "pending")),
                    Arguments.of("V7", Arrays.asList("aa", "bb", "aa"), "aa", Arrays.asList("aa", "aa")),
                    Arguments.of("V8", Arrays.asList("", "abc"), "", Arrays.asList("", "abc")),
                    Arguments.of("V9", Arrays.asList("", "abc"), "a", List.of("abc")),
                    Arguments.of("V10", Arrays.asList("Dog", "dog", "hotDog"), "Dog", Arrays.asList("Dog", "hotDog")),
                    Arguments.of("V11", Arrays.asList("a b", "a-b", "ab"), "a-", List.of("a-b")),
                    Arguments.of("V12", Arrays.asList("ışık", "isik", "güz"), "ış", List.of("ışık"))
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("validClasses")
        void validEquivalenceClasses(String id, List<String> input, String substring, List<String> expected) {
            assertEquals(expected, solution.filterBySubstring(new ArrayList<>(input), substring),
                    () -> id + " substring=\"" + substring + "\"");
        }
    }

    @Nested
    @DisplayName("Boundaries")
    class Boundaries {

        @Test
        @DisplayName("List length around zero and one")
        void listLengthBoundary() {
            assertEquals(List.of(), solution.filterBySubstring(new ArrayList<>(List.of()), "a"));
            assertEquals(List.of("a"), solution.filterBySubstring(new ArrayList<>(List.of("a")), "a"));
            assertEquals(Arrays.asList("a", "cab"),
                    solution.filterBySubstring(new ArrayList<>(Arrays.asList("a", "b", "cab")), "a"));
        }

        @Test
        @DisplayName("Substring length empty, one, many")
        void substringLengthBoundary() {
            assertEquals(Arrays.asList("", "a"), solution.filterBySubstring(new ArrayList<>(Arrays.asList("", "a")), ""));
            assertEquals(List.of("a"), solution.filterBySubstring(new ArrayList<>(Arrays.asList("", "a")), "a"));
            assertEquals(List.of("abc"), solution.filterBySubstring(new ArrayList<>(Arrays.asList("ab", "abc")), "abc"));
        }

        @Test
        @DisplayName("Whole, prefix, middle, suffix, and absent positions")
        void matchPositionBoundary() {
            assertEquals(Arrays.asList("abc", "abcd", "zabc", "zzabcyy"),
                    solution.filterBySubstring(new ArrayList<>(Arrays.asList("abc", "abcd", "zabc", "zzabcyy", "ab")), "abc"));
        }
    }

    @Nested
    @DisplayName("Invalid or underspecified inputs")
    class InvalidOrPinned {

        @Test
        @DisplayName("Null list throws")
        void nullListThrows() {
            assertThrows(NullPointerException.class, () -> solution.filterBySubstring(null, "x"));
        }

        @Test
        @DisplayName("Null substring throws")
        void nullSubstringThrows() {
            assertThrows(NullPointerException.class,
                    () -> solution.filterBySubstring(new ArrayList<>(List.of("abc")), null));
        }

        @Test
        @DisplayName("Null element throws once inspected")
        void nullElementThrows() {
            assertThrows(NullPointerException.class,
                    () -> solution.filterBySubstring(new ArrayList<>(Arrays.asList("abc", null)), "a"));
        }
    }
}
```

## Decision Note
Added independent Codex manual black-box notes and executable manual tests for HumanEval_007, including equivalence classes, boundaries, and pinned undefined null behavior.
