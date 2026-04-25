---
prompt_id: HumanEval_018
model: claude
step: initial
iteration: 1
timestamp: 2026-04-25T00:00:00 (UTC; date-only — interactive session, exact wall-clock not captured)
output_files:
  - generated_code/claude/HumanEval_018.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Find how many times a given substring can be found in the original string. Count overlaping cases.
    >>> howManyTimes("", "a")
    0
    >>> howManyTimes("aaa", "a")
    3
    >>> howManyTimes("aaaa", "aa")
    3
     */
    public int howManyTimes(String string, String substring) {
```

## Response (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
     * Find how many times a given substring can be found in the original string. Count overlapping cases.
     * >>> howManyTimes("", "a")
     * 0
     * >>> howManyTimes("aaa", "a")
     * 3
     * >>> howManyTimes("aaaa", "aa")
     * 3
     */
    public int howManyTimes(String string, String substring) {
        if (string.isEmpty() || substring.isEmpty() || substring.length() > string.length()) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i <= string.length() - substring.length(); i++) {
            if (string.startsWith(substring, i)) {
                count++;
            }
        }
        return count;
    }
}
```

## Decision note
- How was this output used? Saved as-is to `generated_code/claude/HumanEval_018.java`. No edits.
- If changed before saving, what changed and why? No changes.
- What downstream artifacts did this produce or invalidate? Produces the candidate-under-test consumed by `tests/base_tests/claude/HumanEval_018_BaseTest.java` and the improved/manual suites. Does not invalidate any prior artifact (none existed for this prompt under `claude/`).
