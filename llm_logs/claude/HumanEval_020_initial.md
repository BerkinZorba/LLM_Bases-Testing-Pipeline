---
prompt_id: HumanEval_020
model: claude
step: initial
iteration: 1
timestamp: 2026-04-25T00:00:00 (UTC; date-only — interactive session, exact wall-clock not captured)
output_files:
  - generated_code/claude/HumanEval_020.java
---

## Prompt (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
    From a supplied list of numbers (of length at least two) select and return two that are the closest to each
    other and return them in order (smaller number, larger number).
    >>> findClosestElements(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.2))
    [2.0, 2.2]
    >>> findClosestElements(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.0))
    [2.0, 2.0]
     */
    public List<Double> findClosestElements(List<Double> numbers) {
```

## Response (verbatim)
```java
import java.util.*;
import java.lang.*;

class Solution {
    /**
     * From a supplied list of numbers (of length at least two) select and return two that are the closest to each
     * other and return them in order (smaller number, larger number).
     * >>> findClosestElements(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.2))
     * [2.0, 2.2]
     * >>> findClosestElements(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 2.0))
     * [2.0, 2.0]
     */
    public List<Double> findClosestElements(List<Double> numbers) {
        List<Double> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);

        double minDiff = Double.MAX_VALUE;
        double first = 0, second = 0;

        for (int i = 0; i < sorted.size() - 1; i++) {
            double diff = sorted.get(i + 1) - sorted.get(i);
            if (diff < minDiff) {
                minDiff = diff;
                first = sorted.get(i);
                second = sorted.get(i + 1);
            }
        }

        return Arrays.asList(first, second);
    }
}
```

## Decision note
- How was this output used? Saved as-is to `generated_code/claude/HumanEval_020.java`. No edits.
- If changed before saving, what changed and why? No changes.
- What downstream artifacts did this produce or invalidate? Produces the candidate-under-test consumed by `tests/base_tests/claude/HumanEval_020_BaseTest.java` and the improved/manual suites. Does not invalidate any prior artifact (none existed for this prompt under `claude/`).
