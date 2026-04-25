---
prompt_id: HumanEval_057
model: claude
step: initial
iteration: 1
timestamp: 2026-04-25T00:00:00 (local)
output_files:
  - generated_code/claude/HumanEval_057.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Return True is list elements are monotonically increasing or decreasing.
    >>> monotonic(Arrays.asList(1, 2, 4, 20))
    true
    >>> monotonic(Arrays.asList(1, 20, 4, 10))
    false
    >>> monotonic(Arrays.asList(4, 1, 0, -10))
    true
     */
    public boolean monotonic(List<Integer> l) {
```

## Response (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    Return True is list elements are monotonically increasing or decreasing.
    >>> monotonic(Arrays.asList(1, 2, 4, 20))
    true
    >>> monotonic(Arrays.asList(1, 20, 4, 10))
    false
    >>> monotonic(Arrays.asList(4, 1, 0, -10))
    true
     */
    public boolean monotonic(List<Integer> l) {
        boolean nonDecreasing = true;
        boolean nonIncreasing = true;
        for (int i = 1; i < l.size(); i++) {
            if (l.get(i) < l.get(i - 1)) {
                nonDecreasing = false;
            }
            if (l.get(i) > l.get(i - 1)) {
                nonIncreasing = false;
            }
        }
        return nonDecreasing || nonIncreasing;
    }
}
```

## Decision note
- How was this output used? Saved as-is to `generated_code/claude/HumanEval_057.java`. No edits to the LLM output before saving (per Section 9 guardrail: do not silently edit generated code before base testing).
- If changed before saving, what changed and why? No changes.
- What downstream artifacts did this produce or invalidate? Produces the input for `tests/base_tests/claude/HumanEval_057_BaseTest.java` execution (next step). Coverage and analysis artifacts for HumanEval_057/claude do not yet exist.
