---
prompt_id: HumanEval_007
model: claude
step: initial
iteration: 1
timestamp: 2026-04-25T00:00:00 (UTC; date-only — interactive session, exact wall-clock not captured)
output_files:
  - generated_code/claude/HumanEval_007.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Filter an input list of strings only for ones that contain given substring
    >>> filterBySubstring(List.of(), "a")
    []
    >>> filterBySubstring(Arrays.asList("abc", "bacd", "cde", "array"), "a")
    ["abc", "bacd", "array"]
     */
    public List<String> filterBySubstring(List<String> strings, String substring) {
```

## Response (verbatim)
```java
import java.lang.*;
import java.util.*;

class Solution {
    /**
     * Filter an input list of strings only for ones that contain given substring
     * >>> filterBySubstring(List.of(), "a")
     * []
     * >>> filterBySubstring(Arrays.asList("abc", "bacd", "cde", "array"), "a")
     * ["abc", "bacd", "array"]
     */
    public List<String> filterBySubstring(List<String> strings, String substring) {
        List<String> result = new ArrayList<>();
        for (String s : strings) {
            if (s.contains(substring)) {
                result.add(s);
            }
        }
        return result;
    }
}
```

## Decision note
- How was this output used? Saved as-is to `generated_code/claude/HumanEval_007.java`. No edits.
- If changed before saving, what changed and why? No changes.
- What downstream artifacts did this produce or invalidate? Produces the candidate-under-test consumed by `tests/base_tests/claude/HumanEval_007_BaseTest.java` and the improved/manual suites. Does not invalidate any prior artifact (none existed for this prompt under `claude/`).
