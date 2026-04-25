---
prompt_id: HumanEval_095
model: claude
step: initial
iteration: 1
timestamp: 2026-04-26T00:00:00 (local)
output_files:
  - generated_code/claude/HumanEval_095.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Given a map, return True if all keys are strings in lower
    case or all keys are strings in upper case, else return False.
    The function should return False is the given map is empty.
    Examples:
    checkDictCase({"a":"apple", "b":"banana"}) should return True.
    checkDictCase({"a":"apple", "A":"banana", "B":"banana"}) should return False.
    checkDictCase({"a":"apple", 8:"banana", "a":"apple"}) should return False.
    checkDictCase({"Name":"John", "Age":"36", "City":"Houston"}) should return False.
    checkDictCase({"STATE":"NC", "ZIP":"12345" }) should return True.
     */
    public boolean checkDictCase(Map<Object, Object> dict) {
```

## Response (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Given a map, return True if all keys are strings in lower
    case or all keys are strings in upper case, else return False.
    The function should return False is the given map is empty.
    Examples:
    checkDictCase({"a":"apple", "b":"banana"}) should return True.
    checkDictCase({"a":"apple", "A":"banana", "B":"banana"}) should return False.
    checkDictCase({"a":"apple", 8:"banana", "a":"apple"}) should return False.
    checkDictCase({"Name":"John", "Age":"36", "City":"Houston"}) should return False.
    checkDictCase({"STATE":"NC", "ZIP":"12345" }) should return True.
     */
    public boolean checkDictCase(Map<Object, Object> dict) {
        if (dict.isEmpty()) {
            return false;
        }
        boolean allLower = true;
        boolean allUpper = true;
        for (Object key : dict.keySet()) {
            if (!(key instanceof String)) {
                return false;
            }
            String s = (String) key;
            if (!s.equals(s.toLowerCase())) {
                allLower = false;
            }
            if (!s.equals(s.toUpperCase())) {
                allUpper = false;
            }
        }
        return allLower || allUpper;
    }
}
```

## Decision note
- How was this output used? Saved as-is to `generated_code/claude/HumanEval_095.java`.
- If changed before saving, what changed and why? No changes.
- What downstream artifacts did this produce or invalidate? Provides the input for `tests/base_tests/claude/HumanEval_095_BaseTest.java`, `tests/improved_tests/claude/HumanEval_095_ImprovedTest.java`, `tests/manual_tests/claude/HumanEval_095_*`, and the JaCoCo runs under `coverage_reports/HumanEval_095/claude/`.
- Approach note: empty maps short-circuit to `false` per spec. The keys are checked one-by-one: any non-`String` key short-circuits to `false` (handles the `8:"banana"` dataset row). For `String` keys, two flags `allLower` and `allUpper` are kept in sync — a key that does not equal its `toLowerCase()` form clears `allLower`; a key that does not equal its `toUpperCase()` form clears `allUpper`. The function returns `allLower || allUpper`. Strings with no cased letters (e.g. `"12345"`, `""`) leave both flags true, so a map whose keys mix `"STATE"` and `"ZIP"` (where `"ZIP"` is upper-cased but `"12345"` would not be required) still returns `true` via the `allUpper` path.
